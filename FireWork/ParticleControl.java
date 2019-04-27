package FireWork;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


public class ParticleControl implements Runnable ,Config{
	private JFrame jf;
	private ArrayList<Particle> plist = new ArrayList<Particle>();
	private int j;// 烟花发数
	private Graphics bg;
	public ParticleControl(JFrame jf) {
		this.jf = jf;
	}
	@Override
	public void run() {
		raise();

	}
	
	// 烟花上升
		public void raise() {
			// 20发
			for (j = 0; j < 10; j++) {
				// 创建粒子（烟花）
				Particle rp = new Particle();
				
				Random rand = new Random();
				int deepx = rand.nextInt(400)+150;
				rp.position = new Vect(deepx,800);
//				rp.position = PSTART;
				rp.velocity = VSTART;
				rp.acceleration = ASTART;
				rp.color = new Color(255, 150, 150);
				rp.width = SIZE / 3;
				rp.height = SIZE;
				
				//调用音效
				Music music = new Music("烟花.wav");
				music.start();
				
				//设定上升粒子生命周期
				rp.life = 65 + new Random().nextInt(50);
				for (rp.age = 1; rp.age < rp.life; rp.age++) {
					// 粒子位置
					rp.position = rp.position.add(rp.velocity.multiply(DT));
					rp.velocity = rp.velocity.add(rp.acceleration.multiply(DT));
					// 绘制粒子
					bg = jf.getGraphics();
					bg.setColor(rp.color);
					bg.fillOval(rp.getX(), rp.getY(), rp.width, rp.height);				
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 调用爆炸效果
				bomp(rp);
				
				if(j==4) {
					Graphics2D g2d = (Graphics2D) jf.getGraphics();  
		            g2d.setColor(Color.red);
		            Font font=new Font("宋体",Font.PLAIN,64);  
		            g2d.setFont(font);  
		            // 抗锯齿
		          
		            g2d.drawString("Best Wishes For You",200,150);  
		            // 释放对象  
		
				}
			}

		}
		
		// 璀璨烟花
		public void bomp(Particle rp) {
			//设定爆炸粒子生命周期
			rp.life = 40 + new Random().nextInt(30);
			for (rp.age = 1; rp.age < rp.life; rp.age++) {
				for (int i = 0; i < 30; i++) {
					Particle p = new Particle();
					p.position = new Vect(rp.getX(), rp.getY());
					p.velocity = new Vect(2, -10);
					p.acceleration = particleDirection();
					p.color = new Color(200 + new Random().nextInt(55), (new Random().nextInt(50) + (i + j) * 100) % 255,
							new Random().nextInt(255));
					p.width = SIZE / 3;
					p.height = SIZE / 3;
					plist.add(p);
				}
				for (Particle p : plist) {
					// 计算每个粒子的下一位置
					p.position = p.position.add(p.velocity.multiply(DT));
					p.velocity = p.velocity.add(p.acceleration.multiply(DT));
					// 画到缓冲区
					bg.setColor(p.color);
					bg.fillOval(p.getX(), p.getY(), p.width, p.height);
				}
				try {
					Thread.sleep(20);
				} catch (Exception ef) {
				}
			}
			plist.clear();
		}
		
		// 生成一个随机方向（粒子加速度）
		public static Vect particleDirection() {
			double theta = Math.random() * 2 * Math.PI;
			return new Vect(100 + (Math.cos(theta) * 700), 100 + (Math.sin(theta) * 700));
		}
	 
	 

}
