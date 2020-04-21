package acadevs.entreculturas.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import acadevs.entreculturas.dao.DAOException;
import acadevs.entreculturas.dao.DAOFactory;
import acadevs.entreculturas.dao.ISocioDAO;
import acadevs.entreculturas.enums.TipoCuota;
import acadevs.entreculturas.modelo.AdministracionFisica;
import acadevs.entreculturas.modelo.Socio;
import acadevs.entreculturas.util.Utilidad;

public class MySQLSocioDAO implements ISocioDAO {

//SENTENCIAS MYSQL
	final String INSERT_CONTACTO = "insert into contactos "
			+ "(dni,"
			+ " nombre,"
			+ " apellido,"
			+ " direccion,"
			+ " telefono,"
			+ " fecha_inicio,"
			+ " fecha_fin,"
			+ " cargo,"
			+ " correo,"
			+ " pass,"
			+ " sede)"
			+ "values (?, ?, ?, ?, ?, ? ,? ,? ,?, ?, ?)";
	
	final String INSERT_SOCIO = "insert into socios "
			+ "(cuota,"
			+ " estado,"
			+ " tipo_cuota, "
			+ "id_socio)"
			+ " values (?, ?, ?, ?)";
	
	final String UPDATE_CONTACTO = "update contactos "
			+ "set "
			+ "dni = ?, "
			+ "nombre = ?, "
			+ "apellido = ?, "
			+ "direccion = ?, "
			+ "telefono = ?, "
			+ "fecha_inicio = ?,"
			+ " fecha_fin = ?, "
			+ "cargo = ?,"
			+ " correo = ?,"
			+ " pass = ?,"
			+ " sede = ?"
			+ " where contactos.id_contacto = ?";
	
	final String UPDATE_SOCIO = "update socios "
			+ " set "
			+ "cuota = ?, "
			+ "estado = ?, "
			+ "tipo_cuota = ?"
			+ " where socios.id_socio = ?";
	
	final String DELETE = "delete contactos, socios "
			+ "from contactos "
			+ "inner join socios"
			+ " on contactos.id_contacto = socios.id_socio"
			+ " where id_contacto = ?";
	
	final String GETALL = "select * from contactos "
	    	+ "inner join socios"
	    	+ " on contactos.id_contacto = socios.id_socio";
	
	final String GETUNO = "select * from contactos "
			+ "inner join socios"
			+ " on contactos.id_contacto = socios.id_socio"
			+ " where contactos.dni = ?";

//CONEXIÓN
		private Connection conexion;

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
		private Socio convertir(ResultSet rs) throws SQLException, DAOException {
			
			String dni = rs.getString("dni");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String direccion = rs.getString("direccion");
			Integer telefono = rs.getInt("telefono");
			Date fechaIni = Utilidad.conversorFecha(rs.getDate("fecha_inicio"));
			Date fechaFin = Utilidad.conversorFecha(rs.getDate("fecha_fin"));
			String cargo = rs.getString("cargo");
			String correo = rs.getString("correo");
			float cuota = rs.getFloat("cuota");
			boolean estado = rs.getBoolean("estado");
			String pass = rs.getString("pass");
			TipoCuota tipoCuota = TipoCuota.valueOf(rs.getString("tipo_cuota"));
			
			MySQLDAOFactory mysqlF = (MySQLDAOFactory) DAOFactory.getDAOFactory("MySQL");
 	   		MySQLAdministracionFisicaDAO administraciones = mysqlF.getAdministracionFisicaDAO();
			
			AdministracionFisica sede = administraciones.obtener(rs.getInt("sede")); 
			
			Socio socio = new Socio (dni, nombre, apellido, direccion, telefono, fechaIni, fechaFin, cargo, correo, cuota, estado, pass, tipoCuota, sede);
			
			socio.setId(rs.getInt("id_socio"));
			
		return socio;
		
	}

//MÉTODOS DE ACCESO A DATOS	
	@Override
	public void crearNuevo(Socio t) throws DAOException {
		
		//se crea un PreparedStatement en cada método porque la intención es que se cierre al finalizarlo.
		PreparedStatement statContacto = null;
		ResultSet rs = null;
		
		Utilidad.activaAutoCommit(conexion, false);
		
		try {
			statContacto = conexion.prepareStatement(INSERT_CONTACTO, Statement.RETURN_GENERATED_KEYS);
			statContacto.setString(1,  t.getDni());
			statContacto.setString(2, t.getNombre());
			statContacto.setString(3, t.getApellidos());
			statContacto.setString(4, t.getDomicilio());
			statContacto.setInt(5, t.getTelefono());
			statContacto.setDate(6, Utilidad.conversorFecha(t.getFechaInicio()));  
			statContacto.setDate(7, Utilidad.conversorFecha(t.getFechaFin()));
			statContacto.setString(8,  t.getCargo());
			statContacto.setString(9, t.getCorreo());
			statContacto.setString(10, t.getPass()); // la contraseña debería subirse encriptada.
			statContacto.setInt(11, t.getSedeAsignada().getIdAdmin());
			
			if (statContacto.executeUpdate() == 0) {
				throw new DAOException("Hubo algún problema al intentar la llamada insert a la tabla Contactos");
			}
			
			rs = statContacto.getGeneratedKeys();
			
			if (rs.next()) {
				t.setId(rs.getInt(1));
			} else {
				throw new DAOException("Hubo un problema al recuperar el ID del contacto en la inserción de socios");
				}
			
			datosEspecificosSocio(t, INSERT_SOCIO);
			
			conexion.commit();
		} catch (SQLException e) {
			throw new DAOException("Error al intentar guardar datos en las tablas Contactos y/o Socios", e);
		} finally {
			cierraRs(rs);
			cierraStat(statContacto);
		}
	
		Utilidad.activaAutoCommit(conexion, true);
	}

