package FireWork;

import java.awt.Color;



public class Particle {
	//����λ�á��ٶȡ����ٶ�
	public Vect position, velocity, acceleration;
	//������ɫ
	public Color color;
	//�������������䡢��ȡ�����
	public int life,age,width,height;
 
	public Particle() {
	}
 
	public int getX() {
		return (int) this.position.x;
	}
 
	public int getY() {
		return (int) this.position.y;
	}
}
