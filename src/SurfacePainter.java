import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
//import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SurfacePainter extends JPanel {
	
	int toggle = 0;
	JButton playAgain = createButton();
	
	private void doDrawing(Graphics g) throws IOException{	
		Graphics2D g2d = (Graphics2D) g;
		drawGridLines(g2d);	
		
		//places Xs and Os on board
		for (int i = 0; i < Main.board.length; i++) {
			for (int j = 0; j < Main.board.length; j++) {
				if (Main.board[i][j] == 1)
					placeX(g2d,i,j);
				if (Main.board[i][j] == 2)
					placeO(g2d,i,j);
			}	
			//if someone has won
			if (Main.hasWon == 1) 
				//if X wins because if the current turn is false then that means X just placed the winning move
				if (Main.turn == false) {
					g2d.setColor(Color.GREEN);
					g2d.setFont(new Font("TimesRoman", Font.PLAIN, 75)); 
					//hardcoded locations
					g2d.drawString("THE WINNER IS X",Main.WIDTH/6+85,Main.HEIGHT/2);
					//only do this after the first game played
					if (toggle == 0) {
						this.add(playAgain);
						this.setLayout(null);
						//hardcoded
						playAgain.setBounds(Main.WIDTH/2-80,Main.HEIGHT+6,150,50);
						toggle = 1;
					}
					playAgain.setVisible(true);
				}
			//If O wins
				else {
					g2d.setColor(Color.GREEN);
					g2d.setFont(new Font("TimesRoman", Font.PLAIN, 75)); 
					//hardcoded locations
					g2d.drawString("THE WINNER IS O",Main.WIDTH/6+85,Main.HEIGHT/2);
					//only do this after the first game played
					if (toggle == 0) {
						this.add(playAgain);
						this.setLayout(null);
						//hardcoded
						playAgain.setBounds(Main.WIDTH/2-80,Main.HEIGHT+6,150,50);
						toggle = 1;
					}
					playAgain.setVisible(true);
				}
			//If the game ends in a tie 
			if (Main.hasWon == 2) {
				g2d.setColor(Color.GREEN);
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 75)); 
				//hardcoded locations
				g2d.drawString("CAT GAME",Main.WIDTH/6+235,Main.HEIGHT/2);
				//only do this after the first game played
				if (toggle == 0) {
					this.add(playAgain);
					this.setLayout(null);
					//hardcoded
					playAgain.setBounds(Main.WIDTH/2-80,Main.HEIGHT+6,150,50);
					toggle = 1;
				}
				playAgain.setVisible(true);
			}
			repaint();
			revalidate();
		}
		
	}
	
	//creates play again button 
	private JButton createButton() {
		JButton playAgain = new JButton("PLAY AGAIN");
		playAgain.addActionListener(new ActionListener() { 
			//resets board to play again on click and then hides button
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearBoard();
				playAgain.setVisible(false);
			} 
		});
		return playAgain;
	}
	
	//draws gridlines
	private void drawGridLines(Graphics2D g2d) {
		g2d.drawLine(Main.WIDTH/3, 0, Main.WIDTH/3, Main.HEIGHT);
		g2d.drawLine((int)(Main.WIDTH*(2.0/3)), 0, (int)(Main.WIDTH*(2.0/3)), Main.HEIGHT);
		g2d.drawLine(0, Main.HEIGHT/3, Main.WIDTH, Main.HEIGHT/3);
		g2d.drawLine(0, (int)(Main.HEIGHT*(2.0/3)), Main.WIDTH, (int)(Main.HEIGHT*(2.0/3)));
		g2d.drawLine(0,Main.HEIGHT,Main.WIDTH,Main.HEIGHT);
	}
	
	//clears board
	public void clearBoard() {
		Main.hasWon = 0;
		for (int i = 0; i < Main.board.length; i++)
			for (int j = 0; j < Main.board.length; j++)
				Main.board[i][j] = 0;
		repaint();
		revalidate();
	}
	
	//places and X in given location on board (xPos, yPos)
	private void placeX(Graphics2D g2d, int xPos, int yPos) throws IOException {
		InputStream input = ClassLoader.getSystemResourceAsStream("X.png");
		Image x = ImageIO.read(input);
		//places image at about screen (width / 6) plus the screen width times given xPos, and screen (height / 6) plus the (height / 3) * height
		g2d.drawImage(x, (Main.WIDTH/6-100)+(1+(Main.WIDTH/3)*xPos), (Main.HEIGHT/6-100)+(1+(Main.HEIGHT/3)*yPos), null);
		repaint(); revalidate();
	}
	
	//places and O in given location on board (xPos, yPos)
	private void placeO(Graphics2D g2d, int xPos, int yPos) throws IOException {
		InputStream input = ClassLoader.getSystemResourceAsStream("O.png");
		Image o = ImageIO.read(input);
		//places image at about screen (width / 6) plus the screen width times given xPos, and screen (height / 6) plus the (height / 3) * height
		g2d.drawImage(o, (Main.WIDTH/6-100)+(1+(Main.WIDTH/3)*xPos), (Main.HEIGHT/6-100)+(1+(Main.HEIGHT/3)*yPos), null);
		repaint(); revalidate();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try { //try-catch for doDrawing(g)
			doDrawing(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
