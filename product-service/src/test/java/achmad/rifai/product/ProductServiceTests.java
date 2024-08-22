package achmad.rifai.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import achmad.rifai.product.exceptions.NoDataException;
import achmad.rifai.product.models.Product;
import achmad.rifai.product.pojo.ProductChangedReq;
import achmad.rifai.product.pojo.ProductShowRes;
import achmad.rifai.product.repositories.ProductRepository;
import achmad.rifai.product.services.ProductService;

@SpringBootTest
public class ProductServiceTests {

	@Autowired
	ProductService service;

	@MockBean
	ProductRepository repository;

	@Test
	void testGet_Success() {
		when(repository.findById(ID)).thenReturn(Optional.of(PRODUCT));
		assertEquals(PRODUCT_SHOW_RES, service.get(ID));
	}

	@Test
	void testGet_NotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.get(ID));
	}

	@Test
	void testAdd_Success() {
		when(repository.save(any())).thenReturn(PRODUCT);
		assertEquals(PRODUCT_SHOW_RES, service.add(PRODUCT_CHANGED_REQ));
	}

	@Test
	void testUpdate_Success() {
		when(repository.findById(ID)).thenReturn(Optional.of(PRODUCT));
		when(repository.save(any())).thenReturn(PRODUCT);
		assertEquals(PRODUCT_SHOW_RES, service.update(ID, PRODUCT_CHANGED_REQ));
	}

	@Test
	void testUpdate_NotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.update(ID, PRODUCT_CHANGED_REQ));
	}

	@Test
	void testDelete_Success() {
		when(repository.findById(ID)).thenReturn(Optional.of(PRODUCT));
		assertEquals(PRODUCT_SHOW_RES, service.delete(ID));
	}

	@Test
	void testDelete_NotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.delete(ID));
	}

	private static final Long ID = 1L;

	private static final Product PRODUCT = Product.builder()
			.id(ID)
			.name("HP")
			.price(1_000_000.0)
			.quantity(100_000)
			.build();

	private static final ProductChangedReq PRODUCT_CHANGED_REQ = ProductChangedReq.builder()
			.name(PRODUCT.getName())
			.price(PRODUCT.getPrice())
			.quantity(PRODUCT.getQuantity())
			.build();

	private static final ProductShowRes PRODUCT_SHOW_RES = ProductShowRes.builder()
			.name(PRODUCT.getName())
			.price(PRODUCT.getPrice())
			.quantity(PRODUCT.getQuantity())
			.id(PRODUCT.getId())
			.build();

}
