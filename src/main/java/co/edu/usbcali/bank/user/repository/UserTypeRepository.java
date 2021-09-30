package co.edu.usbcali.bank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.user.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer>{

}
