package achmad.rifai.inventory.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "supply_log")
public class SupplyLog implements Serializable {

	private static final long serialVersionUID = -3517557078598112481L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;
	@Column(nullable = false)
	private Long productId;
	@Column(nullable = false)
	private Long qty;
	@Builder.Default
	@Column(nullable = false)
	private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

}
