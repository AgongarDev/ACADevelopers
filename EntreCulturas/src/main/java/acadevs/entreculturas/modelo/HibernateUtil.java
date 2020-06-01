package acadevs.entreculturas.modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static synchronized void crearSessionFactory() {
		
			if (sessionFactory == null) {
				
				Configuration configuration = new Configuration();
			
				configuration.setProperty("hibernate.current_session_context_class", "thread");
				
				sessionFactory = configuration.configure().buildSessionFactory();
			
		}
	}
	
	public static void abrirSession() {
		
		Session session = sessionFactory.openSession();
		ThreadLocalSessionContext.bind(session);
	}
	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) { 
			crearSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static void cerrarSession() {
		
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		
		if (session != null) {
			session.close();
		}
	}
	
	public static void cerrarSessionFactory() {
		
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}
}
	/* private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder() .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } 
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }*/

