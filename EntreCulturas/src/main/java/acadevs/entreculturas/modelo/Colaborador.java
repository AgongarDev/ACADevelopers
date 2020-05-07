package acadevs.entreculturas.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="colaboradores")
public class Colaborador extends Persona implements Serializable{

	public Colaborador () {
		super();
	}

	public Colaborador(String dni, String nombre, String apellidos, String telefono, String domicilio,
			String fechaInicio, String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo) {
		super();
	}
}
