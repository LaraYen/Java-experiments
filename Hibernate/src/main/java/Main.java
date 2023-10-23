import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hqlQuery = "select c.id as courseId, s.id as studentId from PurchaseList pl, Course c, Student s where pl.courseName = c.name and pl.studentName = s.name";
        List<Object[]> res = session.createQuery(hqlQuery).getResultList();

        res.forEach(obj ->
                session.save(
                        new LinkedPurchaseList(new LinkedPurchaseListKey((int) obj[0], (int) obj[1]),
                        (int) obj[0], (int) obj[1]))
        );

        transaction.commit();
        sessionFactory.close();
    }
}
