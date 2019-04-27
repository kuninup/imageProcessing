package FireWork;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
 
public class MainUI extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private Image im1 = new ImageIcon("夜空.jpg").getImage();
 
	public static void main(String[] args) {
		new MainUI().init();
		
	}
 
	public void init() {
		this.setUndecorated(true);  //隐藏菜单栏
		this.setSize(1000, 800);
		this.setTitle("FireWork");
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		//添加监听器
		
		this.setVisible(true);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//创建匿名对象，传递窗口（this）参数
				//启动线程
				new Thread(new ParticleControl(MainUI.this)).start();

//				//移除监听器
//				MainUI.this.removeMouseListener(this);
			}
		});
	}
	public void paint(Graphics g) {
		super.paint(g);	
		g.drawImage(im1,0, 0, null);
	}
}

