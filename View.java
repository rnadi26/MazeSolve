import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View extends JFrame{
	private int [][] maze = 
        { {0,0,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,1,0,1,0,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,1,0,1,1,1,0,1},
          {1,0,0,0,1,1,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,0,0,1},
          {1,0,1,0,1,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,1,0,1},
          {1,0,0,0,0,0,0,0,0,0,0,0,1},
          {1,1,1,1,1,1,1,1,1,1,1,0,3}
        };
	
	private final List<Integer> path=new ArrayList<Integer>();
	private int pindex;
	
	public View() {
		setTitle("Simple Maze Solver");
		setSize(640,480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Dfs.searchPath(maze, 1, 1, path);
//		pindex=path.size()-2;
//		System.out.println(path);
	}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for(int i=0;i<maze.length;i++) {
				for(int j=0;j<maze[0].length;j++) {
					Color color;
					switch(maze[i][j]) {
					case 1 : color=Color.BLACK;
					break;
					case 3 : color=Color.RED;
					break;
					default : color=Color.WHITE;
					break;
					}
					g.setColor(Color.BLACK);
					g.drawRect(35*(j+1),35*(i+1),35,35);
					g.setColor(color);
					g.fillRect(35*(j+1)+1,35*(i+1)+1,35,35);
				}
			}
			RatInMaze rat=new RatInMaze(maze.length,maze[0].length);
			rat.solveMaze(maze,0,0,maze.length-1,maze[0].length-1);
			for(int i=0;i<rat.sol.length;i++) {
				for(int j=0;j<rat.sol[0].length;j++) {
					System.out.print(rat.sol[i][j]+" ");
					if(rat.sol[i][j]==1 && maze[i][j]!=3) {
					g.setColor(Color.GREEN);
					g.fillRect(35*(j+1),35*(i+1),35,35);
					}
				}
				System.out.println();
			}
//		for(int p=0;p<path.size();p+=2) {
//			int px=path.get(p);
//			int py=path.get(p+1);
//			g.setColor(Color.GREEN);
//			g.fillRect(px*30,py*30,30,30);
//		}
		
//		int px=path.get(pindex);
//		int py=path.get(pindex+1);
//		g.setColor(Color.RED);
//		g.fillOval(px*30,py*30,30,30);
//		
		}
		
		
//		@Override
//		protected void processKeyEvent(KeyEvent ke) {
//	        if (ke.getID() != KeyEvent.KEY_PRESSED) {
//	            return;
//	        }
//	        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
//	            pindex -= 2;
//	            if (pindex < 0) {
//	                pindex = 0;
//	            }
//	        }
//	        else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
//	            pindex += 2;
//	            if (pindex > path.size() - 2) {
//	            	pindex = path.size() - 2;
//	            }
//	        }
//	        repaint(); 
//	    }
		
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					View view=new View();
					view.setVisible(true);
				}
			});
		}
	}
