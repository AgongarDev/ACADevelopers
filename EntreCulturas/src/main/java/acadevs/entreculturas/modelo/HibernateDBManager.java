package acadevs.entreculturas.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

public class HibernateDBManager {
	
	/*
	 * Hibernate nos permite utilizar el método saveOrUpdate de manera que en función de si existe o no el objeto en la BD lo inserta o lo actualiza.
	 * */
	public <T> void guardarOActualizar(T t) {
		
		try {
			
			HibernateUtil.abrirSession();
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
				session.beginTransaction();
				session.saveOrUpdate(t);
				session.getTransaction().commit();
			
		} finally {
			HibernateUtil.cerrarSession();
		}
	}
	
	public <T> void borrar(T t) {
		
		try {
			
			HibernateUtil.abrirSession();
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
				session.beginTransaction();
				session.delete(t);
				session.getTransaction().commit();
			
		} finally {
			HibernateUtil.cerrarSession();
		}
	}
	
	public <T> List<T> obtenerTodos(Class<T> tipo) {
		
		try {
			
			HibernateUtil.abrirSession();
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(tipo);
			criteria.from(tipo);
			List<T> datos = session.createQuery(criteria).getResultList();
			
			return datos;
			
			} finally {
			HibernateUtil.cerrarSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T obtenerUno (T t, Serializable id) {
		
		try {
			
			HibernateUtil.abrirSession();
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			T objeto = (T) session.get(t.getClass(), id);
			
			return objeto;
			
			} finally {
			HibernateUtil.cerrarSession();
		}
	
	}
	/*
	 * Une las claves id de la tabla primera y la segunda en una tarcera tabla, siguiendo las reglas de normalización de BD. 
	 * */
	public <T, K> void joinMuchosAMuchos(Class<T> tClass, int idT, Class<K> kClass, int idK) {
		
		try {
			HibernateUtil.abrirSession();
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			session.load(tClass, idT);
			session.load(kClass, idK);
			
		} finally {
			HibernateUtil.cerrarSession();
		}
	}
}