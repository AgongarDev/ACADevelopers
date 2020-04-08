package ACADevelopers.Entreculturas;

public class Voluntario extends Persona {
	
	public Voluntario () {
		super();
	}

	public Voluntario(String dni, String nombre, String apellidos, String telefono, String domicilio,
			String fechaInicio, String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo,
			Ong ong) {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo);
}

}
