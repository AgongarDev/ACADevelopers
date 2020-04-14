package acadevs.entreculturas.dao.mysql;

import java.util.List;

import acadevs.entreculturas.dao.SocioDAO;
import acadevs.entreculturas.modelo.Socio;

public class MySQLSocioDAO implements SocioDAO {

	//SENTENCIAS MYSQL
	final String INSERT = "insert into socios (dni, nombre, apellido, direccion, telefono, fecha_inicio, fecha_fin, cargo, correo, cuota, estado, pass, tipo_cuota, sede) "
			+ "values (?, ?, ?, ?, ?, ? ,? ,? ,?, ?, ?, ?, ?, ?)";
	final String UPDATE = "update socios set ? = ? where id_socio = ?";
	final String DELETE = "delete from socios where id_socio = ?";
	final String GETALL = "select * from socios";
	final String GETADMIN = "select * from socios where id_socio = ?";
	
	@Override
	public void crearNuevo(Socio t) {
		
	}

	@Override
	public Socio obtener(Long id) {
		return null;
	}

	@Override
	public void actualizar(Socio t) {
		
	}

	@Override
	public void borrar(Socio t) {
		
	}

	@Override
	public List<Socio> obtenerTodos() {
		return null;
	}
	

}
