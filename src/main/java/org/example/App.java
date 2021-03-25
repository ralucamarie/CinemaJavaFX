package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.domain.Movie;
import org.example.domain.MovieValidator;
import org.example.domain.Reservation;
import org.example.domain.ReservationValidator;
import org.example.repository.IRepository;
import org.example.repository.InMemoryRepository;
import org.example.service.MovieService;
import org.example.service.ReservationService;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        /**scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();**/

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent parentfxml = fxmlLoader.load();
        this.scene = new Scene(parentfxml,640,480);

        IRepository<Movie> movieRepository = new InMemoryRepository();
        IRepository<Reservation> reservationRepository = new InMemoryRepository();

        MovieValidator movieValidator = new MovieValidator();
        ReservationValidator reservationValidator = new ReservationValidator();

        MovieService movieService = new MovieService(movieRepository,movieValidator);
        ReservationService reservationService = new ReservationService(reservationRepository, movieRepository, reservationValidator);

        movieService.addMovie(1, "The Night Before Christmas",2002,25,true);
        movieService.addMovie(2, "Cats and Dogs",2017,25,true);
        movieService.addMovie(3, "Beautiful Stranger at Night",2009,25,true);
        movieService.addMovie(4, "Everything about you",2020,25,true);
        movieService.addMovie(5, "Sailing",2014,25,true);
        movieService.addMovie(6, "Good night, I say to you",2014,25,true);

        PrimaryController primaryController = fxmlLoader.getController();
        primaryController.setServices(movieService,reservationService);

        stage.setTitle("Cinema Manager");
        stage.setScene(this.scene);
        stage.show();


    }

   // static void setRoot(String fxml) throws IOException {
   //     scene.setRoot(loadFXML(fxml));
    //}

    //private static Parent loadFXML(String fxml) throws IOException {
    //    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    //    return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch();
    }

}