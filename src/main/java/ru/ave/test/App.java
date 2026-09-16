package ru.ave.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    static void main() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        SessionFactory factory = context.getBean(SessionFactory.class);

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user1= new User("alex");
            User user2= new User("mike");
            session.persist(user1);
            session.persist(user2);
            transaction.commit();
            System.out.println(session.createQuery("FROM User u WHERE u.id = 1", User.class).getResultList());

            transaction = session.beginTransaction();
            User user3 = session.find(User.class, 1L);
            System.out.println(user3);
            user3.setName("july");
            transaction.commit();

            System.out.println(session.createQuery("FROM User u WHERE u.id = 1", User.class).getResultList());

            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id = 1", User.class).executeUpdate();
            transaction.commit();

            System.out.println(session.createQuery("FROM User u WHERE u.id = 1", User.class).getResultList());

//            transaction = session.beginTransaction();
            System.out.println(session.createQuery("SELECT u FROM User u WHERE u.id = :test_id", User.class)
                    .setParameter("test_id", 2).getResultList());


//            transaction.commit();

        }




    }
}
