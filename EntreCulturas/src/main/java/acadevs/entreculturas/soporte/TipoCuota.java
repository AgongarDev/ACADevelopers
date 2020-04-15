package acadevs.entreculturas.soporte;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType( name = "TipoDeCuota")
@XmlEnum
public enum TipoCuota {
	TRIM("Trimestral"), 
	MES("Mensual"), 
	ANUAL("Anual");

	private final String texto;
	//private TipoCuota cuota;
	
	TipoCuota (String texto) {
		this.texto = texto;
	}
/*	public void setEnumValue (TipoCuota nuevotipodecuota) {
		this.cuota = nuevotipodecuota; 
	}*/
	
	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return this.texto;
		
	}
}
