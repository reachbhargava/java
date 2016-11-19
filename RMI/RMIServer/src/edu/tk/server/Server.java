package edu.tk.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.util.Timer;

public class Server {

	public static void main(String[] argv) {
		try {
			System.setProperty("java.security.policy", "./security.policy");
			LocateRegistry.createRegistry(1099);
			System.setSecurityManager(new RMISecurityManager());

			GameServerService gameServerService = new GameServerService();
			Naming.rebind("rmi://localhost/gameServerService", gameServerService);

			System.out.println("Game Service Server is ready.");

			Timer timer = new Timer();
			timer.schedule(new Scheduler(gameServerService), 0, 900);
		} catch (Exception e) {
			System.err.println("Exception in server : " + e);
		}
	}
}
