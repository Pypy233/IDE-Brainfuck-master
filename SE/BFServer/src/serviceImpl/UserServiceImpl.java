package serviceImpl;

import java.io.BufferedReader;
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
    
        public boolean register(String username,String password){
            boolean result = false;
            String line = "";
            String[] spiltLine;
            File userInformation = new File("userinformation");
            try{
                FileReader fileReader = new FileReader(userInformation);
                BufferedReader bfReader = new BufferedReader(fileReader);
                try{
                    while((line = bfReader.readLine())!=null){
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
            if(result==true){
                File newUserInformation = new File("userInformation");
                try{
                    FileWriter writer = new FileWriter(newUserInformation,true);
                    writer.write("\n"+username+","+password);
                    writer.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }

	@Override
	public boolean login(String username, String password) throws RemoteException {
            boolean result = false;
            String line = "";
            String[] spiltLine;
            File userFile = new File("userinformation");
            try {
                FileReader fileReader = new FileReader(userFile);
                    BufferedReader bfReader = new BufferedReader(fileReader);
                    try{
                    while((line = bfReader.readLine())!=null){
                        spiltLine = line.split(",");
                        if(spiltLine[0].equals(username)&&spiltLine[1].equals(password)){
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
      
		return true;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}
       

}
