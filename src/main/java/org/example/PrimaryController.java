package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToMovies() throws IOException {
        App.setRoot("movies");
    }
    @FXML
    private void switchToReservations() throws IOException {
        App.setRoot("reservations");
    }
}
