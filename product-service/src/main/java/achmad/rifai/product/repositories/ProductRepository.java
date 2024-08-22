package achmad.rifai.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.product.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
