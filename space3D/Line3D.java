package space3D;

public class Line3D {
	public Vector3D u;
	private double x0, y0, z0;
	private double a, b, c;

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

	public double getZ0() {
		return z0;
	}

	public void setZ0(double z0) {
		this.z0 = z0;
	}

	public Vector3D getU() {
		return u;
	}

	public void setU(Vector3D u) {
		this.u = u;
	}

	public Line3D() {
	}

	/**
	 * phuong trinh duong thang di qua 2 diem
	 * 
	 * @param x
	 * @param y
	 */
	public Line3D(Point x, Point y) {
		u = new Vector3D(x, y);
		this.x0 = x.getX();
		this.y0 = x.getY();
		this.z0 = x.getZ();

		this.a = u.getX();
		this.b = u.getY();
		this.c = u.getZ();
	}
	/**
	 * phuong trinh duong thang khi co diem qua va vector chi phuong
	 */
	
	public Line3D(Point x , Vector3D u){
		this.u = u;
		this.x0 = x.getX();
		this.y0 = x.getY();
		this.z0 = x.getZ();

		this.a = u.getX();
		this.b = u.getY();
		this.c = u.getZ();
	}
	

}
