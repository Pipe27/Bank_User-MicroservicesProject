package co.edu.usbcali.bank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import co.edu.usbcali.bank.user.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UserServiceIT {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Test
	@Order(1)
	void debeCrearUnUser() throws Exception {
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
		user = userService.save(user);
		
		//Assert
		assertNotNull(user, "El user es nulo, no se pudo grabar");
		
	}
	
	@Test
	@Order(2)
	void debeModificarUnUser() throws Exception {
		//Arrange
		String idUser = "felipea.97@hotmail.com";
		
		Users user = userService.findById(idUser).get();
		
		
		String nameExpected = "Catalina";
		user.setName("Catalina");
		
		//Act
		user = userService.update(user);
		
		//Assert
		assertNotNull(user, "El user es nulo, no se pudo grabar");
		assertEquals(nameExpected, user.getName());
		
	}
	
	@Test
	@Order(3)
	void debeBorrarUnUser() throws Exception {
		//Arrange
		String idUser = "felipea.97@hotmail.com";
		
		Users user = userService.findById(idUser).get();
		Optional<Users> userOptional = null;
		
		//Act
		userService.delete(user);
		userOptional = userService.findById(idUser);
		
		//Assert
		assertFalse(userOptional.isPresent(), "El user no se eliminó");
		
	}
	
	@Test
	@Order(4)
	void debeConsultarUsers() {
		//Arrange
		List<Users> users = null;
		
		//Act
		users = userService.findAll();
		
		//Assert
		assertFalse(users.isEmpty(), "No se pudieron consultar todos los usuarios");
		
	}
	
	@Test
	@Order(5)
	void noDebeBorrarUnUser() throws Exception {
		//Arrange
		String idUser = "jhume8@sourceforge.net";
		String actualMessage = null;
		String expectedMessage = "El user que intenta eliminar, tiene transacciones asociadas";
		
		//Act
		RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
			userService.deleteById(idUser);
		});
		
		actualMessage = runtimeException.getMessage();
		
		//Assert
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

}
