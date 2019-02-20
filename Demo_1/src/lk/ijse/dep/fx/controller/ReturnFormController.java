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
import lk.ijse.dep.fx.db.DBConnection;
import lk.ijse.dep.fx.dto.PaymentDTO;
import lk.ijse.dep.fx.entity.RentPK;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnFormController {
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtAdvanced;
    @FXML
    private JFXTextField txtExtraAmount;
    @FXML
    private JFXDatePicker txtCurrentDate;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtPaymentID;
    @FXML
    private JFXTextField txtRentalID;
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
    @FXML
    private Button btnSearch;
    @FXML
    private JFXTextField txtNetTotal;
    @FXML
    private Button btnReport;

    private ManagePaymentBO managePaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEPAYMENT);
    private ManageRentBO manageRentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGERENT);
    private ManageVehicleBO manageVehicleBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEVEHICLE);

    public void initialize(){
        txtCurrentDate.setValue(LocalDate.now());
    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("DashBord Form ");

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }

    @FXML
    private void btnSearchOnAction(ActionEvent actionEvent) {

        PaymentDTO paymentDTO =null;
        try {
             paymentDTO = managePaymentBO.findPayment(txtPaymentID.getText());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (paymentDTO == null){
            new Alert(Alert.AlertType.ERROR,"InValid Payment ID", ButtonType.OK).showAndWait();
            txtPaymentID.requestFocus();
            txtPaymentID.selectAll();
        }else {
            txtRentDate.setValue(paymentDTO.getRent_date());
            txtReturnDate.setValue(paymentDTO.getReturn_date());
            txtNIC.setText(paymentDTO.getC_nic());
            txtVehicleNo.setText(paymentDTO.getV_num());
            txtAdvanced.setText(String.valueOf(paymentDTO.getAdvanced()));
            txtRentalAmount.setText(String.valueOf(paymentDTO.getRental_amount()));
        }

        LocalDate currentDateValue = txtCurrentDate.getValue();
        LocalDate returnDateValue = txtReturnDate.getValue();

        Period period = Period.between(returnDateValue,currentDateValue);
        int days = period.getDays();
        txtDuration.setText(String.valueOf(days));

        double vehicle_rate=0;
        try {
             vehicle_rate = manageVehicleBO.findVehicle(txtVehicleNo.getText()).getVehicle_rate();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
         double extraAmount =  vehicle_rate*days;
        txtExtraAmount.setText(String.valueOf(extraAmount));

        double rentalAmount = Double.parseDouble(txtRentalAmount.getText());
        double advance = Double.parseDouble(txtAdvanced.getText());
        double balance = rentalAmount-advance;

        txtBalance.setText(String.valueOf(balance));
        double netTotal = balance + extraAmount;

        txtNetTotal.setText(String.valueOf(netTotal));

    }


    @FXML
    private void btnReportOnAction(ActionEvent actionEvent) throws Exception {
        boolean payresult=false;
        try {
             payresult = managePaymentBO.updatePayment(new PaymentDTO(txtPaymentID.getText(), txtRentDate.getValue(),
                    txtReturnDate.getValue(), txtCurrentDate.getValue(), Double.parseDouble(txtRentalAmount.getText()),
                    Double.parseDouble(txtAdvanced.getText()), Double.parseDouble(txtExtraAmount.getText()),
                    Double.parseDouble(txtNetTotal.getText()),
                    txtNIC.getText(), txtVehicleNo.getText()));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (payresult){
           okAlert();
        }else {
            failedAlert();
        }

        File file = new File("JasperReports/PaymentBill.jasper");
        JasperReport compilereport = (JasperReport) JRLoader.loadObject(file);

        HashMap<String, Object> para = new HashMap<>();

        para.put("pid",txtPaymentID.getText());
        para.put("date",txtCurrentDate.getValue());
        para.put("nic",txtNIC.getText());
        para.put("vnum",txtVehicleNo.getText());
        para.put("nettotal",txtNetTotal.getText());

        JasperPrint fillReport = JasperFillManager.fillReport(compilereport, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(fillReport,false);


        try {
            managePaymentBO.deletePayment(txtPaymentID.getText());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        boolean rentresult =false;
        try {
             rentresult = manageRentBO.deleteRent(new RentPK(txtNIC.getText(),txtVehicleNo.getText()));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (rentresult){
            okAlert();
        }else {
            failedAlert();
        }
        returnreset();







    }

    public void returnreset(){
        txtReturnDate.setValue(null);
        txtReturnDate.setValue(null);
        txtRentalAmount.clear();
        txtDuration.clear();
        txtAdvanced.clear();
        txtVehicleNo.clear();
        txtBalance.clear();
        txtExtraAmount.clear();
        txtNetTotal.clear();
        txtPaymentID.clear();
        txtNIC.clear();
        txtVehicleNo.clear();
    }
    public void okAlert(){
        new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully.Thank you Come Again",
                ButtonType.OK).showAndWait();
    }
    public void failedAlert(){
        new Alert(Alert.AlertType.ERROR,"Failed.Try Again",
                ButtonType.OK).showAndWait();
    }
}
