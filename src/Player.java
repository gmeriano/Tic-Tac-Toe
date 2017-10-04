import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Player {
	
	private String name;
	private Image img;
	private SurfacePainter dc;
	private Graphics2D g2d;
	
	public Player(String nm, Image image, SurfacePainter sp, Graphics2D g) {
		name = nm;
		img = image;
		dc = sp;
		g2d = g;
		
	}
	
	//places and X in given location on board (xPos, yPos)
	public void placePiece(int xPos, int yPos) throws IOException {
		//places image at about screen (width / 6) plus the screen width times given xPos, and screen (height / 6) plus the (height / 3) * height
		g2d.drawImage(img, (Main.WIDTH/6-100)+(1+(Main.WIDTH/3)*xPos), (Main.HEIGHT/6-100)+(1+(Main.HEIGHT/3)*yPos), null);
		dc.repaint(); dc.revalidate();
	}
}
