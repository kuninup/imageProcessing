package FireWork;

public class Vect {

	public double x,y;
	
	public Vect(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	//向量加法
	public Vect  add(Vect v){
		return new Vect(this.x+v.x,this.y+v.y);
	}
	//向量乘法
	public Vect multiply(double d){
		return new Vect(this.x*d,this.y*d);
	}
}
