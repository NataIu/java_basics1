
import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        refillLinkedPurchase(session);

        sessionFactory.close();
    }

    private static void refillLinkedPurchase(Session session) {
        Transaction txn = session.beginTransaction();

        //почистим таблицу, если уже была заполнена
        session.createQuery("DELETE " + LinkedPurchase.class.getSimpleName()).executeUpdate();

        //заполним таблицу
        String hqlIns = "in" +
                "sert into "+ LinkedPurchase.class.getSimpleName() +
                " (studentId, courseId) "+
                "select student.id, course.id from "+ Purchase.class.getSimpleName();

        int rows = session.createQuery(hqlIns).executeUpdate();

        System.out.println("rows: "+ rows);
        txn.commit();
    }
}
