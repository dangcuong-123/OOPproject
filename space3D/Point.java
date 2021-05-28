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
	
	public double calcAreaFrom3Points (Point p1, Point p2, Point p3) {
		double sum = 0;
		sum += (p1.getX() * p2.getY() - p1.getY() * p2.getX())
				+ (p2.getX() * p3.getY() - p2.getY() * p3.getX())
				+ (p3.getX() * p1.getY() - p3.getY() * p1.getX());
		
		double area = Math.abs(sum / 2);
		return area;
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
		if(Math.abs(this.x - o.getX()) <= 1e-5) {
        	if(Math.abs(this.y - o.getY()) <= 1e-5) {
        		if(this.z > o.getZ())
        			return 1;
        		else return -1;
        	}
        	else
        		if(this.y > o.getY())
        			return 1;
        		else return -1;
        		//return (int)(this.y - o.getY());
        }
        else
        	if(this.x > o.getX())
    			return 1;
    		else return -1;
	}

}
