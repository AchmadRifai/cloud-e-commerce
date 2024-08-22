package achmad.rifai.inventory.pojo;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ErrorRes implements Serializable {

	private static final long serialVersionUID = -6767336434886764857L;

	private String message;
	private Map<String, String> messages;

}
