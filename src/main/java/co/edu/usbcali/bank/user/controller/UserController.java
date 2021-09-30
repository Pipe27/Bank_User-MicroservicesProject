package co.edu.usbcali.bank.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.user.domain.Users;
import co.edu.usbcali.bank.user.dto.UserDTO;
import co.edu.usbcali.bank.user.mapper.UserMapper;
import co.edu.usbcali.bank.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) throws Exception {
		log.info("Request to findById with user id: " + id);
		Users user = (userService.findById(id).isPresent() == true) ? userService.findById(id).get() : null;
		UserDTO userDTO = userMapper.userToUserDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll() throws Exception {
		List<UserDTO> userDTOs = userMapper.userListToUserDTOList(userService.findAll());
		return ResponseEntity.ok().body(userDTOs);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) throws Exception {
		Users user = userMapper.userDTOToUser(userDTO);
		user = userService.save(user);
		userDTO = userMapper.userToUserDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}
	
}
