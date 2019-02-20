package lk.ijse.dep.fx.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashbordFormController  implements Initializable {

    @FXML
    private ImageView imgAvailable;
    @FXML
    private ImageView imgCustomer;
    @FXML
    private ImageView imgCar;
    @FXML
    private ImageView ImgRentDetails;
    @FXML
    private ImageView imgPayment;
    @FXML
    private ImageView imgReturn;
    @FXML
    private Label lblMenu;
    @FXML
    private Label lblDescription;
    @FXML
    private AnchorPane root;



    @FXML
    private void navigate(MouseEvent event) throws IOException {

        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch(icon.getId()){
                case "imgCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/CustomerForm.fxml"));
                    break;
                case "imgAvailable":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/AvailableForm.fxml"));
                    break;
                case "imgCar":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/VehicleForm.fxml"));
                    break;
                case "ImgRentDetails":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/RentDetailsForm.fxml"));
                    break;
                case "imgPayment":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/PaymentForm.fxml"));
                    break;
                case "imgReturn":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/ReturnForm.fxml"));
                    break;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            switch(icon.getId()){
                case "imgAvailable":
                    lblMenu.setText("Available Vehicles");
                    lblDescription.setText("Click to select, search or view Available vehicles");
                    break;
                case "imgCustomer":
                    lblMenu.setText("Manage Customer");
                    lblDescription.setText("Click to add, edit, delete, search or view Customer");
                    break;
                case "imgCar":
                    lblMenu.setText("Manage Vehicles");
                    lblDescription.setText("Click to add, edit, delete, search or view Vehicles");
                    break;
                case "ImgRentDetails":
                    lblMenu.setText("Rental Details");
                    lblDescription.setText("Click if you want to view Rental Vehicle  Details");
                    break;
                case "imgPayment":
                    lblMenu.setText("Manage Payment");
                    lblDescription.setText("Click to add payment Details");
                    break;
                case "imgReturn":
                    lblMenu.setText("Manage Return Vehicle");
                    lblDescription.setText("Click to add Return Vehicle Details and check Balance");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }
}
