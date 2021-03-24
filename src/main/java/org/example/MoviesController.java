package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class MoviesController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}