package achmad.rifai.user.pojo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserChangesReq implements Serializable {

	private static final long serialVersionUID = 7586180105058446626L;

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
	@NotEmpty
	@NotBlank
	@Size(max = 255)
	private String name;

}
