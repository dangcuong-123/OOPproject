package space3D;

public class Vector2D3D {
	public double x;
	public double y;
	public double z;
	
	public Vector2D3D() {}
	
	public Vector2D3D(double x, double y) {
		super();
		setX(x);
		setY(y);
	}
	
	public Vector2D3D(double x, double y, double z) {
		super();
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public double norm2_2D(Vector2D3D vector) {
		return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
	}
	
	public double norm2_3D(Vector2D3D vector) {
		return Math.sqrt(vector.getX() * vector.getX()
				+ vector.getY() * vector.getY()
				+ vector.getZ() * vector.getZ());
	}
	
	public Vector2D3D getVector_2D(Point p1, Point p2) {
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		return new Vector2D3D(x, y);
	}
	
	public Vector2D3D getVector_3D(Point p1, Point p2) {
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		double z = p1.getZ() - p2.getZ();
		return new Vector2D3D(x, y, z);
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
