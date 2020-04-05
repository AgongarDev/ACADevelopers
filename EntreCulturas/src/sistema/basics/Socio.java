package sistema.basics;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

public class Socio extends Persona implements IUsuario{

	private float cuotaAportacion;
	private boolean estadoAportacion;
	private TipoCuota tipoCuota;
	private ArrayList<Proyecto> listaProyectos;
	
	// constructores
	
	public Socio() {
		super();
	}

	public Socio(String dni, String nombre, String apellidos, String telefono, String domicilio, String fechaInicio,
			String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo, Ong ong, float cuotaAportacion, boolean estadoAportacion, String tipo) {
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo, ong);
		this.cuotaAportacion = cuotaAportacion;
		this.estadoAportacion = estadoAportacion;
		this.tipoCuota = TipoCuota.valueOf(tipo);
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
		return tipoCuota.getTexto();
	}

	public void setTipoCuota(String tipoCuota) {
		this.tipoCuota = TipoCuota.valueOf(tipoCuota);
	}

	public void addProyecto(Proyecto proyecto) {
		listaProyectos.add(proyecto);
	}
	
	//métodos de la interfaz
	
	@Override
	public void abrirSesion() throws IOException, JAXBException {
		//menú de acciones del socio
		
	}
	
	@Override
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		System.out.println("Listado de proyectos :");
		for (Proyecto elem : listaProyectos) {
			elem.toString();
		}
	}
	
	

	
}
