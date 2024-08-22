package achmad.rifai.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import achmad.rifai.user.exceptions.NoDataException;
import achmad.rifai.user.mappers.UserMapper;
import achmad.rifai.user.models.User;
import achmad.rifai.user.pojo.UserChangesReq;
import achmad.rifai.user.pojo.UserDetailRes;
import achmad.rifai.user.pojo.UserShowRes;
import achmad.rifai.user.repositories.UserRepository;
import achmad.rifai.user.services.UserService;

@SpringBootTest
class UserServiceTests {

	@Autowired
	UserService service;

	@MockBean
	UserRepository repository;

	@Test
	void testGet_Success() {
		when(repository.findById(ID)).thenReturn(Optional.of(USER));
		assertEquals(USER_SHOW_RES, service.get(ID));
	}

	@Test
	void testGet_NotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.get(ID));
	}

	@Test
	void testFindByUsername_Success() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(USER));
		assertEquals(USER_DETAIL_RES, service.getByUsername(USER.getUsername()));
	}

	@Test
	void testFindByUsername_NotFound() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.getByUsername(USER.getUsername()));
	}

	@Test
	void testAdd_Success() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(repository.save(UserMapper.INSTANCE.userChangesReqToUser(USER_CHANGES_REQ))).thenReturn(USER);
		assertEquals(USER_SHOW_RES, service.add(USER_CHANGES_REQ));
	}

	@Test
	void testAdd_Exists() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(USER));
		assertThrows(IllegalArgumentException.class, () -> service.add(USER_CHANGES_REQ));
	}

	@Test
	void testUpdate_Success() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(repository.findById(ID)).thenReturn(Optional.of(USER));
		when(repository.save(any())).thenReturn(USER);
		assertEquals(USER_SHOW_RES, service.update(ID, USER_CHANGES_REQ));
	}

	@Test
	void testUpdate_UsernameExists() {
		final var user2 = UserMapper.INSTANCE.userChangesReqToUser(USER_CHANGES_REQ);
		user2.setId(2L);
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(user2));
		assertThrows(IllegalArgumentException.class, () -> service.update(ID, USER_CHANGES_REQ));
	}

	@Test
	void testUpdate_NotFound() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.update(ID, USER_CHANGES_REQ));
	}

	@Test
	void testDelete_Success() {
		when(repository.findById(ID)).thenReturn(Optional.of(USER));
		assertEquals(USER_SHOW_RES, service.delete(ID));
	}

	@Test
	void testDelete_NotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.delete(ID));
	}

	private static final Long ID = 1L;

	private static final User USER = User.builder()
			.id(ID)
			.name("Achmad Rifa'i")
			.password("1234")
			.username("ai")
			.build();

	private static final UserChangesReq USER_CHANGES_REQ = UserChangesReq.builder()
			.name(USER.getName())
			.password(USER.getPassword())
			.username(USER.getUsername())
			.build();

	private static final UserShowRes USER_SHOW_RES = UserShowRes.builder()
			.id(ID)
			.name(USER.getName())
			.username(USER.getUsername())
			.build();

	private static final UserDetailRes USER_DETAIL_RES = UserDetailRes.builder()
			.id(ID)
			.name(USER.getName())
			.username(USER.getUsername())
			.password(USER.getPassword())
			.build();

}
