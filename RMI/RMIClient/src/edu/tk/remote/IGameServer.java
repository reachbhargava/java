package edu.tk.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameServer extends Remote {
	void huntFly(String playerName) throws RemoteException;
	
	void login(String playerName, IGameClient client) throws RemoteException;
	
	public void logout(String playerName) throws RemoteException;
}
