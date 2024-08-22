package achmad.rifai.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.product.exceptions.NoDataException;
import achmad.rifai.product.mappers.ProductMapper;
import achmad.rifai.product.pojo.ProductChangedReq;
import achmad.rifai.product.pojo.ProductShowRes;
import achmad.rifai.product.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	private final ProductMapper mapper = ProductMapper.INSTANCE;

	public ProductShowRes get(Long id) {
		return repository.findById(id)
				.map(product -> mapper.productToProductShowRes(product))
				.orElseThrow(() -> new NoDataException(String.format("%d not found", id)));
	}

	@Transactional
	public ProductShowRes add(ProductChangedReq req) {
		return mapper.productToProductShowRes(repository.save(mapper.productChangedReqToProduct(req)));
	}

	@Transactional
	public ProductShowRes update(Long id, ProductChangedReq req) {
		final var product = repository.findById(id).orElseThrow(() -> new NoDataException(String.format("%d not found", id)));
		product.setName(req.getName());
		product.setPrice(req.getPrice());
		product.setQuantity(req.getQuantity());
		return mapper.productToProductShowRes(repository.save(product));
	}

	@Transactional
	public ProductShowRes delete(Long id) {
		final var product = repository.findById(id).orElseThrow(() -> new NoDataException(String.format("%d not found", id)));
		repository.delete(product);
		return mapper.productToProductShowRes(product);
	}

}
