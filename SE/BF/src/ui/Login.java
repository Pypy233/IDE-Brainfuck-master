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

import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rmi.RemoteHelper;

public class Login extends Application{
     PasswordField pf = new PasswordField();
       TextField userTextField = new TextField();  
       private String userNameStr = "";
    @Override
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(pane,400,250,Color.WHITE);
      Text title = new Text(50,50,"Logon Screen");
      title.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.ITALIC,15));
      pane.add(title,0,0, 2, 1);
  
      Label userName = new Label("User Name:");  
      pane.add(userName, 0, 1);  
  
   
      pane.add(userTextField, 1, 1);  
  
      Label pw = new Label("Password:");  
      pane.add(pw, 0, 2);  
     
      pane.add(pf,1,2);
      Button btRegister = new Button("Register");
      
      Button btDone = new Button("Done");
      pane.add(btDone,3,2);
      
     
      
      btDone.setOnAction(e ->{
          final String  userNameStr = userTextField.getText();
           String password = pf.getText();
           System.out.print(userNameStr);
          try{
             if(RemoteHelper.getInstance().getUserService().login(userNameStr, password)){
                  
                 primaryStage.close();
                 MainFrame mainFrame = new MainFrame();
                 mainFrame.start(primaryStage);
             }else{
                 primaryStage.close();
                 LoginFailure logFailure = new LoginFailure();
                 logFailure.start(primaryStage);
             }
              }catch(RemoteException ex){
                  ex.printStackTrace();
              }
          
      });
      
      btRegister.setOnAction(e ->{
          primaryStage.close();
          Register re = new Register();
          re.start(primaryStage);
      });
      pane.add(btRegister,1,4);
        primaryStage.setTitle("Logon Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
   public String getUser(){
     return userNameStr;      
   }
   public String getPassword(){
       return pf.getText();
   }
   public void getInstance(String[] args){
       Application.launch(args);
   }
    
}
