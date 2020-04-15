package acadevs.entreculturas.dao;

import java.util.List;

import acadevs.entreculturas.modelo.Socio;

public interface SocioDAO extends DAO<Socio, Long> {

	@Override
	void crearNuevo(Socio t) throws DAOException;

	@Override
	Socio obtener(Long id) throws DAOException;

	@Override
	void actualizar(Socio t) throws DAOException;

	@Override
	void borrar(Socio t) throws DAOException;

	@Override
	List<Socio> obtenerTodos() throws DAOException;

	
	
}
