package space3D;

public class Rectangle {
	
	public Point vertexA;
	public Point vertexB;
	public Point vertexC;
	public Point vertexD;
	public Point getVertexA() {
		return vertexA;
	}
	public void setVertexA(Point vertexA) {
		this.vertexA = vertexA;
	}
	public Point getVertexB() {
		return vertexB;
	}
	public void setVertexB(Point vertexB) {
		this.vertexB = vertexB;
	}
	public Point getVertexC() {
		return vertexC;
	}
	public void setVertexC(Point vertexC) {
		this.vertexC = vertexC;
	}
	public Point getVertexD() {
		return vertexD;
	}
	public void setVertexD(Point vertexD) {
		this.vertexD = vertexD;
	}
	public Rectangle(Point p0, Point p1,Point p2,Point p3) {
		Vector3D v01 = new Vector3D(p0, p1);
		Vector3D v02 = new Vector3D(p0, p2);
		Vector3D v03 = new Vector3D(p0, p3);
		vertexA = p0;
		if(Calculate3D.scalar(v01, v02)==0) {
			vertexB = p1;
			vertexC = p3;
			vertexD = p2;
		}else {
			if(Calculate3D.scalar(v01, v03)==0) {
				vertexB = p1;
				vertexC = p2;
				vertexD = p3;
			}else {
				vertexB = p2;
				vertexC = p1;
				vertexD = p3;
			}
		}
	}
	@Override
	public String toString() {
		return "Rectangle [vertexA=" + vertexA + ", vertexB=" + vertexB + ", vertexC=" + vertexC + ", vertexD="
				+ vertexD + "]";
	}
}
