package achmad.rifai.product.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import achmad.rifai.product.pojo.ProductChangedReq;
import achmad.rifai.product.pojo.ProductShowRes;
import achmad.rifai.product.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/{id}")
	public ProductShowRes get(@PathVariable Long id) {
		return service.get(id);
	}

	@PostMapping
	public ProductShowRes add(@RequestBody @Valid ProductChangedReq req) {
		return service.add(req);
	}

	@PutMapping("/{id}")
	public ProductShowRes update(@PathVariable Long id, @RequestBody @Valid ProductChangedReq req) {
		return service.update(id, req);
	}

	@DeleteMapping("/{id}")
	public ProductShowRes delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
