package achmad.rifai.inventory.pojo.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductChangedReq implements Serializable {

	private static final long serialVersionUID = -3308427798875077854L;

	private String name;
	private Double price;
	private Integer quantity;

}
