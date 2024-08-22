package achmad.rifai.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import achmad.rifai.user.models.User;
import achmad.rifai.user.pojo.UserChangesReq;
import achmad.rifai.user.pojo.UserDetailRes;
import achmad.rifai.user.pojo.UserShowRes;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User userChangesReqToUser(UserChangesReq req);

	UserShowRes userToUserShowRes(User user);

	UserDetailRes userToUserDetailRes(User user);

}
