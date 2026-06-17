package entidad;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Modalidad {
	private int idModalidad;
	private String nombre;
	private String numHombres;
	private String numMujeres;
	private String edadMaxima;
	private String edadMinima;
	private String sede;
	private int estado;
	private LocalDateTime fechaRegistro;
	private LocalDateTime fechaActualizacion;
	
	//foreign key
	private Deporte deporte;
}