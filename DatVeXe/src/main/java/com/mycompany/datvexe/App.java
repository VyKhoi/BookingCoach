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

        try {
//        scene = new Scene(loadFXML("BookTicKet"));

            scene = new Scene(loadFXML("LoginGUI"));

            stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);

            stage.setResizable(false);
            stage.setMinWidth(900);
            stage.setMinHeight(480);
            stage.show();

            ChangeTicketServices.autoUpdateTicket();
            ChangeTicketServices.autoUpdateCSCS();
//           
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
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
