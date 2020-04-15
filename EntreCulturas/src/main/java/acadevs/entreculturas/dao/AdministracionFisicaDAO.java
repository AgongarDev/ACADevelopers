package acadevs.entreculturas.dao;

import java.util.List;

import acadevs.entreculturas.modelo.AdministracionFisica;

public interface AdministracionFisicaDAO extends DAO<AdministracionFisica, Long> {

	@Override
	void crearNuevo(AdministracionFisica t)  throws DAOException;

	@Override
	AdministracionFisica obtener(Long id) throws DAOException;

	@Override
	void actualizar(AdministracionFisica t) throws DAOException;

	@Override
	void borrar(AdministracionFisica t) throws DAOException;

	@Override
	List<AdministracionFisica> obtenerTodos() throws DAOException;
	
	
}
