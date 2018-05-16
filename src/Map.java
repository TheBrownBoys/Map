import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {

	private static final int frameX = 640, frameY = 480;
	private static final String gameName = "Tanks v1";
	public static boolean isPlaying;
	Shot shot;
	Tank tank1, tank2;

	public Map() 
	{
		reset();
	}
	
	public void reset() {
		shot = new Shot(frameX, frameY);
		tank1 = new Tank(frameX, frameY, 0);
		tank2 = new Tank(frameX, frameY, 1);
		isPlaying = true;
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) 
			{
			}
			public void keyReleased(KeyEvent e) 
			{
				tank1.keyReleased(e);
				tank2.keyReleased(e);
			}
			public void keyPressed(KeyEvent e) 
			{
				tank1.keyPressed(e);
				tank2.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	/*After WWII, the British set up a lab to specialize their tanks
	to fight should there be another World War; One tank is trying
	to break out, while the other specialized tanks try to kill it.*/
	
	
	public void checkPoints() {
		if (shot.getY()<=0) {
			tank2.addPoint();
//			System.out.println("player 2 points: " + tank2.getPoints());
		} else if (shot.getY() + shot.getvelY()>=frameY-(shot.getRadius()+25)) { // TODO fix this shit
			tank1.addPoint();
//			System.out.println("player 1 points: " + tank1.getPoints());
		}
	}
	
	public void checkIntersection() {
		if (shot.getBounds().intersects(tank1.getBounds()))
			shot.reverseDirection();
		else if (shot.getBounds().intersects(tank2.getBounds()))
			shot.reverseDirection();
	}

	public void update() {
		tank1.update();
		tank2.update();
		shot.update();
		checkIntersection();
		checkPoints();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paint(g);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, frameX, frameY);
		tank1.draw(g2d);
		tank2.draw(g2d);
		shot.draw(g2d);
	}
	
	public static void main(String args[]) throws InterruptedException {
		JFrame frame = new JFrame(gameName);
		Map table = new Map();
		frame.add(table);
		frame.setSize(frameX, frameY);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(table.isPlaying) {
			table.update();
			table.repaint();
			Thread.sleep(15);
		}
		System.exit(0);
	}
}