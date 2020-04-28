package acadevs.entreculturas.modelo;

import java.util.Date;

public class Voluntario extends Persona {
	
	public Voluntario () {
		super();
	}

	public Voluntario(String dni, String nombre, String apellidos, int telefono, String domicilio,
			Date fechaInicio, Date fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo,
			Ong ong) {
		super(dni, nombre, apellidos, domicilio, telefono, fechaInicio, fechaFin, sedeAsignada, cargo, correo);
}

}
