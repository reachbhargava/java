package edu.tk.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.tk.remote.IGameClient;
import edu.tk.remote.IGameServer;

public class GameServerService extends UnicastRemoteObject implements IGameServer {

	public Position currentPosition;

	public Map<String, IGameClient> players;

	public Map<String, Integer> gameData;

	public GameServerService() throws RemoteException {
	}

	public void huntFly(String playerName) throws RemoteException {
		System.out.println("The player " + playerName + " has hunted the fly.");
		Integer count = gameData.get(playerName);
		gameData.put(playerName, count + 1);
		updateAll();

	}

	public void login(String playerName, IGameClient client) throws RemoteException {

		if (players == null) {
			players = new HashMap<String, IGameClient>();
		}
		players.put(playerName, client);
		if (gameData == null) {
			gameData = new HashMap<String, Integer>();
		}
		gameData.put(playerName, 0);

		if (currentPosition == null) {
			currentPosition = generateRandomPosition();
		}
		client.receiveFlyPosition(currentPosition.getX(), currentPosition.getY());
		updateAll();
	}

	public void updateAll() throws RemoteException {
		Set<String> playerNames = players.keySet();
		for (String name : playerNames) {
			players.get(name).updateChanges(gameData);
		}
	}

	public void logout(String playerName) throws RemoteException {

		System.out.println("In logout - " + playerName);
		players.remove(playerName);
		gameData.remove(playerName);
		updateAll();
	}

	public void propogatePositions() throws RemoteException {

		if (null != players) {
			Position position = generateRandomPosition();
			Set<String> playerNames = players.keySet();
			for (String name : playerNames) {
				players.get(name).receiveFlyPosition(position.getX(), position.getY());
			}
			currentPosition = position;
		}
	}

	private Position generateRandomPosition() {
		Position pos = new Position();
		int max_X = 1050;
		int min_X = 50;
		int max_Y = 550;
		int min_Y = 50;
		Random rand = new Random();
		pos.setX(rand.nextInt((max_X - min_X) + 1) + min_X);
		pos.setY(rand.nextInt((max_Y - min_Y) + 1) + min_Y);
		return pos;
	}

}
