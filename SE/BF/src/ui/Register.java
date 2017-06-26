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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import rmi.RemoteHelper;
public class Register extends Application{
    @Override
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(pane,400,250,Color.WHITE);
        Label userName = new Label("User Name:");  
         pane.add(userName, 0, 1);  
  
         TextField userTextField = new TextField();  
         pane.add(userTextField, 1, 1);  
  
         Label pw = new Label("Password:");  
         pane.add(pw, 0, 2);  
         PasswordField pf = new PasswordField();
         pane.add(pf,1,2);
         Label pw2 = new Label("Enter again:");  
         pane.add(pw2, 0, 3);  
         PasswordField pf2 = new PasswordField();
         pane.add(pf2,1,3); 
         Button btDone = new Button("Done");
         final Label message = new Label("");
         pane.add(btDone, 1, 5);
         pane.add(message, 1, 4);
        
         btDone.setOnAction(e ->{
             
          final String userNameStr = userTextField.getText();
          String password = pf.getText();
            System.out.println(1);
            System.out.println(userNameStr);
            System.out.println(password);
             if(!pf.getText().equals(pf2.getText())){
                  message.setText("Your password is different!");
                  message.setTextFill(Color.rgb(210, 39, 30));    
            }
            else {          
                message.setText("Your password has been confirmed");                
                message.setTextFill(Color.rgb(21, 117, 84));
                 try {
                     if(RemoteHelper.getInstance().getUserService().register(userNameStr, password)){
                       
                         primaryStage.close();
                         MainFrame mainFrame = new MainFrame();
                         mainFrame.start(primaryStage);
                             
                     }
                 } catch (RemoteException ex) {
                     ex.printStackTrace();
             }
             } 
         });
         
         
        primaryStage.setTitle("Register Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  public static void main(String[] args){
      Application.launch(args);
     
      
  }
    
    
}
    