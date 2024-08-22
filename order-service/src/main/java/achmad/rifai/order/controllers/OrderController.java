package achmad.rifai.order.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import achmad.rifai.order.pojo.ErrorRes;
import achmad.rifai.order.pojo.OrderReq;
import achmad.rifai.order.services.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ErrorRes orderProduct(@RequestBody @Valid OrderReq req) {
		return service.orderProduct(req);
	}

}
