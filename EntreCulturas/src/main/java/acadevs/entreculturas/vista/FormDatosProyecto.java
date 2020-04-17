package acadevs.entreculturas.vista;

import acadevs.entreculturas.modelo.ViewException;

public class FormDatosProyecto  {
	
//	private String id;
	
	public FormDatosProyecto (String id) throws ViewException {
	
		// -- NO IMPLEMENTADO --
	//	this.id = id;
		//imprimeForm();
	}

/*public imprimeMenu() {
		
		Application.limpiapantalla();
		
		Proyecto nuevoProyecto = new Proyecto();
		
		System.out.println("\nIntroduce el nombre del proyecto: ");
		nuevoProyecto.setNombreProyecto(br.readLine());
		
		System.out.println("\nIntroduce el ID del proyecto: ");
		nuevoProyecto.setIdProyecto(br.readLine());
		
		System.out.println("\nIntroduce la linea de acción del proyecto (C/A/F/E): ");
		switch (br.readLine()) {
			case "C":
				nuevoProyecto.setlAccion(LineaDeAccion.COOP);
				break;
	
			case "A":
				nuevoProyecto.setlAccion(LineaDeAccion.ACC);;
				break;
	
			case "F":
				nuevoProyecto.setlAccion(LineaDeAccion.FORT);
				break;
			case "E":
				nuevoProyecto.setlAccion(LineaDeAccion.EDU);
				break;
	
			default:
				break;
		}
		
		System.out.println("\nIntroduce fecha de inicio del proyecto: ");
			nuevoProyecto.setFechaInicio(br.readLine());
		System.out.println("\nIntroduce fecha de fin del proyecto: ");
			nuevoProyecto.setFechaInicio(br.readLine());
		System.out.println("\nIntroduce sublínea de acción del proyecto: ");
			nuevoProyecto.setSublineaDeAccion(br.readLine());
		System.out.println("\nIntroduce país del proyecto: ");
			nuevoProyecto.setPais(br.readLine());
		System.out.println("\nIntroduce dirección del proyecto: ");
			nuevoProyecto.setDireccion(br.readLine());
			
		System.out.println("\nIntroduce el dni del socio local: ");
		    try {
	        	String dni = br.readLine();
	        	validarDni(dni);
	        	nuevoProyecto.getSocioLocal().setDni(dni);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Dni no válido, podrá modificarlo más adelante"); 
	        	nuevoProyecto.getSocioLocal().setDni("000000000");
	        }
		System.out.println("\nIntroduce el nombre del socio local: ");
		    nuevoProyecto.getSocioLocal().setNombre(br.readLine());
		System.out.println("\nIntroduce el apellidos del socio local: ");
		
		    nuevoProyecto.getSocioLocal().setApellidos(br.readLine());
		System.out.println("\nIntroduce el apellidos del socio local: ");
		    nuevoProyecto.getSocioLocal().setApellidos(br.readLine());
		System.out.println("\nIntroduce el teléfono del socio locala: ");
	        try {
	        	String numero = br.readLine();
	        	validarNumeroTelefono(numero);
	    		nuevoProyecto.getSocioLocal().setTelefono(numero);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
	        	nuevoProyecto.getSocioLocal().setTelefono("000000000");
	        }
	    System.out.println("\nIntroduce el domicilio del socio local: ");
		    nuevoProyecto.getSocioLocal().setDomicilio(br.readLine());
		System.out.println("\nIntroduce el fecha inicio de colaboración del socio local: ");
		    nuevoProyecto.getSocioLocal().setFechaInicio(br.readLine());
		System.out.println("\nIntroduce el fecha en la que la persona finaliza su colaboraci�n del socio local: ");
		    nuevoProyecto.getSocioLocal().setFechaFin(br.readLine());

			System.out.println("\nIntroduce el id de la sede asignada: ");
			 nuevoProyecto.getSocioLocal().getSedeAsignada().setIdAdmin(br.readLine());
			System.out.println("\nIntroduce la dirección de la sede asignada: ");
			nuevoProyecto.getSocioLocal().getSedeAsignada().setDireccion(br.readLine());
			
			System.out.println("\nIntroduce la dirección de la sede asignada: ");
			nuevoProyecto.getSocioLocal().getSedeAsignada().setNumEmpleados(br.read());
			
			System.out.println("\nIntroduce e-mail de la sede asignada: ");
			nuevoProyecto.getSocioLocal().getSedeAsignada().setCorreo(br.readLine());
			
			System.out.println("\nIntroduce el teléfono de la sede asignada: ");
	        try {
	        	String numero = br.readLine();
	        	validarNumeroTelefono(numero);
	        	nuevoProyecto.getSocioLocal().getSedeAsignada().setTelefono(numero);
	        } catch (TelefonoNoValidoException e) {
	        	System.out.println("Número no válido, podrá modificarlo más adelante"); 
	        	nuevoProyecto.getSocioLocal().getSedeAsignada().setTelefono("000000000");
	        }

		System.out.println("\nIntroduce el cargo del socio local: ");
		    nuevoProyecto.getSocioLocal().setCargo(br.readLine());
		System.out.println("\nIntroduce el correo del socio local: ");
		    nuevoProyecto.getSocioLocal().setCorreo(br.readLine());
		proyectoDAO.crearNuevo(nuevoProyecto);

		System.out.println("\nIntroduce el dni del aportador del proyecto: ");
	    try {
	    	String dni = br.readLine();
	    	validarDni(dni);
	    	nuevoProyecto.getFinanciador().setIdAportador(dni);
	    } catch (TelefonoNoValidoException e) {
	    	System.out.println("Dni no válido, podrá modificarlo más adelante"); 
	    	nuevoProyecto.getFinanciador().setIdAportador("000000000");
	    }
		System.out.println("\nIntroduce el tipo de aportador (I/P/E/H): ");
		switch (br.readLine()) {
			case "I":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.INST);
				break;
	
			case "P":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.PART);
				break;
	
			case "E":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.EMPR);
				break;
			case "H":
				nuevoProyecto.getFinanciador().setTipoAportador(TipoAportador.HEREN);
				break;
	
			default:
				break;
		}

*/
}
