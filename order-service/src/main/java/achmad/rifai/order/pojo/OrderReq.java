package achmad.rifai.order.pojo;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderReq implements Serializable {

	private static final long serialVersionUID = 4672994931913187343L;

	@NotNull
	@Positive
	private Long productId;
	@NotNull
	@Positive
	private Long qty;

}
