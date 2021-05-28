package space3D;

public class Vector3D {
	private double x;
	private double y;
	private double z;
	public Vector3D(Point a , Point b){
		this.x = a.getX() -  b.getX();
		this.y = a.getY() -  b.getY();
		this.z = a.getZ() -  b.getZ();
	}
	
	public Vector3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}

}