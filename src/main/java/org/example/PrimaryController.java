package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.domain.Movie;
import org.example.domain.Reservation;
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

    public TableView reservationTable;
    public TableColumn colResId;
    public TableColumn colMovieIdRes;
    public TableColumn colCustomerCard;
    public TableColumn colDate;
    public TextField txtReservationId;
    public TextField txtMovieResId;
    public TextField txtCustomerCard;
    public TextField txtDate;
    
    private ReservationService reservationService;
    private MovieService movieService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    @FXML
    private void initialize(){

        Platform.runLater(() ->{
           movies.addAll(movieService.readAll());
           movieTable.setItems(movies);
           reservations.addAll(reservationService.readAll());
          reservationTable.setItems(reservations);
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
    public void btnDeleteSelectedMovieClick(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = (Movie)movieTable.getSelectionModel().getSelectedItem();
        if (selectedMovie!=null){
            this.movieService.deleteMovie(selectedMovie.getId(),selectedMovie.getTitle(),selectedMovie.getYear(),selectedMovie.getTicketPrice(),selectedMovie.isRunning());
            refreshMovies();
        }
    }
    public void btnUpdateMovieClick(ActionEvent actionEvent) throws Exception{
        try {
            int id = Integer.parseInt(txtMovieId.getText());
            String title = txtTitle.getText();
            int year = Integer.parseInt(txtYear.getText());
            int ticketPrice = Integer.parseInt(txtTicketPrice.getText());
            boolean running = chkRunning.isSelected();

            movieService.updateMovie(id, title, year, ticketPrice, running);

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
    private void refreshReservations() {
       reservations.clear();
       reservations.addAll(reservationService.readAll());
    }

    public void btnAddReservationClick(ActionEvent actionEvent) throws Exception{
        try {
            int id = Integer.parseInt(txtReservationId.getText());
            int reservationMovieId = Integer.parseInt(txtMovieResId.getText());
            int customerCard = Integer.parseInt(txtCustomerCard.getText());
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse((CharSequence) txtDate, formatter);


            reservationService.addReservation(id,reservationMovieId,customerCard,dateTime);

            refreshReservations();

        } catch (Exception rex) {
            // TODO: Common.showValidationError(rex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Validation error:");
            alert.setContentText(rex.getMessage());
            alert.showAndWait();
        }
    }

    public void btnUpdateReservationClick(ActionEvent actionEvent) {
    }

    public void btnDeleteSelectedReservationClick(ActionEvent actionEvent) {
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
