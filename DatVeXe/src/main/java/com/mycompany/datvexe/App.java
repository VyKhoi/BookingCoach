package com.mycompany.datvexe;

import com.bookingCoach.services.ChangeTicketServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override

    public void start(Stage stage) throws IOException, SQLException {
        scene = new Scene(loadFXML("BookTicKet"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(900);
        stage.setMinHeight(480);
        stage.show();
        
        // chạy cập nhật vé
//        ChangeTicketServices.autoUpdateTicket();
//        ChangeTicketServices.autoUpdateCSCS();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
