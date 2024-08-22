package achmad.rifai.inventory.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.inventory.feigns.ProductFeign;
import achmad.rifai.inventory.feigns.UserFeign;
import achmad.rifai.inventory.models.SupplyLog;
import achmad.rifai.inventory.pojo.ErrorRes;
import achmad.rifai.inventory.pojo.SupplyReq;
import achmad.rifai.inventory.pojo.product.ProductChangedReq;
import achmad.rifai.inventory.repositories.SupplyLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final SupplyLogRepository repository;

	private final UserFeign userFeign;

	private final ProductFeign productFeign;

	@Transactional
	public synchronized ErrorRes supplying(SupplyReq req) {
		final var user = userFeign.findByUsername(req.getUsername());
		if (!user.getPassword().equals(req.getPassword())) throw new IllegalArgumentException("Wrong password");
		final var product = productFeign.get(req.getProductId());
		productFeign.update(product.getId(), ProductChangedReq.builder()
				.name(product.getName())
				.price(product.getPrice())
				.quantity((int)(product.getQuantity() + req.getQty()))
				.build());
		repository.save(SupplyLog.builder()
				.productId(product.getId())
				.userId(user.getId())
				.qty(req.getQty())
				.build());
		return ErrorRes.builder().message("Success").build();
	}

}
