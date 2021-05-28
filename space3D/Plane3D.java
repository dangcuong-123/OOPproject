package space3D;

public class Plane3D {
	private Vector3D n;
	private double a;
	private double b;
	private double c;
	private double d;
	
	public Plane3D() {}
	
	public Plane3D(Vector3D n, Point p) {
		super();
		this.n = n;
		this.a = n.getX();
		this.b = n.getY();
		this.c = n.getZ();
		
		this.d = -(p.getX()*this.a + p.getY()*this.b + p.getZ()*this.c);
	}

	public Plane3D(Point x1, Point x2, Point x3) {
		Vector3D u1 = new Vector3D(x1,x2);
		Vector3D u2 = new Vector3D(x1,x3);
		
		this.n = Calculate3D.det(u1, u2);
		this.a = n.getX();
		this.b = n.getY();
		this.c = n.getZ();
		
		this.d = -(x1.getX()*this.a + x1.getY()*this.b + x1.getZ()*this.c);
	}

	public Vector3D getN() {
		return n;
	}

	public void setN(Vector3D n) {
		this.n = n;
	}

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

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
	
}
