package acadevs.entreculturas.modelo;

import java.util.ArrayList;
import java.io.Serializable;

public class TotalIngresos implements Serializable {

	private int any;
	private float importe;
	private String moneda;
	private float neto;
	private float bruto;
	private Ong ong;
	private ArrayList<Subvencion> subvenciones;
	private ArrayList<AportacionParticular> aportacionesParticulares;
	
	//constructor de la clase
	
	public TotalIngresos() {
		super();
	}
	
	public TotalIngresos(int any, float importe, String moneda, float neto, float bruto, Ong ong) {
		super();
		this.any = any;
		this.importe = importe;
		this.moneda = moneda;
		this.neto = neto;
		this.bruto = bruto;
		this.ong = ong;
	}

	public int getAny() {
		return any;
	}

	public void setAny(int any) {
		this.any = any;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public float getNeto() {
		return neto;
	}

	public void setNeto(float neto) {
		this.neto = neto;
	}

	public float getBruto() {
		return bruto;
	}

	public void setBruto(float bruto) {
		this.bruto = bruto;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	public void listaSubvenciones() {
		for (Subvencion elem : subvenciones) {
			elem.toString();
		}
	}

	public void addSubvencion(Subvencion aportacion) {
		this.subvenciones.add(aportacion);
	}

	public void listaAportacionesParticulares() {
		for (AportacionParticular elem : aportacionesParticulares) {
			elem.toString();
		}
	}

	public void addAportacionParticular(AportacionParticular aportacion) {
		this.aportacionesParticulares.add(aportacion);
	}
	

	
	/*
 * /*
 * muestra en pantalla el contenido de la clase
 * 
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        String landingDate = formatDate.format(this.flightdate.getTime());
        
            str = str.append(ID + ": " + this.id + NL);
            str = str.append(FLIGHTTIME + ": " + this.flightDuration + NL );
            str = str.append(LANDDATE +": " + landingDate + NL);
            str = str.append(LANDHOUR + ": " + this.landHour + NL );
        
        return str.toString();
    }*/
	
}
