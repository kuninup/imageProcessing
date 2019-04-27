package ImageProcessing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;

public class PListener extends  MouseAdapter implements ActionListener{
	
	private String command;
	//private MainFrame mainFrame;
	public Graphics g;
	private BufferedImage bi;
	private  ProcessingFunction p = new  ProcessingFunction();
	//����
	public PListener(BufferedImage bi) {
		this.bi = bi;
//		System.out.println(g);
//		System.out.println(bi);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.command = e.getActionCommand();
		if(command.equals("ԭͼ")) {
			g.drawImage(bi, 20, 20, bi.getWidth(), bi.getHeight(), null);
			//System.out.println("ԭͼ");
		}
		else if(command.equals("�ҶȻ�")) {
			Color[][] allcolor= p.img2color(bi);
			p.graying(allcolor);
			BufferedImage bi2 = p.color2img(allcolor);
			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
			//System.out.println("�ҶȻ�");
		}
		else if(command.equals("����")) {
			Color[][] allcolor= p.img2color(bi);
			p.reverseColor(allcolor);
			BufferedImage bi2 = p.color2img(allcolor);
			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
			//System.out.println("����");
		}
		else if(command.equals("��˹ģ��")) {
			Color[][] allcolor= p.img2color(bi);
			p.gaussBlur(allcolor, 6,3.5f);
			BufferedImage bi2 = p.color2img(allcolor);
			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
			//System.out.println("��˹ģ��");
		}
		else if(command.equals("����")) {
			Color[][] allcolor= p.img2color(bi);
			p.toSketch(allcolor);
			BufferedImage bi2 = p.color2img(allcolor);
			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
//			try {
//				ImageIO.write(bi2, "jpg", new File("C:\\Users\\������\\Pictures\\������.jpg"));
//			} catch (IOException e1) {
//				
//				e1.printStackTrace();
//			}
		}
//		else if(command.equals("������")) {
//			Color[][] allcolor= p.img2color(bi);
//			p.haHaMirror(allcolor);
//			BufferedImage bi2 = p.color2img(allcolor);
//			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
////			try {
////				ImageIO.write(bi2, "jpg", new File("C:\\Users\\������\\Pictures\\��(2).jpg"));
////			} catch (IOException e1) {
////				
////				e1.printStackTrace();
////			}
//		}
	}
	
	public void mouseDragged(MouseEvent e) {
       int x=e.getX();
       int y=e.getY();
        
         if(command.equals("������")) {
			Color[][] allcolor= p.img2color(bi);
			p.haHaMirror(allcolor,x,y);
			BufferedImage bi2 = p.color2img(allcolor);
			g.drawImage(bi2, 20, 20, bi2.getWidth(), bi2.getHeight(), null);
//			try {
//				ImageIO.write(bi2, "jpg", new File("C:\\Users\\������\\Pictures\\��(2).jpg"));
//			} catch (IOException e1) {
//				
//				e1.printStackTrace();
//			}
		}
    }

}
