package ACADevelopers.Entreculturas;

public class Colaborador extends Persona {

	public Colaborador () {
		super();
	}

	public Colaborador(String dni, String nombre, String apellidos, String telefono, String domicilio,
			String fechaInicio, String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo,
			Ong ong) {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo, ong);
	}
	

}
