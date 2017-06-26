package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.io.FileNotFoundException;
import java.io.IOException;

import service.IOService;
import service.UserService;
import service.ExecuteService;
import serviceImpl.IOServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.ExecuteServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService,
        UserService,ExecuteService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
        private ExecuteService executeService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
                executeService= new ExecuteServiceImpl();
	}
        @Override
        public String execute(String code,String param)throws RemoteException{
		return executeService.execute(code, param);
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName,String time) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName,time);
	}

	@Override
	public String readFile(String userId, String fileName,String time) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName,time);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}
        public boolean deleteFile(String userId,String fileName) throws RemoteException{
		return iOService.deleteFile(userId,fileName);
	}
	
      
     
	public boolean register(String username,String password)throws RemoteException{
		return userService.register(username, password);
	}
        public String getUser() throws RemoteException{
            return userService.getUser();
        }

}
