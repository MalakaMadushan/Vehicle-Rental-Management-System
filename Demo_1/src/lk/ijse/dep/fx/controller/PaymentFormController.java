package lk.ijse.dep.fx.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lk.ijse.dep.fx.business.BOFactory;
import lk.ijse.dep.fx.business.custom.ManagePaymentBO;
import lk.ijse.dep.fx.business.custom.ManageRentBO;
import lk.ijse.dep.fx.business.custom.ManageVehicleBO;
import lk.ijse.dep.fx.dto.PaymentDTO;
import lk.ijse.dep.fx.dto.RentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentFormController {
    @FXML
    private Button btnOK;
    @FXML
    private Button btnPayment;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtAdvance;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtPaymentID;

    @FXML
    private JFXDatePicker txtRentDate;
    @FXML
    private JFXDatePicker txtReturnDate;
    @FXML
    private JFXTextField txtDuration;
    @FXML
    private JFXTextField txtRentalAmount;
    @FXML
    private JFXTextField txtBalance;

    private String NIC;
    private String VNum;

    public static PaymentFormController paymentFormController;

    private ManagePaymentBO managePaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEPAYMENT);
    private ManageRentBO manageRentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGERENT);
    private ManageVehicleBO manageVehicleBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEVEHICLE);

    public void initialize(){
        try {
            txtPaymentID.setText(manageRentBO.generatePaymentID());


        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("  DashBord Form");

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }



    public void setInitialData(String input) {
        this.NIC = input;
        this.VNum =input;
        txtNIC.setText( NIC);
        txtVehicleNo.setText(CustomerFormController.VNum);
        txtRentDate.setValue(CustomerFormController.fromDate);
        txtReturnDate.setValue(CustomerFormController.toDate);

    }

    @FXML
    private void btnOK_On_Action(ActionEvent actionEvent) {
        LocalDate rentDateValue = txtRentDate.getValue();
        LocalDate returnDateValue = txtReturnDate.getValue();

        Period period = Period.between(rentDateValue,returnDateValue);
        int days = period.getDays();
        txtDuration.setText(String.valueOf(days));

        double vehicle_rate=0;
        try {
            vehicle_rate = manageVehicleBO.findVehicle(txtVehicleNo.getText()).getVehicle_rate();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
            double amount = vehicle_rate*days;

        txtRentalAmount.setText(String.valueOf(amount));

    }

    @FXML
    private void txtAdvance_On_Action(ActionEvent actionEvent) {
        double advance = Double.parseDouble(txtAdvance.getText());
        double amount = Double.parseDouble(txtRentalAmount.getText());
        double balance = amount-advance;

        txtBalance.setText(String.valueOf(balance));
    }

    @FXML
    private void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {

        if (txtDuration.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"cannot use empty field.plz Enter the OK",ButtonType.OK).showAndWait();
            return;
        }else if (txtAdvance.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"cannot use empty...",ButtonType.OK).showAndWait();
            txtAdvance.requestFocus();
            return;
        }

        boolean result=false;
        try {
             result = manageRentBO.createRent(new RentDTO(txtNIC.getText(), txtVehicleNo.getText(), txtRentDate.getValue(),
                    txtReturnDate.getValue(), Integer.parseInt(txtDuration.getText())));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (result){
            new Alert(Alert.AlertType.CONFIRMATION,"Payment Accepted,Customer Rent Successfully",
                    ButtonType.OK).showAndWait();
        }


        boolean payment =false;
        try {
             payment = managePaymentBO.createPayment(new PaymentDTO(txtPaymentID.getText(),txtRentDate.getValue(),
                    txtReturnDate.getValue(),null, Double.parseDouble(txtRentalAmount.getText()),
                     Double.parseDouble(txtAdvance.getText()),0,
                     0,txtNIC.getText(),txtVehicleNo.getText()));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (payment){
            new Alert(Alert.AlertType.CONFIRMATION,"Payment Accepted.Thank you...",ButtonType.OK).showAndWait();
        }else {
            new Alert(Alert.AlertType.ERROR,"Payment Not Accepted.Hold On",ButtonType.OK).showAndWait();

        }


        reset();

//        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/RentdetailsForm.fxml"));
//        Scene scene = new Scene(root);
//        Stage primaryStage = (Stage) btnPayment.getScene().getWindow();
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Rent Details Form");
//        primaryStage.show();
//        primaryStage.setResizable(false);
//        primaryStage.centerOnScreen();
    }
    private void reset(){
        txtNIC.clear();
        txtVehicleNo.clear();
        txtAdvance.clear();
        txtRentalAmount.clear();
        txtDuration.clear();
        txtReturnDate.setValue(null);
        txtRentDate.setValue(null);
        txtBalance.clear();
    }
}
