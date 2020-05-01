package acadevs.entreculturas.modelo;

import java.io.Serializable;

public class Colaborador extends Persona implements Serializable{

	public Colaborador () {
		super();
	}

	public Colaborador(String dni, String nombre, String apellidos, String telefono, String domicilio,
			String fechaInicio, String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo) {
		super();
	}
}
