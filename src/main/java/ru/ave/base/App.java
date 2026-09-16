package ru.ave.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    static void main() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        SessionFactory factory = context.getBean(SessionFactory.class);

        try (Session session = factory.openSession()) {

            Transaction transaction = session.beginTransaction();
            Movie movie1 = new Movie("Побег из Шоушенка", "Драма", 1994);
            Movie movie2 = new Movie("Крёстный отец", "Криминал", 1972);
            Movie movie3 = new Movie("Тёмный рыцарь", "Боевик", 2008);
            Movie movie4 = new Movie("Криминальное чтиво", "Криминал", 1994);
            Movie movie5 = new Movie("Форрест Гамп", "Драма", 1994);
            Movie movie6 = new Movie("Начало", "Фантастика", 2010);
            Movie movie7 = new Movie("Матрица", "Фантастика", 1999);
            Movie movie8 = new Movie("Интерстеллар", "Фантастика", 2014);
            Movie movie9 = new Movie("Бойцовский клуб", "Драма", 1999);
            Movie movie10 = new Movie("Гладиатор", "Боевик", 2000);
            session.persist(movie1);
            session.persist(movie2);
            session.persist(movie3);
            session.persist(movie4);
            session.persist(movie5);
            session.persist(movie6);
            session.persist(movie7);
            session.persist(movie8);
            session.persist(movie9);
            session.persist(movie10);
            transaction.commit();

            List<Movie> movies = session.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
            System.out.println(movies);

            List<Movie> moviesByGenre = session.createQuery("SELECT m FROM Movie m WHERE m.genre = :genre_drama",
                    Movie.class)
                    .setParameter("genre_drama", "Драма")
                    .getResultList();
            System.out.println(moviesByGenre);

            transaction = session.beginTransaction();
            Movie movie = session.find(Movie.class, 1);
            movie.setTitle("Escape from Shoushenk");
            transaction.commit();


            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Movie m WHERE m.id = :id_2")
                    .setParameter("id_2", 2)
                            .executeUpdate();
            transaction.commit();

            transaction = session.beginTransaction();
            Movie movieForDelete = session.find(Movie.class, 3);
            session.remove(movieForDelete);
            transaction.commit();

            List<Movie> moviesAfterUpdate = session.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
            System.out.println(moviesAfterUpdate);



        }

    }

}
