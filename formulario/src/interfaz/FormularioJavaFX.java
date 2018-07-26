/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persona.Persona;

/**
 *
 * @author Kenny Camba Torres
 */
public class FormularioJavaFX extends Application {
    
    private final VBox ROOT = new VBox();;
    private Scene scene; 
    private TextField name;
    private TextField cedula;
    private TextArea direccion;
    private Button btnSave;
    private Label text;
    private ArrayList<Persona> personas;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        personas = new ArrayList<>();
        stage.setTitle("Hello World!");
        createScreenTitle();
        createScreenContent();
        createScreenButtons();
        scene = new Scene(ROOT);
        stage.setScene(scene);
        stage.show();
    }
    
    public void createScreenTitle(){
        HBox contentTitle = new HBox();
        contentTitle.setAlignment(Pos.CENTER);
        Label title = new Label("Formulario de Registro");
        title.setPadding(new Insets(10, 0, 0, 0));
        title.setStyle("-fx-font-size: 14pt");
        contentTitle.getChildren().add(title);
        ROOT.getChildren().add(contentTitle);
    }
    
    public void createScreenContent(){
        direccion = new TextArea();
        direccion.setMaxWidth(300);
        direccion.setMaxHeight(100);
        GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(5);
        content.setPadding(new Insets(10, 40, 10, 40));
        content.add(new Label("Nombre*"), 0, 0);
        content.add(new Label("Cedula*"), 0, 1);
        content.add(new Label("Drección"), 0, 2);
        name = new TextField();
        content.add(name, 1, 0);
        cedula = new TextField();
        content.add(cedula, 1, 1);
        content.add(direccion, 1, 2);
        ROOT.getChildren().add(content);
        
        HBox status = new HBox();
        text = new Label("");
        status.setPadding(new Insets(2, 0, 2, 10));
        status.getChildren().add(text);
        ROOT.getChildren().add(status);
    }
    
    public void createScreenButtons(){
        HBox contentButton = new HBox();
        contentButton.setPadding(new Insets(10, 0, 10, 0));
        
        Button btnClean = new Button("Limpiar");
        btnSave = new Button("Guardar");
        Button btnExit = new Button("Salir");
        
        //Clase anónima
        btnExit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.getDialogPane().setHeaderText("¿En verdad quiere salir?"); 
                Optional<ButtonType> op = alert.showAndWait();
                if(op.get() == ButtonType.OK) {
                    Platform.exit();
                }else {
                    alert.close();
                }
            }
        }); 
        
//        btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent evt) {
//                    
//            }
//        });
        //Expresion lambda
        btnClean.setOnMouseClicked(evt -> {
            limpiarCuadros(); 
        }); 
        
        btnSave.setOnAction(new AccionesBotonSave());
        
        contentButton.setAlignment(Pos.CENTER);
        contentButton.getChildren().addAll(btnClean, btnSave, btnExit);
        contentButton.setSpacing(10);
        ROOT.getChildren().add(contentButton); 
    }
    
    private void limpiarCuadros() {
        name.setText("");
        cedula.setText("");
        direccion.setText(""); 
    }
   
    private class AccionesBotonSave implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent evt) {
            Persona persona;
            String nombre = name.getText();
            String cedulaTxt = cedula.getText();
            if(!nombre.isEmpty() && !cedulaTxt.isEmpty()) {
                text.setText("Persona guardada correctamente"); 
                persona = new Persona(nombre, cedulaTxt, direccion.getText());
                limpiarCuadros();
                personas.add(persona);
                System.out.println(personas);
            }else if(nombre.isEmpty()) {
                text.setText("El campo nombre es obligatorio");
            }else if(cedulaTxt.isEmpty()) {
                text.setText("El campo cedula es obligatorio"); 
            }
        }
    }
}
