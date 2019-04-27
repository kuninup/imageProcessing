package FireWork;



public interface Config {
	public static final int SIZE = 16;//粒子大小
	public static final double DT  = 0.01;//时间变量
	public static final Vect PSTART=new Vect(400,700);//粒子初始位置
	public static final Vect VSTART=new Vect(0,-500);//粒子初始速度
	public static final Vect ASTART=new Vect(0,200);//粒子初始加速度
}
