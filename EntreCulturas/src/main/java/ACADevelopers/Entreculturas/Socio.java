package ACADevelopers.Entreculturas;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "Socio")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)

public class Socio extends Persona implements Usuario {
    private String pass;
	private float cuotaAportacion;
	Boolean estadoAportacion;
	private TipoCuota tipoCuota;
	private ArrayList<Socio> ListadoSocios;
	private DAOFactory xmlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.XML);
	private DAO<Socio> socioDAO = (XMLSocioDAO) xmlDAOFactory.getSocioDAO();
	private DAO<Proyecto>proyectoDAO = (XMLProyectoDAO) xmlDAOFactory.getProyectoDAO();
	// constructores
	
	public Socio() {
		super();
	}

	public Socio(String dni, String nombre, String apellidos, String telefono, String domicilio, String fechaInicio,
			String fechaFin, AdministracionFisica sedeAsignada, String cargo, String correo, float cuotaAportacion, boolean estadoAportacion, TipoCuota tipoCuota) 
	{
		super(dni, nombre, apellidos, telefono, domicilio, fechaInicio, fechaFin, sedeAsignada, cargo, correo);
		this.cuotaAportacion = cuotaAportacion;
		this.estadoAportacion = estadoAportacion;
		this.tipoCuota = tipoCuota;
	}


	//MÉTODOS
	
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
	
	@XmlElement(name = "cuota")
	public float getCuotaAportacion() {
		return cuotaAportacion;
	}

	public void setCuotaAportacion(float cuotaAportacion) {
		this.cuotaAportacion = cuotaAportacion;
	}

	@XmlElement(name = "activo")
	public Boolean getEstadoAportacion() {
		return estadoAportacion;
	}
	public void setEstadoAportacion(Boolean estadoAportacion) {
		this.estadoAportacion = estadoAportacion;
	}

	@XmlElement
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

	@Override
	public void abrirSesion() throws IOException, JAXBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultarProyectos(ArrayList<Proyecto> lp) {
		// TODO Auto-generated method stub
		
	}
	
}
