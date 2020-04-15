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
	final String UPDATE = "update socios set ? = ? where id_socio = ?";
	final String DELETE = "delete from socios where id_socio = ?";
	final String GETALL = "select * from socios";
	final String GETADMIN = "select * from socios where id_socio = ?";

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
			
			
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("Hubo algún problema al intentar la llamada insert a la tabla Administraciones");
			}
			
			rs = stat.getGeneratedKeys();
			
			if (rs.next()) {
				t.setIdAdmin(rs.getLong(1));
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
	public Socio obtener(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Socio t) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Socio t) throws DAOException {
		// TODO Auto-generated method stub
		
	}
}
