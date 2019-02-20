package lk.ijse.dep.fx.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.dep.fx.business.custom.ManageCustomerBO;
import lk.ijse.dep.fx.business.custom.ManagePaymentBO;
import lk.ijse.dep.fx.business.custom.ManageRentBO;
import lk.ijse.dep.fx.business.custom.ManageVehicleBO;
import lk.ijse.dep.fx.db.DBConnection;
import lk.ijse.dep.fx.dto.CustomDTO;
import lk.ijse.dep.fx.dto.CustomerDTO;
import lk.ijse.dep.fx.dto.PaymentDTO;
import lk.ijse.dep.fx.dto.VehicleDTO;
import lk.ijse.dep.fx.entity.Payment;
import lk.ijse.dep.fx.entity.Rent;
import lk.ijse.dep.fx.view.utill.AvailableTM;
import lk.ijse.dep.fx.view.utill.RentDetailsTM;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentDetailsFormController {
    @FXML
    private Button btnSearch;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtVehicleModel;
    @FXML
    private JFXTextField txtVehicleType;
    @FXML
    private Button btnBack;
    @FXML
    private JFXDatePicker txtRentDate;
    @FXML
    private JFXDatePicker txtReturnDate;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXTextField txtRate;
    @FXML
    private JFXTextField txtColour;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private Button btnReport;

    private ManageRentBO manageRentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGERENT);
//    private ManageCustomerBO manageCustomerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGECUSTOMER);
//    private ManageVehicleBO manageVehicleBO =BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEVEHICLE);

    public void initialize(){

    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashbord Form  Form");

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }

    public void btnSearch_On_Action(ActionEvent actionEvent) {
        String nic = txtNIC.getText();
        String vehicleNO = txtVehicleNo.getText();

        if (nic.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer NIC is Empty", ButtonType.OK).showAndWait();
            txtNIC.requestFocus();
            return;
        }
        else if (vehicleNO.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle Number is Empty",ButtonType.OK).showAndWait();
            txtVehicleNo.requestFocus();
            return;
        }
        else if(!isvalidateNIC(txtNIC.getText())){
            new Alert(Alert.AlertType.ERROR,"Invalid NIC number.please try again",ButtonType.OK).showAndWait();
            txtNIC.requestFocus();
            return;
        }
        else if(!isvalidateVNum(vehicleNO)){
            new Alert(Alert.AlertType.ERROR,"Invalid Vehicle number.please try again",ButtonType.OK).showAndWait();
            txtVehicleNo.requestFocus();
            return;
        }


        System.out.println(nic);
        System.out.println(vehicleNO);

        List<CustomDTO> rentDetails = null;
        try {
            rentDetails = manageRentBO.findRentDetails(nic, vehicleNO);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        ObservableList<CustomDTO> customDTOS = FXCollections.observableArrayList(rentDetails);
        ObservableList<Rent> items = FXCollections.observableArrayList();
        for (CustomDTO customDTO : customDTOS) {
            txtRentDate.setValue(customDTO.getRent_date());
            txtReturnDate.setValue(customDTO.getReturn_date());
            txtName.setText(customDTO.getName());
            txtAddress.setText(customDTO.getAddress());
            txtTelephone.setText(customDTO.getTelephone());
            txtVehicleModel.setText(customDTO.getModel());
            txtVehicleType.setText(customDTO.getType());
            txtColour.setText(customDTO.getColour());
            txtRate.setText(String.valueOf(customDTO.getVehicle_rate()));
        }

        LocalDate rentDateValue = txtRentDate.getValue();
        LocalDate returnDateValue = txtReturnDate.getValue();
        double rate = Double.parseDouble(txtRate.getText());

        Period period = Period.between(rentDateValue,returnDateValue);
        int days = period.getDays();


        double total =rate*days;
        txtTotal.setText(String.valueOf(total));
        System.out.println(total);

    }



    @FXML
    private void btnReportOnAction(ActionEvent actionEvent) throws Exception {

        File file = new File("JasperReports/RentalDetails.jasper");
        JasperReport compilereport = (JasperReport) JRLoader.loadObject(file);

        HashMap<String, Object> para = new HashMap<>();

        para.put("nic",txtNIC.getText());
        para.put("vnum",txtVehicleNo.getText());
        para.put("name",txtName.getText());
        para.put("rentdate",txtRentDate.getValue());
        para.put("returndate",txtReturnDate.getValue());
        para.put("total",Double.parseDouble(txtTotal.getText()));



        JasperPrint fillReport = JasperFillManager.fillReport(compilereport, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(fillReport,false);

    }

    private boolean isvalidateVNum(String Vnum){
        return Vnum.matches("^[A-Za-z]{2,3}-[0-9]{4}$");
    }

    private boolean isvalidateNIC(String NIC){
        return NIC.matches("^\\d{9}[Vv]$");
    }
    }
