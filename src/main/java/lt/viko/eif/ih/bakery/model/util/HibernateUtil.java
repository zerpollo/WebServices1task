package lt.viko.eif.ih.bakery.model.util;
import lt.viko.eif.ih.bakery.model.Bakery;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

/** This class is used to create connection, send and receive xml from mysql database, also there is a method how registry shutdown.
 * This class possess needed variable for registry and session factory.
 * The method sendToDatabase creates new session, then the transaction with bakery object is created and committed.
 * The method receiveFromDatabase creates new session, then the query for mysql database is created, after that,
 * jaxb marshaller is created, new file is created with the "bakery.xml" name. The resulting data is then written into the file
 * using jaxb marshaller. Finally, the success message is printed if the operation is successful.
 */
public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void sendToDatabase(Bakery bakery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bakery);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void receiveFromDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Bakery> bakeryList = session.createQuery("from Bakery", Bakery.class).list();
            for (Bakery bakery : bakeryList) {
                JAXBoperations.marshallBakeryToXML(bakery);
            }
            System.out.println("Bakery object received and successfully printed to console marshalled as XML");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
