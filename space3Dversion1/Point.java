package space3Dversion1;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
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
