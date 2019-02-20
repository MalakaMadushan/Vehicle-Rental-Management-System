package lk.ijse.dep.fx.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dep.fx.business.BOFactory;
import lk.ijse.dep.fx.business.custom.ManageVehicleBO;
import lk.ijse.dep.fx.dto.VehicleDTO;
import lk.ijse.dep.fx.entity.Vehicle;
import lk.ijse.dep.fx.view.utill.VehicleTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleFormController {

    @FXML
    private JFXTextField txtVehicleName;
    @FXML
    private JFXTextField txtModel;
    @FXML
    private JFXTextField txtType;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private TableView<VehicleTM> tblVehicle;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtVehicleNumber;
    @FXML
    private JFXTextField txtColour;
    @FXML
    private JFXTextField txtVehicleRate;

    private ManageVehicleBO manageVehicleBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEVEHICLE);

    public void initialize(){
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("vehicle_rate"));
        tblVehicle.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("colour"));

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);

        List<VehicleDTO> vehicleDB =null;
        try {
             vehicleDB = manageVehicleBO.getVehicle();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        ObservableList<VehicleDTO> vehicleDTOS = FXCollections.observableArrayList(vehicleDB);
        ObservableList<VehicleTM> vehicleTMS = FXCollections.observableArrayList();
        for (VehicleDTO vehicleDTO : vehicleDTOS) {
           vehicleTMS.add(new VehicleTM(vehicleDTO.getVehicle_number(),vehicleDTO.getName(),vehicleDTO.getModel(),
                   vehicleDTO.getType(),vehicleDTO.getVehicle_rate(),vehicleDTO.getColour()));

        }
        tblVehicle.setItems(vehicleTMS);

        tblVehicle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<VehicleTM>() {
            @Override
            public void changed(ObservableValue<? extends VehicleTM> observable, VehicleTM oldValue, VehicleTM selectedItem) {

                if (selectedItem == null){
                    return;
                }
                txtVehicleNumber.setText(selectedItem.getVehicle_number());
                txtVehicleName.setText(selectedItem.getName());
                txtModel.setText(selectedItem.getModel());
                txtType.setText(selectedItem.getType());
                txtVehicleRate.setText(String.valueOf(selectedItem.getVehicle_rate()));
                txtColour.setText(selectedItem.getColour());

                txtVehicleNumber.setEditable(false);
                btnAdd.setDisable(false);
                btnRemove.setDisable(false);
                btnAdd.setText("Update");
            }
        });
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {

        //check the txt field is empty?
        if (txtVehicleNumber.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle Number is Empty",ButtonType.OK).showAndWait();
            txtVehicleNumber.requestFocus();
            return;
        }else if (txtVehicleName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle Name is Empty",ButtonType.OK).showAndWait();
            txtVehicleName.requestFocus();
            return;
        }else if (txtModel.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle Model is Empty",ButtonType.OK).showAndWait();
            txtModel.requestFocus();
            return;
        }else if (txtType.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle Type  is Empty",ButtonType.OK).showAndWait();
            txtType.requestFocus();
            return;
        }else if (txtVehicleRate.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Vehicle rate  is Empty",ButtonType.OK).showAndWait();
            txtVehicleRate.requestFocus();
            return;
        }else if (txtColour.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"vehicle Colour is Empty",ButtonType.OK).showAndWait();
            txtColour.requestFocus();
            return;
        }
        //validation for Vehicle NUmber
        else if(!isvalidateVNum(txtVehicleNumber.getText())){
            new Alert(Alert.AlertType.ERROR,"Invalid Vehicle number.please try again",ButtonType.OK).showAndWait();
            txtVehicleNumber.requestFocus();
            return;
        }

        if (tblVehicle.getSelectionModel().isEmpty()){
            //new Vehicle

            ObservableList<VehicleTM> items = tblVehicle.getItems();
            for (VehicleTM vehicleTM : items) {
                if (vehicleTM.getVehicle_number().equals(txtVehicleNumber.getText())){
                    new Alert(Alert.AlertType.ERROR,"Duplicate Value is Not Allowed",ButtonType.OK).showAndWait();
                    txtVehicleNumber.requestFocus();
                    return;
                }

            }
            VehicleTM vehicleTM = new VehicleTM(txtVehicleNumber.getText(),txtVehicleName.getText(),txtModel.getText(),
                    txtType.getText(),Double.parseDouble(txtVehicleRate.getText()),txtColour.getText());
            tblVehicle.getItems().add(vehicleTM);
            VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleNumber.getText(),txtVehicleName.getText(),txtModel.getText(),
                    txtType.getText(),Double.parseDouble(txtVehicleRate.getText()),txtColour.getText());

            boolean result = false;
            try {
                result = manageVehicleBO.createVehicle(vehicleDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle save successfully", ButtonType.OK).showAndWait();
                tblVehicle.scrollTo(vehicleTM);
            }else {
                new Alert(Alert.AlertType.ERROR,"Vehicle save Failed", ButtonType.OK).showAndWait();
                return;
            }

        }else {
            //Vehicle Update
            VehicleTM selectedVehicle = tblVehicle.getSelectionModel().getSelectedItem();
            selectedVehicle.setName(txtVehicleName.getText());
            selectedVehicle.setModel(txtModel.getText());
            selectedVehicle.setType(txtType.getText());
            selectedVehicle.setVehicle_rate(Double.valueOf(txtVehicleRate.getText()));
            selectedVehicle.setColour(txtColour.getText());

            boolean result= false;
            try {
                 result = manageVehicleBO.updateVehicle(new VehicleDTO(txtVehicleNumber.getText(), txtVehicleName.getText(), txtModel.getText(),
                        txtType.getText(), Double.parseDouble(txtVehicleRate.getText()), txtColour.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Update Successfully",ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Vehicle Update Failed",ButtonType.OK).showAndWait();
            }


        }
        reset();
    }

    @FXML
    private void btnRemoveOnAction(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (ButtonType.YES == buttonType.get()){
            String selectedVehicle = tblVehicle.getSelectionModel().getSelectedItem().getVehicle_number();
            tblVehicle.getItems().remove(tblVehicle.getSelectionModel().getSelectedItem());
            boolean result= false;
            try {
                result = manageVehicleBO.deleteVehicle(selectedVehicle);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle delete Successfully",ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Vehicle delete Failed",ButtonType.OK).showAndWait();
            }
        }
        reset();


    }

    @FXML
    private void btnNewOnAction(ActionEvent actionEvent) {
        reset();
        btnAdd.setText("Add");
    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("DashBord  Form");

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
    private void reset(){
        txtVehicleNumber.clear();
        txtVehicleName.clear();
        txtModel.clear();
        txtType.clear();
        txtVehicleRate.clear();
        txtColour.clear();
        txtVehicleNumber.requestFocus();
        btnAdd.setDisable(false);
        txtVehicleNumber.setEditable(true);
        tblVehicle.getSelectionModel().clearSelection();

    }
    private boolean isvalidateVNum(String Vnum){
        return Vnum.matches("^[A-Za-z]{2,3}-[0-9]{4}$");
    }

}
