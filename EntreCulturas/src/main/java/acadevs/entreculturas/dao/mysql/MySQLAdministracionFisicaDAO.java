package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import acadevs.entreculturas.dao.AdministracionFisicaDAO;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.dao.DAOException;

public class MySQLAdministracionFisicaDAO implements AdministracionFisicaDAO {

	//SENTENCIAS MYSQL
	final String INSERT = "insert into administraciones (nombre, direccion, telefono, correo, num_empleados) values (?, ?, ?, ?, ?)";
	final String UPDATE = "update administraciones set ? = ? where id_sede = ?";
	final String DELETE = "delete from administraciones where id_sede = ?";
	final String GETALL = "select * from administraciones";
	final String GETADMIN = "select * from administraciones where id_sede = ?";
	
	private Connection conexion;
	
	public MySQLAdministracionFisicaDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
	@Override
	public void crearNuevo(AdministracionFisica t) throws DAOException {
		
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(INSERT);
			stat.setString(1, t.getNombre());
			stat.setString(2, t.getDireccion());
			stat.setInt(3, t.getTelefono());
			stat.setString(4, t.getCorreo());
			stat.setInt(5, t.getNumEmpleados());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("Hubo algún problema al intentar la llamada insert a la tabla Administraciones");
			};
		} catch (SQLException e) {
			throw new DAOException("Error al intentar guardar datos en la tabla Administraciones", e);
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					throw new DAOException("Error al cerrar la llamada a la tabla Administraciones", e);
				}
			}
		}
	}

	private AdministracionFisica convertir(ResultSet rs) throws SQLException {
		String nombre = rs.getString("nombre");
		String direccion = rs.getString("direccion");
		int telefono = rs.getInt("telefono");
		
		return null;
		
	}

	@Override
	public void actualizar(AdministracionFisica t) {
		
	}

	@Override
	public void borrar(AdministracionFisica t) {

		
	}

	@Override
	public List<AdministracionFisica> obtenerTodos() {

		return null;
	}
	
	@Override
	public AdministracionFisica obtener(Long id) {

		return null;
	}
}
		