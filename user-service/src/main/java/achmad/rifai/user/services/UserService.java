package achmad.rifai.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.user.exceptions.NoDataException;
import achmad.rifai.user.mappers.UserMapper;
import achmad.rifai.user.pojo.UserChangesReq;
import achmad.rifai.user.pojo.UserDetailRes;
import achmad.rifai.user.pojo.UserShowRes;
import achmad.rifai.user.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserShowRes get(Long id) {
		return userRepository.findById(id)
				.map(user -> UserMapper.INSTANCE.userToUserShowRes(user))
				.orElseThrow(() -> new NoDataException(String.format("User %d not found", id)));
	}

	public UserDetailRes getByUsername(String username) {
		return userRepository.findByUsername(username)
				.map(user -> UserMapper.INSTANCE.userToUserDetailRes(user))
				.orElseThrow(() -> new NoDataException(String.format("%s not found", username)));
	}

	@Transactional
	public UserShowRes add(UserChangesReq req) {
		if (userRepository.findByUsername(req.getUsername()).isPresent()) 
			throw new IllegalArgumentException(String.format("%s is exists", req.getUsername()));
		final var mapper = UserMapper.INSTANCE;
		return mapper.userToUserShowRes(userRepository.save(mapper.userChangesReqToUser(req)));
	}

	@Transactional
	public UserShowRes update(Long id, UserChangesReq req) {
		if (userRepository.findByUsername(req.getUsername()).filter(user -> user.getId() != id).isPresent()) 
			throw new IllegalArgumentException(String.format("%s is exists", req.getUsername()));
		final var user = userRepository.findById(id).orElseThrow(() -> new NoDataException(String.format("User %d not found", id)));
		user.setName(req.getName());
		user.setPassword(req.getPassword());
		user.setUsername(req.getUsername());
		return UserMapper.INSTANCE.userToUserShowRes(userRepository.save(user));
	}

	@Transactional
	public UserShowRes delete(Long id) {
		final var user = userRepository.findById(id).orElseThrow(() -> new NoDataException(String.format("User %d not found", id)));
		userRepository.delete(user);
		return UserMapper.INSTANCE.userToUserShowRes(user);
	}

}
