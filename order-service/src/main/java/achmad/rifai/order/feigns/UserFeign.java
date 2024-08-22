package achmad.rifai.order.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import achmad.rifai.order.pojo.user.UserDetailRes;

@FeignClient(name = "user-service")
public interface UserFeign {

	@GetMapping("/user")
	UserDetailRes findByUsername(@RequestParam(name = "username", required = true) String username);

}
