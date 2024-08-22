package achmad.rifai.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import achmad.rifai.user.pojo.UserChangesReq;
import achmad.rifai.user.pojo.UserDetailRes;
import achmad.rifai.user.pojo.UserShowRes;
import achmad.rifai.user.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/{id}")
	public UserShowRes get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	public UserDetailRes findByUsername(@RequestParam(name = "username", required = true) String username) {
		return service.getByUsername(username);
	}

	@PostMapping
	public UserShowRes add(@Valid @RequestBody UserChangesReq req) {
		return service.add(req);
	}

	@PutMapping("/{id}")
	public UserShowRes update(@PathVariable Long id, @Valid @RequestBody UserChangesReq req) {
		return service.update(id, req);
	}

	@DeleteMapping("/{id}")
	public UserShowRes delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
