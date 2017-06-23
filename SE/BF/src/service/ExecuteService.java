/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author py
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ExecuteService extends Remote{
    public String execute(String code,String param)throws RemoteException;
		
}

