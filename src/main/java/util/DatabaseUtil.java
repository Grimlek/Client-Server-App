package util;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

/**
 * Created by csexton on 7/16/16.
 */
public class DatabaseUtil {

    private static EntityManagerFactory sessionFactory = buildSessionFactory();

    private static EntityManagerFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = Persistence.createEntityManagerFactory("client-server-app");
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
