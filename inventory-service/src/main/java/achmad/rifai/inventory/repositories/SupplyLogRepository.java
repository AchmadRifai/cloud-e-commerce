package achmad.rifai.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.inventory.models.SupplyLog;

public interface SupplyLogRepository extends JpaRepository<SupplyLog, Long> {

}
