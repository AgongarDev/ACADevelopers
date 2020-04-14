package acadevs.entreculturas.modelo;
public class MayorDeEdadException {	
	static void comprobacion(int edad){ 
	      if(edad<18) {
	         throw new ArithmeticException("Eres menor de edad. No te puedes registrar como socio todavía"); 
	      }
	      else {
	         System.out.println("Eres válido para registrarte!!"); 
	      }
	   } 
	   public static void main(String args[]){ 
	     System.out.println("Bienvenido al proceso de registro!!");
	     System.out.println("Que tengas un buen día.."); 
	 } 
}