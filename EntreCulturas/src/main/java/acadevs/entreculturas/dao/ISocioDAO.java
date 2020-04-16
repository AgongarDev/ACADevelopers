package acadevs.entreculturas.dao;

import acadevs.entreculturas.modelo.Socio;
/**
 * 
 * @author Ana, Cristina, Antonio
 * Plantilla de acceso a socios mediante el patrón DAO. 
 * Extiende con un parámetro de clase Socio y un String que servirá para obtener un solo por DNI (atributo del tipo String)
 */
public interface ISocioDAO extends IDAO<Socio, String> {
	
}
