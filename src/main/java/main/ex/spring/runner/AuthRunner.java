package main.ex.spring.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import main.ex.spring.entity.Dispositivo;
import main.ex.spring.entity.ERole;
import main.ex.spring.entity.Role;
import main.ex.spring.entity.StatoDispositivo;
import main.ex.spring.entity.TipoDispositivo;
import main.ex.spring.entity.User;
import main.ex.spring.repository.DispositivoRepository;
import main.ex.spring.repository.RoleRepository;
import main.ex.spring.repository.UserRepository;
import main.ex.spring.service.AuthService;
import main.ex.spring.service.DispositivoService;
import main.ex.spring.service.UserService;

@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired
	DispositivoRepository dispositivoRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	@Autowired
	DispositivoService dispositivoService;
	@Autowired
	UserService userService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// setRoleDefault();

		User u = new User();
		u.setPassword(passwordEncoder.encode("pass"));
		u.setName("Daniele");
		u.setUsername("Testi");
		u.setEmail("d.t@mail.com");
		// userService.saveUtente(u);
//
		Dispositivo d1 = new Dispositivo();
		d1.setModello("Apple");
		d1.setTipoDispositivo(TipoDispositivo.SMARTPHONE);
		d1.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
		// dispositivoService.saveDispositivo(d1);
		// dispositivoService.removeDispositivo(3l);

		// dispositivoService.addDispositivoToUtente(1l, 1l);
		
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}

}
