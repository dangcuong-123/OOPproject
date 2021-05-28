package space3Dversion1;

public class Camera {
	public Point camPosition;
	public double angleHeight;	// Goc cao
	public double angleWidth;	// Goc rong
	public Plane3D upperPlane; //mat phang phia tren camera
	
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
	
	public Plane3D getUpperPlane() {
		return upperPlane;
	}

	public void setUpperPlane(Plane3D upperPlane) {
		this.upperPlane = upperPlane;
	}
}
