package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import acadevs.entreculturas.modelo.AdministracionFisica;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.IAdministracionFisicaDAO;

public class MySQLAdministracionFisicaDAO implements IAdministracionFisicaDAO {

//SENTENCIAS MYSQL
	final String INSERT = "insert into administraciones (nombre, direccion, telefono, correo, num_empleados) values (?, ?, ?, ?, ?)";
	final String UPDATE = "update administraciones set nombre = ?, direccion = ?, telefono = ?, correo = ?, num_empleados = ? where id_sede = ?";
	final String DELETE = "delete from administraciones where id_sede = ?";
	final String GETALL = "select * from administraciones";
	final String GETUNO = "select * from administraciones where nombre = ?";

//CONEXIÓN
	private Connection conexion;

	public MySQLAdministracionFisicaDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
//MÉTODOS DE DESCONEXIÓN
	private void cierraStat(PreparedStatement stat) throws DAOException{
		
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				throw new DAOException("Error al cerrar la llamada a la tabla Administraciones", e);
			}
		}
	}
	
	private void cierraRs(ResultSet rs) throws DAOException{
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DAOException ("Error al cerrrar el resultado de la llamada a la tabla Administraciones", e);
			}
		}
	}

//MÉTODOS DE UTILIDAD
	private AdministracionFisica convertir(ResultSet rs) throws SQLException {
		
		String nombre = rs.getString("nombre");
		String direccion = rs.getString("direccion");
		int telefono = rs.getInt("telefono");
		String correo = rs.getString("correo");
		short numEmpleados = rs.getShort("num_empleados");
		
		AdministracionFisica sede = new AdministracionFisica (nombre, direccion, telefono, correo, numEmpleados);
		
		sede.setIdAdmin(rs.getInt("id_sede"));
		
		return sede;
		
	}

//MÉTODOS DE ACCESO A DATOS	
	@Override
	public void crearNuevo(AdministracionFisica t) throws DAOException {
		
		//se crea un PreparedStatement en cada método porque la intención es que se cierre al finalizarlo.
		PreparedStatement stat = null; 
		ResultSet rs = null;
		
		try {
			stat = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, t.getNombre());
			stat.setString(2, t.getDireccion());
			stat.setInt(3, t.getTelefono());
			stat.setString(4, t.getCorreo());
			stat.setShort(5, (short) t.getNumEmpleados());
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("Hubo algún problema al intentar la llamada insert a la tabla Administraciones");
			}
			
			rs = stat.getGeneratedKeys();
			
			if (rs.next()) {
				t.setIdAdmin(rs.getInt(1));
			} else {
				throw new DAOException("Hubo algún problema para recuperar el ID de la administración");
			}
		} catch (SQLException e) {
			throw new DAOException("Error al intentar guardar datos en la tabla Administraciones", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
	}
		
	@Override 
	public void actualizar(AdministracionFisica t) throws DAOException {
	
		/*La actualización se hará en otra clase para mantener ésta únicamente como enlace de accesoa la base de datos.*/
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(UPDATE);
			stat.setString(1, t.getNombre());
			stat.setString(2, t.getDireccion());
			stat.setInt(3, t.getTelefono());
			stat.setString(4, t.getCorreo());
			stat.setShort(5, (short) t.getNumEmpleados());
			
		} catch (SQLException e) {
			throw new DAOException("Hubo un problema en la actualización del dato de la tabla Administraciones", e);
		} finally {
			cierraStat(stat);
		}
	}

	@Override
	public void borrar(AdministracionFisica t) throws DAOException{
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(DELETE);
			stat.setLong(1, t.getIdAdmin());
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("No se ha encontrado este registro");
			};
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Administraciones", e);
		} finally {
			cierraStat(stat);
		}
	}

	@Override
	public List<AdministracionFisica> obtenerTodos() throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<AdministracionFisica> administraciones = new ArrayList<>();
		
		try {
			stat = conexion.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				administraciones.add(convertir(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Administraciones", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
		return administraciones;
	}
	
	@Override
	public AdministracionFisica obtener(String nombre) throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		AdministracionFisica sede = null;
		
		try {
			stat = conexion.prepareStatement(GETUNO);
			stat.setString(1, nombre);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				sede = convertir(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Administraciones", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
		return sede;
	}
	
	public AdministracionFisica obtener(Integer id) throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		AdministracionFisica sede = null;
		
		try {
			stat = conexion.prepareStatement(GETUNO);
			stat.setLong(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				sede = convertir(rs);
			} else {
				throw new DAOException("No se ha encontrado este registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Administraciones", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
		return sede;
	}
}
		