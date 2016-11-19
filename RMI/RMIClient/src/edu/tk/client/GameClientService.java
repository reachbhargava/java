package edu.tk.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Set;

import edu.tk.remote.IGameClient;

public class GameClientService extends UnicastRemoteObject implements IGameClient {

	public GameClientService() throws RemoteException {
	}

	public void receiveFlyHunted(String playerName, int newPoints) {
		System.out.println("Received player name - " + playerName + " with points - " + newPoints);
	}

	public void receiveFlyPosition(int x, int y) throws RemoteException {
		if(null==FlyGame.newPosition)
		{
			FlyGame.newPosition = new Position();
		}
		FlyGame.newPosition.setX(x);
		FlyGame.newPosition.setY(y);
	}

	public void updateChanges(Map<String, Integer> gameData) throws RemoteException {
		FlyGame.gameData = gameData;
	}
}
