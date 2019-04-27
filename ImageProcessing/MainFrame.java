package ImageProcessing;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame {

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.initUI();

	}

	
	
	private void initUI() {
		JFrame jf = new JFrame();
		jf.setSize(1200, 1000);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(3);
		jf.setLayout(new FlowLayout());
		
		ProcessingFunction p = new  ProcessingFunction();
		BufferedImage bi = p.imageRead("test.jpg");
		PListener plis = new PListener(bi);
		
		String[] commands = {"原图","灰度化","反相","高斯模糊","素描","哈哈镜"};
		for(int i=0;i<commands.length;i++) {
			JButton bu = new JButton(commands[i]);
			jf.add(bu);
			bu.addActionListener(plis);
		}
		jf.addMouseMotionListener(plis);
		
		jf.setVisible(true);
		plis.g = jf.getGraphics();
	}

}
