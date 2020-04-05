package ACADevelopers.Entreculturas;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

public class Socio extends Persona implements Usuario{

	private float cuotaAportacion;
	private boolean estadoAportacion;
	private TipoCuota tipoCuota;
	private ArrayList<Proyecto> listaProyectos;
	
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

	public void addProyecto(Proyecto proyecto) {
		listaProyectos.add(proyecto);
	}
	
	//m�todos de la interfaz
	
	@Override
	public void abrirSesion() throws IOException, JAXBException {
		//men� de acciones del socio
		
	}
	
	@Override
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		System.out.println("Listado de proyectos :");
		for (Proyecto elem : listaProyectos) {
			elem.toString();
		}
	}
	
	

	
}
