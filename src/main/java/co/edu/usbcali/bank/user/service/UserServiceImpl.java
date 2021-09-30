package co.edu.usbcali.bank.user.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.user.domain.Users;
import co.edu.usbcali.bank.user.repository.UserRepository;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String id) {
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users save(Users entity) throws Exception {
		if(entity == null) {
			throw new Exception("El user es nulo");
		}
		validate(entity);
		if(userRepository.existsById(entity.getUserEmail())) {
			throw new Exception("El usuario ya existe");
		}
		return userRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users update(Users entity) throws Exception {
		if(entity == null) {
			throw new Exception("El user es nulo");
		}
		validate(entity);
		if(userRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El usuario ya existe");
		}
		return userRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Users entity) throws Exception {
		if(entity == null) {
			throw new Exception("El user es nulo");
		}
		
		if(entity.getUserEmail() == null) {
			throw new Exception("El id del user es nulo");
		}
		
		if(userRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El user no existe");
		}
		
		findById(entity.getUserEmail()).ifPresent(user -> {
			if (user.getTransactions() != null && user.getTransactions().isEmpty() == false) {
				throw new RuntimeException("El user que intenta eliminar, tiene transacciones asociadas");
			}
		});
		
		userRepository.deleteById(entity.getUserEmail());
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null) {
			throw new Exception("El id del user es nulo");
		}
		
		if (findById(id).isPresent() == false) {
			throw new Exception("El user no existe");
		}
		
		delete(findById(id).get());
		
	}

	@Override
	public void validate(Users entity) throws Exception {
		Set<ConstraintViolation<Users>> constraintViolations = validator.validate(entity);
		if(constraintViolations.isEmpty() == false) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}	

}
