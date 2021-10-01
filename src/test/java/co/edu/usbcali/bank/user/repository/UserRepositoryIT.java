package co.edu.usbcali.bank.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.bank.user.domain.UserType;
import co.edu.usbcali.bank.user.domain.Users;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UserRepositoryIT {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Test
	@Order(1)
	void debeCrearUnUser() {
		//Arrange
		Integer idUserType = 1;
		String idUser = "felipea.97@hotmail.com";
		
		Users user = null;
		UserType userType = null;
		
		Optional<UserType> utOptional = userTypeRepository.findById(idUserType);
		userType = utOptional.get();
		
		user = new Users();
		user.setEnable("N");
		user.setName("Catalina");
		user.setToken("kjashdd7831asd");
		user.setUserEmail(idUser);
		user.setUserType(userType);
		
		//Act
		user = userRepository.save(user);
		
		//Assert
		assertNotNull(user, "El user es nulo, no se pudo grabar");
		
	}
	
	@Test
	@Order(2)
	void debeModificarUnUser() {
		//Arrange
		String idUser = "felipea.97@hotmail.com";
		
		Users user = userRepository.findById(idUser).get();
		
		
		String nameExpected = "Catalina";
		user.setName("Catalina");
		
		//Act
		user = userRepository.save(user);
		
		//Assert
		assertNotNull(user, "El user es nulo, no se pudo grabar");
		assertEquals(nameExpected, user.getName());
		
	}
	
	@Test
	@Order(3)
	void debeBorrarUnUser() {
		//Arrange
		String idUser = "felipea.97@hotmail.com";
		
		Users user = userRepository.findById(idUser).get();
		Optional<Users> userOptional = null;
		
		//Act
		userRepository.delete(user);
		userOptional = userRepository.findById(idUser);
		
		//Assert
		assertFalse(userOptional.isPresent(), "El user no se eliminó");
		
	}
	
	@Test
	@Order(4)
	void debeConsultarUsers() {
		//Arrange
		List<Users> users = null;
		
		//Act
		users = userRepository.findAll();
		
		//Assert
		assertFalse(users.isEmpty(), "No se pudieron consultar todos los usuarios");
		
	}
	
	@Test
	@Order(5)
	void debeConsultarUserEnableYes() {
		//Arrange
		List<Users> users = null;
		
		//Act
		users = userRepository.findByEnable("Y");
		users.forEach(user -> log.info(user.getName()));
		
		//Assert
		assertFalse(users.isEmpty(), "No se pudieron consultar todos los usuarios que están activos");
		
	}

}
