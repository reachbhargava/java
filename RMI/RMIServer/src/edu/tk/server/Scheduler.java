package edu.tk.server;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class Scheduler extends TimerTask {

	GameServerService gameServerService;

	public Scheduler(GameServerService gameServerService) {
		this.gameServerService = gameServerService;
	}

	@Override
	public void run() {
		try {
			gameServerService.propogatePositions();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
