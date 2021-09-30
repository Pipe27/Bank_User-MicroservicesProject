package co.edu.usbcali.bank.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.user.domain.Users;
import co.edu.usbcali.bank.user.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	@Mapping(source = "userType.ustyId", target = "ustyId")
	public UserDTO userToUserDTO(Users user);
	
	@Mapping(target = "userType.ustyId", source = "ustyId")
	public Users userDTOToUser(UserDTO userDTO);
	
	public List<UserDTO> userListToUserDTOList(List<Users> users);
	
	public List<Users> userDTOListToUserList(List<UserDTO> userDTOs);

}
