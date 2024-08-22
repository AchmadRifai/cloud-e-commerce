package achmad.rifai.inventory.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import achmad.rifai.inventory.pojo.product.ProductChangedReq;
import achmad.rifai.inventory.pojo.product.ProductShowRes;

@FeignClient("product-service")
public interface ProductFeign {

	@GetMapping("/product/{id}")
	ProductShowRes get(@PathVariable Long id);

	@PutMapping("/product/{id}")
	ProductShowRes update(@PathVariable Long id, @RequestBody ProductChangedReq req);

}