	@Override
	public Socio obtener(String id) throws DAOException {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		Socio socio = null;
		
		try {
			stat = conexion.prepareStatement(GETUNO);
			stat.setString(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				socio = convertir(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Socios", e);
			} finally {
				cierraRs(rs);
				cierraStat(stat);
				}
		return socio;
	}
	
	/**
	 * Actualiza todos los datos de socio en las tablas de contactos y en la de socios de manera que todos los datos del socio existentes en nuestra BD pasarán a ser subsistuidos por 
	 * los nuevos valores
	 */
	@Override	
	public void actualizar(Socio t) throws DAOException {
		
		/*La actualización se hará en otra clase para mantener ésta únicamente como enlace de accesoa la base de datos.*/
		PreparedStatement stat = null;
		
		Utilidad.activaAutoCommit(conexion, false);

		try {
			stat = conexion.prepareStatement(UPDATE_CONTACTO);
			stat.setString(1,  t.getDni());
			stat.setString(2, t.getNombre());
			stat.setString(3, t.getApellidos());
			stat.setString(4, t.getDomicilio());
			stat.setInt(5, t.getTelefono());
			stat.setDate(6, Utilidad.conversorFecha(t.getFechaInicio()));
			stat.setDate(7, Utilidad.conversorFecha(t.getFechaFin()));
			stat.setString(8,  t.getCargo());
			stat.setString(9, t.getCorreo());
			stat.setString(10, t.getPass()); // la contraseña debería subirse encriptada.
			stat.setLong(11, t.getSedeAsignada().getIdAdmin());
			stat.setInt(12, t.getId());
			
			if (stat.executeUpdate() == 0) {
				throw new DAOException("Error en la actualización de los datos de contacto");
			};
			
			datosEspecificosSocio(t, UPDATE_SOCIO);
			
			conexion.commit();
			
		} catch (SQLException e) {
			throw new DAOException("Hubo un problema en la actualización del dato de la tabla Socios", e);
		} finally {
			cierraStat(stat);
		}
		
		Utilidad.activaAutoCommit (conexion, true);
		
	}

	public void datosEspecificosSocio(Socio t, String ORDEN) throws DAOException {
		
		if (ORDEN.equals("UPDATE_SOCIO")) {// String
			ORDEN = UPDATE_SOCIO; // Constante con sentencia SQL
		}
		/*La actualización se hará en otra clase para mantener ésta únicamente como enlace de accesoa la base de datos.*/
		PreparedStatement stat = null;
		
		try {
			stat = conexion.prepareStatement(ORDEN);
			stat.setFloat(1,  t.getCuotaAportacion()); 
			stat.setBoolean(2, t.getEstadoAportacion()); 
			stat.setString(3, t.getTipoCuota().name()); 
			stat.setInt(4, t.getId());
			stat.executeUpdate();
			
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
				System.out.println("No se ha encontrado al socio con el dni "+t.getDni());	
			};
		} catch (SQLException e) {
			throw new DAOException("Error al acceder al registro de Socios", e);
		} finally {
			cierraStat(stat);
			System.out.println("El socio "+t.getNombre()+" "+t.getApellidos()+" ha sido eliminado de la base de datos.");
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
