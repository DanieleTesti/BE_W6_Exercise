package main.ex.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.ex.spring.entity.Dispositivo;
import main.ex.spring.entity.StatoDispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{

	@Query("SELECT d FROM Dispositivo d WHERE d.statoDispositivo = :stato") public List<Dispositivo> getDispositiviFromStato(@Param("stato") StatoDispositivo stato);


}
