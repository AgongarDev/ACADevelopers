package acadevs.entreculturas.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rutaXML","mySQLHost","mySQLPuerto","mySQLUsuario","mySQLPass"})
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement
public class Config {

	//xml
	public static String rutaXML;
	//MySQL
	public static String MySQLHost;
	
	public static String MySQLPuerto;
	
	public static String MySQLDataBase;
	
	public static String MySQLUsuario;
	
	public static String MySQLPass;
			
	public Config () {
		
		Config.rutaXML = "xml/socios.xml";
		Config.MySQLHost = "localhost";
		Config.MySQLPuerto = "3306";
		Config.MySQLDataBase = "EntreculturasDB_v1";
		Config.MySQLUsuario = "ACADevelopers";
		Config.MySQLPass = "321998";
	}
	@XmlElement
	public String getRutaXML() {
		return rutaXML;
	}

	public void setRutaXML(String rutaXML) {
		Config.rutaXML = rutaXML;
	}
	@XmlElement
	public String getMySQLHost() {
		return MySQLHost;
	}

	public void setMySQLHost(String mySQLHost) {
		MySQLHost = mySQLHost;
	}
	@XmlElement
	public String getMySQLPuerto() {
		return MySQLPuerto;
	}

	public void setMySQLPuerto(String mySQLPuerto) {
		MySQLPuerto = mySQLPuerto;
	}

	@XmlElement
	public String getMySQLUsuario() {
		return MySQLUsuario;
	}

	public void setMySQLUsuario(String mySQLUsuario) {
		MySQLUsuario = mySQLUsuario;
	}
	@XmlElement
	public String getMySQLPass() {
		return MySQLPass;
	}

	public void setMySQLPass(String mySQLPass) {
		MySQLPass = mySQLPass;
	}
}
