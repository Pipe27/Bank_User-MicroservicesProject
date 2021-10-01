package co.edu.usbcali.bank.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.user.domain.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	List<Users> findByEnable(String enable);

}
