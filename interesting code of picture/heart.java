

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class heart {	
	public Graphics2D g;	
	public static void main(String[] args) {	
		heart draw = new heart();
		draw.drawxin();
	}		
		private void drawxin(){   
			JFrame frame = new JFrame();
			frame.setTitle("Draw");
			frame.setSize(700, 800);
			frame.getContentPane().setBackground(Color.BLACK);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(3);
			frame.setVisible(true);
			g = (Graphics2D) frame.getGraphics();
			for(int i=0;i<=180;i++){     
				for(int j=0;j<=180;j++){  
					double r=Math.PI/45*i*(1-Math.sin(Math.PI/45*j))*20;   
					double x=r*Math.cos(Math.PI/45*j)*Math.sin(Math.PI/45*i)+300;   
					double y=-r*Math.sin(Math.PI/45*j)+200;    
					Color c=Color.getHSBColor(i*j/8100.0f, 0.9999f,0.9999f);   
					g.setColor(c);      g.drawOval((int)x, (int)y, 1,1);  
					
					}     
				}   
			} 		 
}		 

