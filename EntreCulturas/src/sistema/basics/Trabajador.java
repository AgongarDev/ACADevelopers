package sistema.basics;


public class Trabajador extends Persona{

	private float salarioBruto;
	private float salarioNeto;
	private String tipoContrato;
	
// Constructor de la clase Trabajador
	public void Trabajador ( )
	
	
	//	Métodos de la clase Trabajador
	
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
	
}
