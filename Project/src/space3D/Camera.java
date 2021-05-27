package space3D;

public class Camera {
	public Point camPosition;
	public double angleHeight;	// Goc cao
	public double angleWidth;	// Goc rong
	
	public Camera(Point camPosition, double angleHeight, double angleWidth) {
		super();
		this.camPosition = camPosition;
		this.angleHeight = angleHeight;
		this.angleWidth = angleWidth;
	}

	public double getAngleHeight() {
		return angleHeight;
	}
	public void setAngleHeight(double angleHeight) {
		this.angleHeight = angleHeight;
	}
	public double getAngleWidth() {
		return angleWidth;
	}
	public void setAngleWidth(double angleWidth) {
		this.angleWidth = angleWidth;
	}
}