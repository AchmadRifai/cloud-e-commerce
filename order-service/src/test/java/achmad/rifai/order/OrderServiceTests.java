package achmad.rifai.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import achmad.rifai.order.feigns.ProductFeign;
import achmad.rifai.order.models.Order;
import achmad.rifai.order.pojo.ErrorRes;
import achmad.rifai.order.pojo.OrderReq;
import achmad.rifai.order.pojo.product.ProductChangedReq;
import achmad.rifai.order.pojo.product.ProductShowRes;
import achmad.rifai.order.repositories.OrderRepository;
import achmad.rifai.order.services.OrderService;

@SpringBootTest
public class OrderServiceTests {

	@Autowired
	OrderService service;

	@MockBean
	OrderRepository repository;

	@MockBean
	ProductFeign productFeign;

	@Test
	void testOrder_Success() {
		when(repository.save(any())).thenReturn(ORDER);
		when(productFeign.get(ORDER_REQ.getProductId())).thenReturn(PRODUCT_SHOW_RES);
		when(productFeign.update(ORDER_REQ.getProductId(), PRODUCT_CHANGED_REQ)).thenReturn(PRODUCT_SHOW_RES);
		assertEquals(GOOD_NEWS, service.orderProduct(ORDER_REQ));
	}

	@Test
	void testOrder_Insuficient() {
		when(productFeign.get(ORDER_REQ.getProductId())).thenReturn(PRODUCT_SHOW_RES);
		assertThrows(IllegalArgumentException.class, () -> service.orderProduct(OrderReq.builder()
				.productId(ORDER_REQ.getProductId())
				.qty(1L + PRODUCT_SHOW_RES.getQuantity())
				.build()));
	}

	private static final OrderReq ORDER_REQ = OrderReq.builder()
			.productId(1L)
			.qty(10L)
			.build();

	private static final ErrorRes GOOD_NEWS = ErrorRes.builder()
			.message("Success")
			.build();

	private static final ProductShowRes PRODUCT_SHOW_RES = ProductShowRes.builder()
			.id(ORDER_REQ.getProductId())
			.name("Kopi Hitam")
			.price(3000.0)
			.quantity(1_000_000)
			.build();

	private static final ProductChangedReq PRODUCT_CHANGED_REQ = ProductChangedReq.builder()
			.name(PRODUCT_SHOW_RES.getName())
			.price(PRODUCT_SHOW_RES.getPrice())
			.quantity(PRODUCT_SHOW_RES.getQuantity())
			.build();

	private static final Order ORDER = Order.builder()
			.createdAt(Timestamp.valueOf(LocalDateTime.now()))
			.id(ORDER_REQ.getProductId())
			.productId(ORDER_REQ.getProductId())
			.qty(ORDER_REQ.getQty())
			.build();

}
