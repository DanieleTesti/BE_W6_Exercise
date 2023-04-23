package main.ex.spring.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityExistsException;
import main.ex.spring.entity.User;
import main.ex.spring.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	@Qualifier("customUtente")
	private ObjectProvider<User> customUtente;

	public void createUtente(String Username, String nome, String email, String password, String name) {
		User u = customUtente.getObject();
		u.setUsername(Username);
		u.setPassword(password);
		u.setEmail(email);
		u.setName(name);
		saveUtente(u);

	}

	public void saveUtente(User u) {
		repo.save(u);
	}

	public String removeUser(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("Utente inesistente!!");
		} else {
			repo.deleteById(id);
		}
		return "Utente eliminato";
	}

	public User updateUser(User user) {
		if (!repo.existsById(user.getId())) {
			throw new EntityExistsException("Utente inesistente!!");
		}
		repo.save(user);
		return user;
	}

	public User findUtenteByID(Long id) {
		return repo.findById(id).get();
	}

	public List<User> findAll() {
		return (List<User>) repo.findAll();
	}
}
