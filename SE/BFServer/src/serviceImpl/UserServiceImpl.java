package serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.UserService;


public class UserServiceImpl implements UserService{
     private String userId = "";
        public boolean register(String username,String password){

            String line = "";
            String[] spiltLine;
            File userInformation = new File("/Users/py/Documents/workspace/SE/BFServer/userinformation.txt");
                       
            boolean result = true;
            
            try{
                FileReader fileReader = new FileReader(userInformation);
                BufferedReader bfReader = new BufferedReader(fileReader);
                try{
                   
                    while((line = bfReader.readLine())!=null){
                        System.out.println(line);
                        spiltLine = line.split(",");
                        if(spiltLine.length>0&&spiltLine[0].equals(username)){
                            result = false;
                            break;
                        }// prevent repetitive username
                    }
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }
            if(result){
                File newUserInformation = new File("/Users/py/Documents/workspace/SE/BFServer/userinformation.txt");
                
                System.out.println("THat's true!");
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newUserInformation,true));
                    
                    writer.append(username+","+password+"\n");
                    writer.flush();
                    writer.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }

	@Override
	public boolean login(String username, String password) throws RemoteException{
            boolean result = false;
            String line = "";
            String[] spiltLine;
            File userFile = new File("/Users/py/Documents/workspace/SE/BFServer/userinformation.txt");
            try {
                FileReader fileReader = new FileReader(userFile);
                    BufferedReader bfReader = new BufferedReader(fileReader);
                    try{
                    while((line = bfReader.readLine())!=null){
                    //    System.out.print(line+"py");
                        spiltLine = line.split(",");
                        if(spiltLine[0].equals(username)&&spiltLine[1].equals(password)){
                         //   System.out.println(spiltLine[0]);
                         //   System.out.println(spiltLine[1]);
                         
                            result = true;
                            break;
                        }bfReader.close();
                    }
                    }catch(IOException ex){
                            ex.printStackTrace();
                        }
                    
            } 
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
          
            
		return result;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}
     @Override
      public String getUser(){
          return userId;
      }

}
