package achmad.rifai.inventory.pojo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SupplyReq implements Serializable {

	private static final long serialVersionUID = -12557701567104936L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 8, max = 255)
	private String username;
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 8, max = 255)
	private String password;
	@NotNull
	@Positive
	private Long productId;
	@NotNull
	@Positive
	private Long qty;

}
