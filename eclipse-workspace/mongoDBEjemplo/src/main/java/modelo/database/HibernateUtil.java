package modelo.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Patrón de diseño Singleton
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            throw new RuntimeException("Error creando SessionFactory", ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
