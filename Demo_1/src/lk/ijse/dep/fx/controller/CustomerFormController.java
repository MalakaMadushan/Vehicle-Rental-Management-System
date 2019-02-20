package lk.ijse.dep.fx.controller;

import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.fx.business.BOFactory;
import lk.ijse.dep.fx.business.custom.ManageCustomerBO;
import lk.ijse.dep.fx.dto.CustomerDTO;
import lk.ijse.dep.fx.view.utill.CustomerTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFormController {
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private TableView<CustomerTM> tblCustomer;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnNext;
    @FXML
    private JFXCheckBox chkNICCopy;
    @FXML
    private JFXCheckBox chkBill;

    public static String VNum;
    public static LocalDate toDate;
    public static LocalDate fromDate;


    private ManageCustomerBO manageCustomerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGECUSTOMER);

    public void initialize(){
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephone"));

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);

        List<CustomerDTO>  customerDB =null;
        try {
             customerDB = manageCustomerBO.getCustomer();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList(customerDB);
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
        for (CustomerDTO customerDTO : customerDTOS) {
            customerTMS.add(new CustomerTM(customerDTO.getNic(),customerDTO.getName(),customerDTO.getAddress(),
                    customerDTO.getTelephone()));

        }

        tblCustomer.setItems(customerTMS);

        // fill the text field after table select
        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM selectedItem) {

                if (selectedItem == null){
                    return;
                }
                txtNIC.setText(selectedItem.getNic());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                txtTelephone.setText(selectedItem.getTelephone());

                txtNIC.setEditable(false);
                btnAdd.setDisable(false);
                btnRemove.setDisable(false);
                btnAdd.setText("Update");
            }
        });

    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {

        //check the txt field is empty?
        if (txtNIC.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer NIC is Empty",ButtonType.OK).showAndWait();
            txtNIC.requestFocus();
            return;
        }else if (txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer Name is Empty",ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }else if (txtAddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer Address is Empty",ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }else if (txtTelephone.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer Telephone Number is Empty",ButtonType.OK).showAndWait();
            txtTelephone.requestFocus();
            return;
        }else if (!chkNICCopy.isSelected()){
            new Alert(Alert.AlertType.ERROR,"Customer have no NIC Copy",ButtonType.OK).showAndWait();
            return;
        }else if (!chkBill.isSelected()){
            new Alert(Alert.AlertType.ERROR,"Customer have no Util Bill Copy",ButtonType.OK).showAndWait();
            return;
        }
        //validation for NIC
        else if(!isvalidateNIC(txtNIC.getText())){
            new Alert(Alert.AlertType.ERROR,"Invalid NIC number.please try again",ButtonType.OK).showAndWait();
            txtNIC.requestFocus();
            return;
        }
        // validation for Telephone
        else if (!isvalidateTele(txtTelephone.getText())){
            new Alert(Alert.AlertType.ERROR,"Invalid Telephone number.please try again",ButtonType.OK).showAndWait();
            txtTelephone.requestFocus();
            return;
        }

        if (tblCustomer.getSelectionModel().isEmpty()){
            //New Customer

            ObservableList<CustomerTM> items = tblCustomer.getItems();
            //find Duplicate value
            for (CustomerTM customerTM : items) {
                if (txtNIC.getText().equals(customerTM.getName())){
                    new Alert(Alert.AlertType.ERROR,"Duplicate Value is Not Allowed",ButtonType.OK).showAndWait();
                    txtNIC.requestFocus();
                    return;
                }
            }

            CustomerTM customerTM = new CustomerTM(txtNIC.getText(),txtName.getText(),txtAddress.getText(),
                    txtTelephone.getText());
            tblCustomer.getItems().add(customerTM);
            CustomerDTO customerDTO = new CustomerDTO(txtNIC.getText(),txtName.getText(),txtAddress.getText(),
                    txtTelephone.getText());

            boolean result = false;
            try {
                result = manageCustomerBO.createCustomer(customerDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Save Successfully", ButtonType.OK).showAndWait();
                tblCustomer.scrollTo(customerTM);
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Save Failed", ButtonType.OK).showAndWait();
            }

        }
        else {
            // customer Update
            CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(txtName.getText());
            selectedCustomer.setAddress(txtAddress.getText());
            selectedCustomer.setTelephone(txtTelephone.getText());
            tblCustomer.refresh();

            boolean result=false;
            try {
                 result = manageCustomerBO.updateCustomer(new CustomerDTO(txtNIC.getText(),txtName.getText(),
                         txtAddress.getText(),txtTelephone.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Update Successfully",ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Update Failed",ButtonType.OK).showAndWait();
            }


        }
        reset();

    }

    @FXML
    private void btnRemoveOnAction(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem().getNic();
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());

            boolean result = false;
            try {
                result = manageCustomerBO.deleteCustomer(selectedCustomer);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Successfully", ButtonType.OK).showAndWait();

            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Delete Failed", ButtonType.OK).showAndWait();
            }
            reset();
        }
    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/DashbordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("DashBord Form");

        primaryStage.show();
    }

    @FXML
    private void btnNewOnAction(ActionEvent actionEvent) {
        btnAdd.setText("Add");
        reset();
    }

    @FXML
    private void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/fx/view/PaymentForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Payment Details Form");

        primaryStage.show();
    }
    public void reset(){
        txtNIC.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelephone.clear();
        chkBill.setSelected(false);
        chkNICCopy.setSelected(false);
        txtNIC.requestFocus();
        btnAdd.setDisable(false);
        btnRemove.setDisable(true);
        txtNIC.setEditable(true);
        tblCustomer.getSelectionModel().clearSelection();

    }
    private boolean isvalidateNIC(String NIC){
        return NIC.matches("^\\d{9}[Vv]$");
    }

    private boolean isvalidateTele(String tele){
        return  tele.matches("^\\d{10}$");
    }

    public void tblCustomers_On_Click(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2){

            CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/fx/view/PaymentForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PaymentFormController controller = fxmlLoader.getController();
            controller.setInitialData(selectedItem.getNic());
            Scene scene = new Scene(root);
            ((Stage)tblCustomer.getScene().getWindow()).setScene(scene);
        }
    }
    public void setInitialData(String input,LocalDate toDate,LocalDate fromDate) {

        this.VNum =input;
        this.toDate =toDate;
        this.fromDate =fromDate;



    }


}
