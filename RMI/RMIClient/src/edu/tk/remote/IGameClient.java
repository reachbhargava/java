package edu.tk.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IGameClient extends Remote {
	void receiveFlyHunted(String playerName, int newPoints) throws RemoteException;

	void receiveFlyPosition(int x, int y) throws RemoteException;

	void updateChanges(Map<String, Integer> gameData) throws RemoteException;
}
