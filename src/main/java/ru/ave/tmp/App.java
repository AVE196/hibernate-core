package ru.ave.tmp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    static void main() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SessionFactory factory = context.getBean(SessionFactory.class);

        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(new User("alex"));
        session.persist(new User("mike"));
        session.persist(new User("july"));
        session.persist(new User("alex"));
        session.getTransaction().commit();

        User user = session.find(User.class, 2L);
        System.out.println(user);

        List<User> resultList = session.createQuery("SELECT u FROM users u WHERE u.name = :u", User.class)
                .setParameter("u", "alex")
                .getResultList();

        resultList.forEach(System.out::println);

        session.close();

        session = factory.openSession();
        session.beginTransaction();
//        session.remove(user);
        user = session.merge(user);
        user.setName("2131341");

        session.getTransaction().commit();

        session.close();

    }

}
