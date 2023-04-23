package main.ex.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import main.ex.spring.entity.Dispositivo;
import main.ex.spring.entity.StatoDispositivo;
import main.ex.spring.entity.User;
import main.ex.spring.exception.MyAPIException;
import main.ex.spring.payload.DispositivoDto;
import main.ex.spring.payload.DispositivoPut;
import main.ex.spring.repository.DispositivoRepository;
import main.ex.spring.repository.UserRepository;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository repo;
	@Autowired
	private UserRepository urepo;

	public void saveDispositivo(Dispositivo d) {
		repo.save(d);
		System.out.println("Dispositivo Salvato");
	}

	public String addDispositivo(DispositivoDto Dpto) {
		
		Dispositivo d = new Dispositivo();
		d.setModello(Dpto.getModello());
		d.setTipoDispositivo(Dpto.getTipoDispositivo());
		d.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
		
		
		System.out.println(d);
		repo.save(d);
		
		return "Dispositivo Aggiunto con successo!";
	}
	
	public List<Dispositivo> getAllDisp() {
		return repo.findAll();
	}
	
	public String removeDispositivo(Long id) {
		repo.deleteById(id);
		return "Dispositivo Eliminato con successo!";
	}
	 public String updateDispositivo(DispositivoPut dis, Long id) {
		 Dispositivo d = repo.findById(id).get();
		 if(d != null) {
			 d.setStatoDispositivo(dis.getStatoDispositivo());
			 repo.save(d);
			 return "Dispositivo modifica con successo!";
		 } else {
			 return "Dispositivo non trovato";
		 }
	 }
	 
	 public String addDispositivoToUtente(Long idU, Long idD) {
		 Dispositivo d = repo.findById(idD).get();
		 User u = urepo.findById(idU).get();
		 if(u != null) {
			 if(d != null) {
				 if(d.getStatoDispositivo().equals(StatoDispositivo.DISPONIBILE)) {
					 u.getDispositivi().add(d);
					 urepo.save(u);
					 d.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
					 repo.save(d);
					 return "Dispositivo associato con successo!";
				 } else {
					 throw new MyAPIException(HttpStatus.BAD_REQUEST, "Dispositivo già assegnato o dismesso!");
				 }
			 } else {
				 throw new MyAPIException(HttpStatus.BAD_REQUEST, "Dispositivo non trovato!");
			 }
		 } else {
			 throw new MyAPIException(HttpStatus.BAD_REQUEST, "Utentenon trovato!");
		 }
	 }
	 
	 public List<Dispositivo> getAllDispositiviByStato(StatoDispositivo stato) {
		 return repo.getDispositiviFromStato(stato);
	 }
}
