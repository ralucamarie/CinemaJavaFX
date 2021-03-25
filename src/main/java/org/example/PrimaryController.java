package org.example;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.domain.Movie;
import org.example.service.MovieService;
import org.example.service.ReservationService;

public class PrimaryController {
    public TextField txtMovieId;
    public TextField txtTitle;
    public TextField txtYear;
    public TextField txtTicketPrice;
    public CheckBox chkRunning;
    public Button btnAddMovie;
    public TableView movieTable;
    public TableColumn colMovieId;
    public TableColumn colMovieTitle;
    public TableColumn colYear;
    public TableColumn colTicketPrice;
    public TableColumn colRunning;
    
    private ReservationService reservationService;
    private MovieService movieService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    
    @FXML
    private void initialize(){

        Platform.runLater(() ->{
           movies.addAll(movieService.readAll());
           movieTable.setItems(movies);
        });
    }

    public void setServices (MovieService movieService, ReservationService reservationService){
        this.movieService = movieService;
        this.reservationService=reservationService;
    }

    public void btnAddMovieClick(ActionEvent actionEvent) throws Exception {
        try {
            int id = Integer.parseInt(txtMovieId.getText());
            String title = txtTitle.getText();
            int year = Integer.parseInt(txtYear.getText());
            int ticketPrice = Integer.parseInt(txtTicketPrice.getText());
            boolean running = chkRunning.isSelected();

            movieService.addMovie(id,title,year,ticketPrice,running);

            refreshMovies();

        } catch (RuntimeException rex) {
            // TODO: Common.showValidationError(rex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Validation error:");
            alert.setContentText(rex.getMessage());
            alert.showAndWait();
        }
    }

    private void refreshMovies() {
        movies.clear();
        movies.addAll(movieService.readAll());
    }


    /** @FXML
    private void switchToMovies() throws IOException {
        App.setRoot("movies");
    }
    @FXML
    private void switchToReservations() throws IOException {
        App.setRoot("reservations");
    }**/
}
