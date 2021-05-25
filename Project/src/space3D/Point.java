package space3D;

public class Point implements Comparable<Object> {
	public double x;
	public double y;
	public double z;
	
	public Point () {}
	public Point (double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public double distanceTwoPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2)
				+ Math.pow((p1.getY() - p2.getY()), 2));
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
	
	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		Point o = (Point)obj;
		if(this.x - o.getX() == 0) {
        	if(this.y - o.getY() == 0) {
        		return (int)(this.z - o.getZ());
        	}
        	else
        		return (int)(this.y - o.getY());
        }
        else
        	return (int)(this.x - o.getX());
	}
	

}