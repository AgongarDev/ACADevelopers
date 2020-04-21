package acadevs.entreculturas.dao.xml;

import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.IAdministracionFisicaDAO;
import acadevs.entreculturas.dao.IDAOListaFactories;

/**
 * Esta clase factoria se usa para crear DAOs que usan
 * XML para el almacenaje de los modelos.
 * 
 * @author Antonio, Cristina y Ana.
 * @version 1.0
 *
 */
public class XMLDAOFactory extends DAOFactory implements IDAOListaFactories<XMLSocioDAO, XMLAdministracionFisicaDAO>{

public XMLDAOFactory () {
	
	this.sociosDAO = null;
	this.administracionesDAO = null;
	
}

@Override
public XMLSocioDAO getSocioDAO() {
	if (sociosDAO == null) {
		this.sociosDAO = new XMLSocioDAO();
	}
	return (XMLSocioDAO) sociosDAO;
}

@Override
public XMLAdministracionFisicaDAO getAdministracionFisicaDAO() {
	if (administracionesDAO == null) {
		this.administracionesDAO = (IAdministracionFisicaDAO) new XMLAdministracionFisicaDAO();
	}
	return (XMLAdministracionFisicaDAO) administracionesDAO;
}

}
