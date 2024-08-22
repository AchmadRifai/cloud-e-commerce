package achmad.rifai.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import achmad.rifai.inventory.feigns.ProductFeign;
import achmad.rifai.inventory.feigns.UserFeign;
import achmad.rifai.inventory.models.SupplyLog;
import achmad.rifai.inventory.pojo.ErrorRes;
import achmad.rifai.inventory.pojo.SupplyReq;
import achmad.rifai.inventory.pojo.product.ProductShowRes;
import achmad.rifai.inventory.pojo.user.UserDetailRes;
import achmad.rifai.inventory.repositories.SupplyLogRepository;
import achmad.rifai.inventory.services.InventoryService;

@SpringBootTest
public class InventoryServiceTests {

	@Autowired
	InventoryService service;

	@MockBean
	SupplyLogRepository repository;

	@MockBean
	UserFeign userFeign;

	@MockBean
	ProductFeign productFeign;

	@Test
	void testSupplying_Success() {
		when(userFeign.findByUsername(eq(USER_DETAIL_RES.getUsername()))).thenReturn(USER_DETAIL_RES);
		when(productFeign.get(eq(PRODUCT_SHOW_RES.getId()))).thenReturn(PRODUCT_SHOW_RES);
		when(productFeign.update(eq(PRODUCT_SHOW_RES.getId()), any())).thenReturn(PRODUCT_SHOW_RES);
		when(repository.save(any())).thenReturn(SUPPLY_LOG);
		assertEquals(GOOD_NEWS, service.supplying(SUPPLY_REQ));
	}

	@Test
	void testSupplying_WrongPassword() {
		when(userFeign.findByUsername(eq(USER_DETAIL_RES.getUsername()))).thenReturn(USER_DETAIL_RES);
		final var req = SupplyReq.builder().username(USER_DETAIL_RES.getUsername()).password("WrongPassword").build();
		assertThrows(IllegalArgumentException.class, () -> service.supplying(req));
	}

	private static final UserDetailRes USER_DETAIL_RES = UserDetailRes.builder()
			.id(1L)
			.name("Achmad Rifa'i")
			.password("12345678")
			.username("AchmadRifai")
			.build();

	private static final ProductShowRes PRODUCT_SHOW_RES = ProductShowRes.builder()
			.id(USER_DETAIL_RES.getId())
			.name("Kopi Hitam")
			.price(2_000.0)
			.quantity(1_000)
			.build();

	private static final ErrorRes GOOD_NEWS = ErrorRes.builder().message("Success").build();

	private static final SupplyReq SUPPLY_REQ = SupplyReq.builder()
			.password(USER_DETAIL_RES.getPassword())
			.productId(PRODUCT_SHOW_RES.getId())
			.qty(3L)
			.username(USER_DETAIL_RES.getUsername())
			.build();

	private static final SupplyLog SUPPLY_LOG = SupplyLog.builder()
			.createdAt(Timestamp.valueOf(LocalDateTime.now()))
			.id(USER_DETAIL_RES.getId())
			.productId(PRODUCT_SHOW_RES.getId())
			.qty(SUPPLY_REQ.getQty())
			.userId(USER_DETAIL_RES.getId())
			.build();

}
