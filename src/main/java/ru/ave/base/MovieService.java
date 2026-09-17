package ru.ave.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.List;

@Service
public class MovieService {

    private final SessionFactory sessionFactory;

    @Autowired
    public MovieService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Movie saveMovie(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

    public Movie findById(Long id) {
        Session session = sessionFactory.openSession();
        Movie movie = session.find(Movie.class, id);
        session.close();
        return movie;
    }

    public List<Movie> findByGenre(String genre) {
        Session session = sessionFactory.openSession();
        List<Movie> movies = session.createQuery("SELECT m FROM Movie m WHERE m.genre = :m_genre", Movie.class)
                .setParameter("m_genre", genre)
                .getResultList();
        session.close();
        return movies;
    }

    public List<Movie> findAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movies = session.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        session.close();
        return movies;
    }

    public Movie deleteMovie(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Movie movie = session.find(Movie.class, id);
        session.remove(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

    public Movie updateMovie(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        movie = session.merge(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

}
