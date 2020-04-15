package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.SocioDAO;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.soporte.TipoCuota;

public class MySQLSocioDAO implements SocioDAO {

//SENTENCIAS MYSQL
	final String INSERT = "insert into socios (dni, nombre, apellido, direccion, telefono, fecha_inicio, fecha_fin, cargo, correo, cuota, estado, pass, tipo_cuota, sede) "
			+ "values (?, ?, ?, ?, ?, ? ,? ,? ,?, ?, ?, ?, ?, ?)";
	final String UPDATE = "update socios set dni = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, fecha_inicio = ?, fecha_fin = ?, cargo = ?, correo = ?, "
			+ "cuota = ?, estado = ?, pass = ?, tipo_cuota = ?, sede = ? where id_socio = ?";
	final String DELETE = "delete from socios where id_socio = ?";
	final String GETALL = "select * from socios";
	final String GETUNO = "select * from socios where id_socio = ?";

//CONEXIÓN
		private Connection conexion;
		private PreparedStatement stat = null;
		private ResultSet rs = null;

		public MySQLSocioDAO (Connection conexion) {
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
		private Socio convertir(ResultSet rs) throws SQLException {
			
			String dni = rs.getString("dni");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String direccion = rs.getString("direccion");
			Integer telefono = rs.getInt("telefono");
			Date fechaIni = new Date(rs.getDate("fecha_ini").getTime());
			Date fechaFin = new Date(rs.getDate("fecha_fin").getTime());
			String cargo = rs.getString("cargo");
			String correo = rs.getString("correo");
			float cuota = rs.getFloat("cuota");
			boolean estado = rs.getBoolean("estado");
			String pass = rs.getString("pass");
			TipoCuota tipoCuota = TipoCuota.valueOf(rs.getString("tipo_cuota"));
			Long sede = rs.getLong("sede");
			
			
			Socio socio = new Socio (dni, nombre, apellido, direccion, telefono, fechaIni, fechaFin, cargo, correo, cuota, estado, pass, tipoCuota, sede);
			
			socio.setId(rs.getLong("id_sede"));
			
		return socio;
		
	}

//MÉTODOS DE ACCESO A DATOS	
	@Override
	public void crearNuevo(Socio t) throws DAOException {
		
		//se crea un PreparedStatement en cada método porque la intención es que se cierre al finalizarlo.
		PreparedStatement stat = null; 
		ResultSet rs = null;
		
		try {
			stat = conexion.prepareStatement(INSERT);
			stat.setString(1,  t.getDni());
			stat.setString(2, t.getNombre());
			stat.setString(3, t.getApellidos());
			stat.setString(4, t.getDomicilio());
			stat.setInt(5, t.getTelefono());
			stat.setDate(6, (java.sql.Date) t.getFechaInicio());
			stat.setDate(7, (java.sql.Date) t.getFechaFin());
			stat.setString(8,  t.getCargo());
			stat.setString(9, t.getCorreo());
			stat.setFloat(10,  t.getCuotaAportacion());
			stat.setBoolean(11, t.getEstadoAportacion());
			stat.setString(12, t.getPass()); // la contraseña debería subirse encriptada.
			stat.setString(13, t.getTipoCuota().getTexto());
			stat.setLong(14, t.getSedeAsignada());
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("Hubo algún problema al intentar la llamada insert a la tabla Socios");
			}
			
			rs = stat.getGeneratedKeys();
			
			if (rs.next()) {
				t.setId(rs.getLong(1));
			} else {
				throw new DAOException("Hubo algún problema para recuperar el ID del socio");
			}
		} catch (SQLException e) {
			throw new DAOException("Error al intentar guardar datos en la tabla Socios", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
	}

	@Override
	public Socio obtener(Long id) throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		Socio socio = null;
		
		try {
			stat = conexion.prepareStatement(GETUNO);
			stat.setLong(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				socio = convertir(rs);
			} else {
				throw new DAOException("No se ha encontrado este registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Socios", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
		return socio;
	}

	
	@Override	
	public void actualizar(Socio t) throws DAOException {
		
		/*La actualización se hará en otra clase para mantener ésta únicamente como enlace de accesoa la base de datos.*/
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(UPDATE);
			stat.setString(1,  t.getDni());
			stat.setString(2, t.getNombre());
			stat.setString(3, t.getApellidos());
			stat.setString(4, t.getDomicilio());
			stat.setInt(5, t.getTelefono());
			stat.setDate(6, (java.sql.Date) t.getFechaInicio());
			stat.setDate(7, (java.sql.Date) t.getFechaFin());
			stat.setString(8,  t.getCargo());
			stat.setString(9, t.getCorreo());
			stat.setFloat(10,  t.getCuotaAportacion());
			stat.setBoolean(11, t.getEstadoAportacion());
			stat.setString(12, t.getPass()); // la contraseña debería subirse encriptada.
			stat.setString(13, t.getTipoCuota().getTexto());
			stat.setLong(14, t.getSedeAsignada());
			
		} catch (SQLException e) {
			throw new DAOException("Hubo un problema en la actualización del dato de la tabla Socios", e);
		} finally {
			cierraStat(stat);
		}
	}

	
	@Override
	public void borrar(Socio t) throws DAOException {
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(DELETE);
			stat.setLong(1, t.getId());
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("No se ha encontrado este registro");
			};
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Socios", e);
		} finally {
			cierraStat(stat);
		}
	}

	
	@Override
	public List<Socio> obtenerTodos() throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Socio> socios = new ArrayList<>();
		
		try {
			stat = conexion.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				socios.add(convertir(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Socios", e);
		} finally {
			cierraRs(rs);
			cierraStat(stat);
		}
		return socios;
	}
}
