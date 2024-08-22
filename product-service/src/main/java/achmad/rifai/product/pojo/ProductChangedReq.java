package achmad.rifai.product.pojo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

	@NotNull
	@NotEmpty
	@NotBlank
	private String name;
	@NotNull
	@Positive
	private Double price;
	@NotNull
	@PositiveOrZero
	private Integer quantity;

}
