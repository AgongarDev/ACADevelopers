package sistema.basics;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public class Administrador extends Persona implements IUsuario {
	
	private float salarioBruto;
	private float salarioNeto;
	private String tipoContrato;
	private ArrayList<Proyecto> listaProyectos;
	
	
	// Constructor de la clase Trabajador
	public Administrador ( ) {
		super();
	}
	
	public Administrador(String dni, String nombre, String apellidos, String telefono, String domicilio,
			String fechaInicio, String fechaFin, String cargo, String correo, float salarioB, float salarioN, String tipoContrato, AdministracionFisica sede, Ong ong) {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sede, cargo, correo, ong);
		this.salarioBruto = salarioB;
		this.salarioNeto = salarioN;
		this.tipoContrato = tipoContrato;
		this.listaProyectos = ong.getProyectos();
	}

	// métodos
	
	public float getSalarioBruto() {
		return salarioBruto;
	}
	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	public float getSalarioNeto() {
		return salarioNeto;
	}
	public void setSalarioNeto(float salarioNeto) {
		this.salarioNeto = salarioNeto;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	@Override
	public void abrirSesion() throws IOException, JAXBException {
		//aquí introduciremos el menú de acciones del adminstrador.
	}
	// como administrador puede listar la información completa de todos los proyectos existentes.
	
	@Override
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		System.out.println("Listado de proyectos :");
		for (Proyecto elem : listaProyectos) {
			elem.toString();
		}
	}
	
	
	
}
