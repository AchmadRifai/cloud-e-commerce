package achmad.rifai.inventory.pojo.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDetailRes implements Serializable {

	private static final long serialVersionUID = 5196873382818481102L;

	private Long id;
	private String username;
	private String name;
	private String password;

}
