/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author py
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class LoginFailure extends Application{
     @Override
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
         Label label = new Label("The password is wrong!");
         Button btOk = new Button("OK");
         btOk.setOnAction(e ->{
             primaryStage.close();
             Login lo  = new Login();
             lo.start(primaryStage);
         });
         pane.add(label, 1, 0);
         pane.add(btOk, 2, 3);
         Scene scene = new Scene(pane,200,60);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
