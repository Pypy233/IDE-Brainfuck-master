/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioMenuItem;
import rmi.RemoteHelper;


public class MainFrame extends Application{
      private  int pointer = -1;
      ArrayList<String> list = new ArrayList<>();
      TextField fileNameText = new TextField();
      TextArea text = new TextArea();
      BorderPane pane =  new BorderPane();
      MenuBar menuBar = new MenuBar();
      TextArea inputText = new TextArea();
      TextArea resultArea = new TextArea();
       RadioMenuItem versionItem = new RadioMenuItem();
     String userId = "";

    /*public static void init(String[] args){
        Application.launch(args);
    }*/
      
    public static void getInstance(String[] args){
       Application.launch(args);
    }
   
    @Override
   
    public void start(Stage primaryStage){
      
      
          
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
   
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu runMenu = new Menu("Run");
        Menu versionMenu = new Menu("Version");
        Menu userMenu = new Menu("User");
        
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem undoMenuItem = new MenuItem("Undo");
        MenuItem redoMenuItem = new MenuItem("Redo");
        MenuItem runMenuItem = new MenuItem("Execute");
        MenuItem userMenuItem = new MenuItem("Login");
        
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(newMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(saveMenuItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exitItem);
         
        userMenu.getItems().add(userMenuItem);
        
        editMenu.getItems().add(undoMenuItem);
        editMenu.getItems().add(redoMenuItem);
        
        runMenu.getItems().add(runMenuItem);
        versionMenu.getItems().add(versionItem);
        menuBar.getMenus().addAll(fileMenu,editMenu,runMenu,versionMenu,userMenu);
        
       
      
        text.setWrapText(true);
        text.setEditable(true);
        text.setOnKeyReleased(e ->{
          String m=text.getText();
            if(m.length()>=1){
		if(m.substring(m.length()-1).equals("[")){
                    text.setText(text.getText()+"]");
			}
		}
	
				pointer=pointer+1;  
        });

        
       
        inputText.setWrapText(true);
        inputText.setEditable(true);
        
       
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
     userMenuItem.setOnAction(e ->{
         Login lo = new Login();
         lo.start(primaryStage);
         userId = lo.getUser();
     });
     
      
      
      newMenuItem.setOnAction(e ->{
         text.setText(null);
         inputText.setText(null);
         resultArea.setText(null);
      });// clear all the text
      
      
      
      openMenuItem.setOnAction(e ->{
          Open open = new Open();
          open.start(primaryStage);
      });
      
      saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
                SavePane sa = new SavePane();
                sa.start(primaryStage);
            }
        });
      
    
      
      exitItem.setOnAction(e ->{
            System.exit(0);
        });
      
      
      
            
      
      redoMenuItem.setOnAction(e ->{
          
      });
        
      runMenuItem.setOnAction((ActionEvent e) -> {
          String code = text.getText();
          String param = inputText.getText();
          
          try {
              resultArea.setText(RemoteHelper.getInstance().getExecuteService().execute(code, param));
              System.out.println(RemoteHelper.getInstance().getExecuteService().execute(code, param)+"\n***");
          } catch (RemoteException ex) {
              ex.printStackTrace();
          } catch(NullPointerException ex){
              ex.printStackTrace();
          }
        });
    }
    class SavePane extends Application{
        @Override
        public void start(Stage primaryStage){
          
           BorderPane savePane = new BorderPane();
           Label label = new Label("File:");
           savePane.setTop(label);
           savePane.setCenter(fileNameText);
           Button btSave = new Button("save");
           savePane.setBottom(btSave);
           btSave.setOnAction(e ->{
          String content = text.getText();
          Login lo = new Login();
          String user = lo.getUser();
          
          String fileName = fileNameText.getText();
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
          String date =   df.format(new Date());
            try {
             //   user = RemoteHelper.getInstance().getUserService().getUser();
                RemoteHelper.getInstance().getIOService().writeFile(content,user, fileName,date);
                System.out.print("UserId    ?"+userId);
                BorderPane pane = new BorderPane();
                Label saveLabel = new Label("File has been saved");
                Button btOK = new Button("OK!");
               pane.setTop(label);
               pane.setBottom(btOK);
               Scene scene1 = new Scene(pane,200,120);
               primaryStage.setScene(scene1);
               
               btOK.setOnAction(o ->{
                   primaryStage.close();
                   MainFrame mainFrame = new MainFrame();
                   mainFrame.start(primaryStage);
               });  
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
           });
             Scene scene = new Scene(savePane,200,130);
           primaryStage.setScene(scene);
           primaryStage.show();
           
        
        }
    
    }
    public static void main(String[] args){
        Application.launch(args);
    }
 
}
       

