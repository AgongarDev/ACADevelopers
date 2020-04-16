package ACADevelopers.Entreculturas;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

public class Socio extends Persona {

	private float cuotaAportacion;
	private boolean estadoAportacion;
	private TipoCuota tipoCuota;
	private ArrayList<Socio> ListadoSocios;
	
	// constructores
	
	public Socio() {
		super();
	}

	public Socio(String dni, String nombre, String apellidos, String telefono, String domicilio, String fechaInicio,
			String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo, Ong ong, float cuotaAportacion, boolean estadoAportacion, TipoCuota tipoCuota) {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo, ong);
		this.cuotaAportacion = cuotaAportacion;
		this.estadoAportacion = estadoAportacion;
		this.tipoCuota = tipoCuota;
	}

	//getters y setters
	public float getCuotaAportacion() {
		return cuotaAportacion;
	}

	public void setCuotaAportacion(float cuotaAportacion) {
		this.cuotaAportacion = cuotaAportacion;
	}

	public boolean isEstadoAportacion() {
		return estadoAportacion;
	}

	public void setEstadoAportacion(boolean estadoAportacion) {
		this.estadoAportacion = estadoAportacion;
	}

	public String getTipoCuota() {
		return tipoCuota.toString();
	}

	public void setTipoCuota(TipoCuota tipodecuota) {
		this.tipoCuota.setEnumValue(tipodecuota);
	}

	public void addSocio(Socio socio) {
		ListadoSocios.add(socio);
	}
	
	public void consultarSocios(ArrayList<Socio> ListadoSocios) {
		System.out.println("Listado de socios :");
		for (Socio elem : ListadoSocios) {
			elem.toString();
		}
	}
	
	

	
}
