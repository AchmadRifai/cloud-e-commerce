package achmad.rifai.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.order.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
