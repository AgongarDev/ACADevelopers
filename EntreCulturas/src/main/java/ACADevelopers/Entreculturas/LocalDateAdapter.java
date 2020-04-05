package ACADevelopers.Entreculturas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Esta clase permite formatear las fechas al trabajar con XML.
 * 
 * @author Yaiza, Teresa y Marc.
 * @version 1.0
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, Date> {
	
	private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	// MÉTODOS
	
	/**
	 * Este metodo formatea la fecha cuando convertimos el XML a un objeto Java.
	 * 
	 * @param xml XML que queremos convertir.
	 * @return Fecha formateada.
	 */
	public Date unmarshal(String xml) throws Exception {
		return dateFormat.parse(xml);
	}
	
	/**
	 * Este metodo formatea la fecha cuando convertimos un objeto Java a XML.
	 * 
	 * @param object Objeto que queremos convertir.
	 * @return Fecha formateada.
	 */
	public String marshal(Date object) throws Exception {
		return dateFormat.format(object);
	}
	
}
