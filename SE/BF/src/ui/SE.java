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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import java.util.ArrayList;
import rmi.RemoteHelper;

public class SE extends Application{
    public static void main(String[] args){
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage){
      

        BorderPane pane =  new BorderPane();
          
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
   
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu runMenu = new Menu("Run");
        Menu versionMenu = new Menu("Version");
        
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem undoMenuItem = new MenuItem("Undo");
        MenuItem redoMenuItem = new MenuItem("Redo");
        MenuItem runMenuItem = new MenuItem("Execute");
        MenuItem versionItem = new MenuItem("");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(newMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(saveMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exitItem);
         
        
        editMenu.getItems().add(undoMenuItem);
        editMenu.getItems().add(redoMenuItem);
        
        runMenu.getItems().add(runMenuItem);
        versionMenu.getItems().add(versionItem);
        menuBar.getMenus().addAll(fileMenu,editMenu,runMenu,versionMenu);
        Label label = new Label("test");
        
       
        TextArea text = new TextArea();
        text.setWrapText(true);
        text.setEditable(true);
      
        
        TextArea inputText = new TextArea();
        inputText.setWrapText(true);
        inputText.setEditable(true);
        
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
       
        
        
        VBox vBox = new VBox();
        HBox hBox1 = new HBox(480);
        HBox hBox2 = new HBox();
     
        Label inputLabel = new Label("Input");
        Label resultLabel = new Label("Result");
        hBox1.getChildren().addAll(inputLabel,resultLabel);
        hBox2.getChildren().addAll(inputText,resultArea);
        vBox.getChildren().addAll(hBox1,hBox2);
      
        pane.setTop(menuBar);
        pane.setCenter(text);
        pane.setBottom(vBox);
        
        Scene scene = new Scene(pane,1000,660);
        primaryStage.setTitle("BF Client");
        primaryStage.setScene(scene);
        primaryStage.show();
       
        // add KeyEvent
       fileMenu.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
       
                
                
        
        
        
        
      // add ActionListener  
      int pointer = -1;
      ArrayList<String> list = new ArrayList<>();
      
      
      newMenuItem.setOnAction(e ->{
         text.setText(null);
         inputText.setText(null);
         resultArea.setText(null);
      });// clear all the text
      
      
      
      openMenuItem.setOnAction(e ->{
        
      });
      
      saveMenuItem.setOnAction(e ->{
          String content = text.getText();
        
      });
      
      
      exitItem.setOnAction(e ->{
            System.exit(0);
        });
      
      
      
            
      text.setOnKeyPressed(e ->{
          String newText = text.getText();
          if(newText.length()>=1){
              if(newText.substring(newText.length()-1).equals("[")){
                  text.setText(text.getText()+"]");
              }
          }
          list.add(pointer+1,text.getText());
          pointer++;
      });
      
        
      undoMenuItem.setOnAction(e ->{
          if(pointer>0){
              pointer--;
          }
      
      });
      
      redoMenuItem.setOnAction(e ->{
          
      });
        
      runMenuItem.setOnAction(e ->{
          String code = text.getText();
          String input = inputText.getText();
          String result = "";
          try{
              result = RemoteHelper.getInstance().getExecuteService().execute(code, input);
              resultArea.setText(result);
          }catch(RemoteException ex){
              ex.printStackTrace();
          }
      });
    }
    
       
}
