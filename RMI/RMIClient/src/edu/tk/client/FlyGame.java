package edu.tk.client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.tk.remote.IGameClient;
import edu.tk.remote.IGameServer;

@SuppressWarnings("serial")
public class FlyGame extends JPanel {
	private static final int DELAY = 950;
	private Random generator = new Random();
	private ImageIcon image;
	private Timer timer;
	public static Position newPosition;
	private int x, y;
	private int catchCount = 0;
	private String playerName;
	public static Map<String, Integer> gameData = new HashMap<String, Integer>();

	private static List<JFrame> frames = new ArrayList<JFrame>();
	private static IGameServer iGameServer;

	public FlyGame(String playerName) {
		this.playerName = playerName;
		image = new ImageIcon("bee.png");
		timer = new Timer(DELAY, new MoveListener());
		if (newPosition == null) {
			newPosition = new Position();
			newPosition.setX(generator.nextInt(1000));
			newPosition.setY(generator.nextInt(550));
		} else {
			x = newPosition.getX();
			y = newPosition.getY();
		}
		System.out.println("Positions of x,y : " + x + "," + y);
		addMouseListener(new MouseClickedListener());
		setPreferredSize(new Dimension(1200, 1000));
		timer.start();
	}

	// Draws the image.
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		image.paintIcon(this, page, x, y);
		setFont(new Font("Arial", Font.BOLD, 35));
		int count = 0;
		for (String player : gameData.keySet()) {
			page.drawString(player, 10 + (count++ * 100), 70);
			page.drawString("" + gameData.get(player), 10 + (count++ * 100), 105);
			setFont(new Font("Arial", Font.BOLD, 35));
		}
	}

	// Method for moving the image.
	public void move() {
		timer.start();
		x = newPosition.getX();
		y = newPosition.getY();
		if (timer.isRunning()) {
			x = newPosition.getX();
			y = newPosition.getY();
		}
		repaint();
	}

	// Method for getting the number of times caught.
	public int getCatchCount() {
		return catchCount;
	}

	// Makes the image move
	private class MoveListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			move();
			repaint();
		}
	}

	public void logout() {
		try {
			iGameServer.logout(playerName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// Records when the user clicks the image.
	private class MouseClickedListener extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if ((event.getButton() == MouseEvent.BUTTON1) && between(event.getX(), x, x + image.getIconWidth())
					&& between(event.getY(), y, y + image.getIconHeight())) {
				updateCount(playerName);
			}
		}

		private void updateCount(String playerName) {
			try {
				iGameServer.huntFly(playerName);
				// the java wait() gave an error. Hence the for loop to simulate
				// a small wait.
				for (int i = 0; i < 10000; i++) {

				}
				Set<String> names = gameData.keySet();
				for (String name : names) {
					if (playerName.equals(name)) {
						catchCount = gameData.get(name);
					}
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean between(int x, int lower, int upper) {
		return (x >= lower) && (x <= upper);
	}

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			args = new String[4];
			args[0] = "Bhargav";
			args[1] = "Disha";
			args[2] = "Aditi";
			args[3] = "Adarsh";
		}

		System.setProperty("java.security.policy", "file:./security.policy");
		try {
			System.setSecurityManager(new RMISecurityManager());
			iGameServer = (IGameServer) Naming.lookup("rmi://localhost/gameServerService");
			for (String name : args) {
				login(name);
				JFrame frame = new JFrame("Catch the bug!");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(new FlyGame(name));
				frame.pack();
				frame.setVisible(true);
				frames.add(frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("RMI Client exception: " + e);
		}

	}

	private static void login(String playerName) throws RemoteException, MalformedURLException, NotBoundException {
		IGameClient client = new GameClientService();
		iGameServer.login(playerName, client);
	}

}