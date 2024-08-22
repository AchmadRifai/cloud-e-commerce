package achmad.rifai.order.pojo.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductShowRes implements Serializable {

	private static final long serialVersionUID = -929401518587231512L;

	private Long id;

	private String name;
	private Double price;
	private Integer quantity;

}
