package ru.ave.base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {

    static void main() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        MovieService movieService = context.getBean(MovieService.class);

        fillMovies().forEach(movieService::saveMovie);
        System.out.println(movieService.findAll());
        System.out.println(movieService.findByGenre("Драма"));
        System.out.println(movieService.findById(1L));
        movieService.deleteMovie(3L);
        System.out.println(movieService.findAll());

    }

    private static List<Movie> fillMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Побег из Шоушенка", "Драма", 1994));
        movies.add(new Movie("Крёстный отец", "Криминал", 1972));
        movies.add(new Movie("Тёмный рыцарь", "Боевик", 2008));
        movies.add(new Movie("Криминальное чтиво", "Криминал", 1994));
        movies.add(new Movie("Форрест Гамп", "Драма", 1994));
        movies.add(new Movie("Начало", "Фантастика", 2010));
        movies.add(new Movie("Матрица", "Фантастика", 1999));
        movies.add(new Movie("Интерстеллар", "Фантастика", 2014));
        movies.add(new Movie("Бойцовский клуб", "Драма", 1999));
        movies.add(new Movie("Гладиатор", "Боевик", 2000));
        return movies;
    }


}
