package com.example.usuario.usuario.Empleados;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import kong.unirest.GetRequest;
import kong.unirest.Unirest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpleadosCreadosController implements Initializable {

    @FXML
    private Label centrosdeportivos;

    @FXML
    private Button cerrar_sesion_button;

    @FXML
    private Button creados_button;

    @FXML
    private Button crearNuevo_button;

    @FXML
    private ImageView foto;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label titulo_dos;

    @FXML
    private Label titulo_uno;

    List<Empleado> empleados1 = new ArrayList<>();

    String mail;
    @FXML
    private Button administracion;

    @FXML
    private Button borrar_button;

    @FXML
    void AdministracionClickedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("com/example/usuario/usuario/Empleados/Administracion-view.fxml"));
        Stage stage;
        Scene scene;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void BorrarEmpleadoClickedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/usuario/usuario/Empleados/BorrarEmpleado-view.fxml"));
        Stage stage;
        Scene scene;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CerrarSesionClickedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/usuario/usuario/LogIn-view.fxml"));
        Stage stage;
        Scene scene;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CrearNuevoClickedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/usuario/usuario/Empleados/CrearEmpleado-view.fxml"));
        Stage stage;
        Scene scene;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String mail_;

    public String getMail_() {
        return mail_;
    }

    public void setMail_(String mail_) {
        this.mail_ = mail_;
    }

    @FXML
    void CreadosClickedButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage stage;
        Scene scene;
        Parent root = fxmlLoader.load(EmpleadosCreadosController.class.getResourceAsStream("/com/example/usuario/usuario/Empleados/EmpleadosCreados-view"));
        EmpleadosCreadosController empleadosCreadosController = fxmlLoader.getController();
        empleadosCreadosController.setMail(mail_);
        empleadosCreadosController.informacion();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public List<Empleado> getData() {
        List<Empleado> empleadosList =null;
        try{
            GetRequest apiResponse = Unirest.get("http://localhost:8080/api/v1/gimnasio/empleado/mailEmpresa/" + mail)
                    .header("Content-Type", "application/json");
            String temp = apiResponse.asJson().getBody().toString();
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            empleadosList = mapper.readValue(temp, new TypeReference<List<Empleado>>(){});
            System.out.println(empleadosList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return empleadosList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void informacion(){
        empleados1.addAll(getData());
        int row = 1;
        int colum = 0;

        try {
            for (int i = 0; i < empleados1.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/usuario/usuario/Empleados/DesplegarEmpleados-view.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                DesplegarEmpleadosController desplegarEmpleadosController = fxmlLoader.getController();
                desplegarEmpleadosController.setData(empleados1.get(i));

                if (colum == 2) {
                    colum = 0;
                    row++;
                }

                grid.add(anchorPane, colum++, row);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}