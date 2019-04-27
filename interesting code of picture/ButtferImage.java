
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ButtferImage extends JFrame {

	private Image im1 = new ImageIcon("»Áª®.gif").getImage();
	private Image im2 = new ImageIcon("≈‡≈‡.jpg").getImage();
	private Graphics g;
	
	
	public void showUI(){
	
		JFrame jf = new JFrame();
		jf.setTitle("À´ª∫≥Â");
		jf.setSize(1200, 700);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		 g = jf.getGraphics();
		
		new Thread(){
		  public void run(){
			while(true){
				try {			
					sleep(100);
		BufferedImage bfi = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics  bfig = bfi.getGraphics();	
		bfig.drawImage(im2, 0, 0,null);
		bfig.drawImage(im1,60, 420, null);
		
			
		g.drawImage(bfi, 0, 0, null);	
//		g.setColor(Color.red);
//		g.drawLine(110, 530, 250, 300);
			
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		  }
		}.start();
		
		
	}
	
//	public void paint() {
//		super.paint(g);
//		
//		g.setColor(Color.red);
//		g.drawLine(210, 530, 306, 407);
//
//	}
//	
	public static void main(String []args){
		new ButtferImage().showUI();
	}
}
