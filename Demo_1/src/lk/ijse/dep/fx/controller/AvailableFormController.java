package lk.ijse.dep.fx.controller;

import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.fx.business.BOFactory;
import lk.ijse.dep.fx.business.custom.ManageRentBO;
import lk.ijse.dep.fx.business.custom.ManageVehicleBO;
import lk.ijse.dep.fx.dto.CustomDTO;
import lk.ijse.dep.fx.dto.VehicleDTO;
import lk.ijse.dep.fx.view.utill.AvailableTM;
import lk.ijse.dep.fx.view.utill.CustomerTM;
import lk.ijse.dep.fx.view.utill.VehicleTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvailableFormController {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<AvailableTM> tblAvailable;
    @FXML
    private Button btnNext;
    @FXML
    private JFXDatePicker txtFromDate;
    @FXML
    private JFXDatePicker txtToDate;

    private ManageVehicleBO manageVehicleBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGEVEHICLE);
    private ManageRentBO manageRentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGERENT);


    public void initialize(){
        tblAvailable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        tblAvailable .getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAvailable .getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblAvailable .getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblAvailable .getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("vehicle_rate"));
        tblAvailable .getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("colour"));


    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle(" DashBord Form");
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnSearchOnAction(ActionEvent actionEvent) {
        LocalDate fromDateValue = txtFromDate.getValue();
        LocalDate toDateValue = txtToDate.getValue();

        List<CustomDTO> DB= null;
        try {
             DB = manageRentBO.getfindAvailableVehicleDetails(fromDateValue, toDateValue);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        ObservableList<CustomDTO> customDTOS = FXCollections.observableArrayList(DB);
        ObservableList<AvailableTM> items = FXCollections.observableArrayList();
        for (CustomDTO customDTO : customDTOS) {
            items.add(new AvailableTM(customDTO.getVehicle_number(),customDTO.getV_name(),customDTO.getModel(),
                    customDTO.getType(),customDTO.getVehicle_rate(),customDTO.getColour()));
        }
        tblAvailable.setItems(items);


        List<CustomDTO> DBnew=null;
        try {
             DBnew = manageRentBO.notRentAvailbleVehicle();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        ObservableList<CustomDTO> customDTOS1 = FXCollections.observableArrayList(DBnew);
        ObservableList<AvailableTM> items2 = FXCollections.observableArrayList();
        for (CustomDTO dto : customDTOS1) {
            items2.add(new AvailableTM(dto.getVehicle_number(),dto.getV_name(),dto.getModel(),dto.getType(),
                    dto.getVehicle_rate(),dto.getColour()));
        }
//        for (CustomDTO customDTO : customDTOS1) {
//            items2.add(new AvailableTM(customDTO.getVehicle_number(),customDTO.getV_name(),customDTO.getModel(),
//                    customDTO.getType(),customDTO.getVehicle_rate(),customDTO.getColour()));
//        }
        tblAvailable.setItems(items2);
    }


    @FXML
    private void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/CustomerForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle(" Customer Form");
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
    }

    public void tblAvailable_On_Click(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2){

            AvailableTM selectedItem = tblAvailable.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/fx/view/CustomerForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            CustomerFormController controller = fxmlLoader.getController();
            controller.setInitialData(selectedItem.getVehicle_number(),txtToDate.getValue(),txtFromDate.getValue());
            Scene scene = new Scene(root);
            ((Stage)tblAvailable.getScene().getWindow()).setScene(scene);
        }
    }
}
