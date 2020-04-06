package ACADevelopers.Entreculturas;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Administrador extends Persona implements Usuario {
	
	private float salarioBruto;
	private float salarioNeto;
	private String tipoContrato;
	private ArrayList<Proyecto> listaProyectos;
	private String pass;
	
	
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

	// m�todos
	
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
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		System.out.println("Listado de proyectos :");
		for (Proyecto elem : listaProyectos) {
			elem.toString();
		}
	}

	/**
	 * Metodo accesor de lectura que nos da la password del trabajador.
	 * 
	 * @return Nos devuelve la password del trabajador.
	 */
	@XmlElement(name = "pass")
	public String getPass() {
		return pass;
	}

	/**
	 * Metodo accesor de lectura que asigna la password del trabajador.
	 * 
	 * @param pass La password del trabajador.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}