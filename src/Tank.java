import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Tank {
	
	private int frameX, frameY, id;
	private int x, y, width, height, velX, velY;
	private String name;
	private int points;
	private Shot shot;
	
	public Tank(int frameX, int frameY, int id) {
		this.frameX = frameX;
		this.frameY = frameY;
		this.id = id;
		shot = new Shot(frameX, frameY);
		width = 75;
		height = 10;
		velX = 0;
		velY = 0;
		x = frameX/2 - width/2;
		if (id==0)
			y = 15;
		else
			y = frameY - 55;
		name = createPlayer();
		points = 0;
	}
	
	private String createPlayer() {
		System.out.println("Enter player " + (id+1) + " name: ");
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
	
	public void checkBoundry() {
		if (x <= 3) 
		{
			x = 1;
		} else if (x + width >= frameX-10) 
		{
			x = frameX-width-15;
		}
		if (y < 1) 
		{
			y = 1;
		} else if (y + height >= frameY-20) 
		{
			y = frameY-height-20;
		}
	}
	
	public void update() {
		checkBoundry();
		x+=velX;
		y+=velY;
	}
	
	public void draw(Graphics2D g2d) {
		/* print the Tank and scores */
		if (id==0) {
			g2d.setColor(Color.PINK);;
			g2d.drawString(name + ": " + points, 5, 25);
		} else {
			g2d.setColor(Color.CYAN);
			g2d.drawString(name + ": " + points, frameX - 75, frameY - 45);
		}
		g2d.fillRect(x, y, 35, 35);
	}
	
	public void keyReleased(KeyEvent e) {
		if (id==0) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				velX = 0;
			if (e.getKeyCode() == KeyEvent.VK_D)
				velX = 0;
			if (e.getKeyCode() == KeyEvent.VK_W)
				velY = 0;
			if (e.getKeyCode() == KeyEvent.VK_S)
				velY = 0;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				velX = 0;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				velX = 0;
			if (e.getKeyCode() == KeyEvent.VK_UP)
				velY = 0;
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				velY = 0;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (id==0) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				velX = -5;
			if (e.getKeyCode() == KeyEvent.VK_D)
				velX = 5;
			if (e.getKeyCode() == KeyEvent.VK_W)
				velY = -5;
			if (e.getKeyCode() == KeyEvent.VK_S)
				velY = 5;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				velX = -5;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				velX = 5;
			if (e.getKeyCode() == KeyEvent.VK_UP)
				velY = -5;
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				velY = 5;
		}
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,35, 35);
	}
	
	public int getPoints() 
	{
		return points;
	}
	
	public void addPoint() 
	{
	}

	public String getName() 
	{
		return name;
	}
}