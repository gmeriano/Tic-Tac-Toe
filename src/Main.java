import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame implements MouseListener {

	public static final int FULL_HEIGHT = 960;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = FULL_HEIGHT - 100;
	
	public static int hasWon = 0;
	public static boolean turn = true; //starts on Xs turn
	
	public static int[][] board = new int[3][3];
		
	//sets up JFrame and runs mouse Listener
	public static void main(String argv[]){		
		
		//setup JFrame
		JFrame window = new JFrame();
		window.setSize(WIDTH,FULL_HEIGHT);
		window.setTitle("Tic-Tac-Toe!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		SurfacePainter dc = new SurfacePainter();
		window.add(dc);	
		dc.clearBoard();
		
		//setup mouse listener that gets mouse location and calculates which position on board that is
		window.addMouseListener(new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				//only run if the game is still going
				if (hasWon == 0) {
					int x = e.getX() / (WIDTH/3);
					int y = e.getY() / (HEIGHT/3);
					if (turn == true) { //if it is Xs turn
						if (board[x][y] == 0) {
							board[x][y] = 1;
							turn = !turn;
						}
					}
					else { //If it is Os turn
						if (board[x][y] == 0) {
							board[x][y] = 2;
							turn = !turn;
						}
					}
				}
				hasWon = hasWon();
			}
		});
		
	}

	//checks to see if anyone has has the game. 1 means someone has won. 0 means no one has won. 2 means cat game
	public static int hasWon() {
		if ((board[0][0] != 0 && board[0][0] == board[1][0] && board[0][0] == board[2][0]) ||
			(board[0][1] != 0 && board[0][1] == board[1][1] && board[0][1] == board[2][1]) ||
			(board[0][2] != 0 && board[0][2] == board[1][2] && board[0][2] == board[2][2]) ||
			(board[0][0] != 0 && board[0][0] == board[0][1] && board[0][0] == board[0][2]) ||
			(board[1][0] != 0 && board[1][0] == board[1][1] && board[1][0] == board[1][2]) ||
			(board[2][0] != 0 && board[2][0] == board[2][1] && board[2][0] == board[2][2]) ||
			(board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
			(board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0])) {
			
			return 1;
		}
		for (int i = 0; i < Main.board.length; i++) 
			for (int j = 0; j < Main.board.length; j++) 
				if (board[i][j] == 0)
					return 0;
		return 2;
	}
	
	
	
	
	//These methods are needed for mouseListener
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
