package achmad.rifai.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import achmad.rifai.inventory.pojo.ErrorRes;
import achmad.rifai.inventory.pojo.SupplyReq;
import achmad.rifai.inventory.services.InventoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@PostMapping
	public ErrorRes supplying(@RequestBody @Valid SupplyReq req) {
		return service.supplying(req);
	}

}
