package space3D;

public class Triangle {
	public Point vertexA;
	public Point vertexB;
	public Point vertexC;

	public Triangle(Point p0, Point p1, Point p2) {
		vertexA = p0;
		vertexB = p1;
		vertexC = p2;
	}
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
	@Override
	public String toString() {
		return "Triangle [vertexA=" + vertexA + ", vertexB=" + vertexB + ", vertexC=" + vertexC + "]";
	}
	
}