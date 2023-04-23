package main.ex.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.ex.spring.entity.Dispositivo;
import main.ex.spring.entity.StatoDispositivo;
import main.ex.spring.payload.DispositivoDto;
import main.ex.spring.payload.DispositivoPut;
import main.ex.spring.service.DispositivoService;

@RestController
@RequestMapping("/api/dispositivi")
public class DispositiviController {

	@Autowired DispositivoService dispositivoService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public  ResponseEntity<List<Dispositivo>> getAllDisp() {
		List<Dispositivo> response = dispositivoService.getAllDisp();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addDispositivo(@RequestBody DispositivoDto dispositivoDto ) {
		String response = dispositivoService.addDispositivo(dispositivoDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteDispositivo(@PathVariable Long id) {
		String response = dispositivoService.removeDispositivo(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/stato/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateStatoDispositivo(@RequestBody DispositivoPut dispositivoPut, @PathVariable Long id) {
		String response = dispositivoService.updateDispositivo(dispositivoPut, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/utente/{idU}/dispositivo/{idD}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> associaDispositivoAUtente(@PathVariable Long idU, @PathVariable Long idD) {
		String response = dispositivoService.addDispositivoToUtente(idU, idD);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/stato/{stato}")
	public ResponseEntity<List<Dispositivo>> getDispositiviByStato(@PathVariable StatoDispositivo stato) {
		List<Dispositivo> response = dispositivoService.getAllDispositiviByStato(stato);
				return ResponseEntity.ok(response);
	}
	
}
