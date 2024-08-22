package achmad.rifai.user.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserShowRes implements Serializable {

	private static final long serialVersionUID = -7207752983550435674L;

	private Long id;
	private String username;
	private String name;

}
