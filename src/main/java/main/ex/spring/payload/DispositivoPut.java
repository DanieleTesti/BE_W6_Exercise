package main.ex.spring.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.ex.spring.entity.StatoDispositivo;
import main.ex.spring.entity.TipoDispositivo;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoPut {

	private String modello;
	private TipoDispositivo tipoDispositivo;
	private StatoDispositivo statoDispositivo;
}
