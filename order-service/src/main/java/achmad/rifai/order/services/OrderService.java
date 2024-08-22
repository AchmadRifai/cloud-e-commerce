package achmad.rifai.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.order.feigns.ProductFeign;
import achmad.rifai.order.models.Order;
import achmad.rifai.order.pojo.ErrorRes;
import achmad.rifai.order.pojo.OrderReq;
import achmad.rifai.order.pojo.product.ProductChangedReq;
import achmad.rifai.order.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductFeign productFeign;

	@Transactional
	public synchronized ErrorRes orderProduct(OrderReq req) {
		final var product = productFeign.get(req.getProductId());
		if (product.getQuantity() <= req.getQty()) throw new IllegalArgumentException("Insuficient stock");
		repository.save(Order.builder().qty(req.getQty()).productId(req.getProductId()).build());
		productFeign.update(req.getProductId(), ProductChangedReq.builder()
				.name(product.getName())
				.price(product.getPrice())
				.quantity((int) (product.getQuantity() - req.getQty()))
				.build());
		return ErrorRes.builder().message("Success").build();
	}

}
