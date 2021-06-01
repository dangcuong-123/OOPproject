package space3D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Camera {
	public Point camPosition;
	public double angleHeight; // Goc cao
	public double angleWidth; // Goc rong
	public Plane3D topPlane; // mat phang camera nam tren
	public Plane3D botPlane;// mat phang doi cam
	public Plane3D oppositePlane;
	public double heightCam; // tam xa camera
	public List<Point> listPointInOppsite = new ArrayList<Point>();
	public Point projectionInOp;
	public Image img;
	public int numberOp =0;
	
	public Camera(Point camPosition, double angleHeight, double angleWidth) {
		
		super();
		this.camPosition = camPosition;
		this.angleHeight = Math.toRadians(angleHeight);
		this.angleWidth = Math.toRadians(angleWidth);
	}

// Lay 4 dinh cua vung nhin thay
	public void setlistPointInOppsite() {
		// Tinh chieu cao chop tu giac
		this.heightCam = Calculate3D.distancePointPlane(camPosition, oppositePlane);
		// Tim hinh chieu camera den mat doi dien
		projectionInOp = Calculate3D.ProjectionPointToPlane(this.getCamPosition(), oppositePlane);
		double deltaZ = this.heightCam * Math.tan(angleHeight / 2);
		if (Calculate3D.scalar(oppositePlane.getN(), new Vector3D(1, 0, 0)) == 0) {
			double deltaX = this.heightCam * Math.tan(angleWidth / 2);
			listPointInOppsite.add(
					new Point(projectionInOp.getX() + deltaX, projectionInOp.getY(), projectionInOp.getZ() + deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX() - deltaX, projectionInOp.getY(), projectionInOp.getZ() + deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX() + deltaX, projectionInOp.getY(), projectionInOp.getZ() - deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX() - deltaX, projectionInOp.getY(), projectionInOp.getZ() - deltaZ));
		} else {
			double deltaY = this.heightCam * Math.tan(angleWidth / 2);
			listPointInOppsite.add(
					new Point(projectionInOp.getX(), projectionInOp.getY() + deltaY, projectionInOp.getZ() + deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX(), projectionInOp.getY() - deltaY, projectionInOp.getZ() + deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX(), projectionInOp.getY() + deltaY, projectionInOp.getZ() - deltaZ));
			listPointInOppsite.add(
					new Point(projectionInOp.getX(), projectionInOp.getY() - deltaY, projectionInOp.getZ() - deltaZ));
		}
	}

	// Check 1 diem co thuoc vung nhin thay cua camera ko
	public boolean checkPointInVisibleAreaOfCam(Point position) {
		Rectangle rec = new Rectangle(listPointInOppsite.get(0), listPointInOppsite.get(1), listPointInOppsite.get(2),
				listPointInOppsite.get(3));
		
		Point insecPosition = Calculate3D.intersectionLinePlane(new Line3D(position ,this.camPosition), oppositePlane);
		if(insecPosition == null) return false;
		double areaRec = Calculate3D.areaRectangle(rec);
		double sumTri = Calculate3D.areaTriangle(new Triangle(insecPosition, rec.getVertexA(), rec.getVertexB()))
				+ Calculate3D.areaTriangle(new Triangle(insecPosition, rec.getVertexB(), rec.getVertexC()))
				+ Calculate3D.areaTriangle(new Triangle(insecPosition, rec.getVertexC(), rec.getVertexD()))
				+ Calculate3D.areaTriangle(new Triangle(insecPosition, rec.getVertexD(), rec.getVertexA()));
	
		return Math.abs(areaRec - sumTri) < 1e-5;
	}

	public Point getCamPosition() {
		return camPosition;
	}

	public void setCamPosition(Point camPosition) {
		this.camPosition = camPosition;
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

	public Plane3D gettopPlane() {
		return topPlane;
	}

	public void settopPlane(Plane3D upperPlane) {
		this.topPlane = upperPlane;
	}

	public Plane3D getTopPlane() {
		return topPlane;
	}

	public void setTopPlane(Plane3D topPlane) {
		this.topPlane = topPlane;
	}

	public Plane3D getOppositePlane() {
		return oppositePlane;
	}

	public void setOppositePlane(Plane3D oppositePlane,int k) {
		this.oppositePlane = oppositePlane;
		this.numberOp = k;
		this.setlistPointInOppsite();
		this.settopPlane(new Plane3D(this.camPosition, listPointInOppsite.get(0),listPointInOppsite.get(1)));
		img = new Image();

	}
	class Image{
		public Point[][] matrixPoint ;
		public Color[][] matrixImg;
		public int widthImg, heightImg;
		public Image(){
		this.widthImg = (int)(Math.cos(angleWidth)*Math.sqrt(300000/(Math.cos(angleWidth)*Math.cos(angleHeight))));
		this.heightImg = (int)(Math.cos(angleHeight)*Math.sqrt(300000/(Math.cos(angleWidth)*Math.cos(angleHeight))));
		this.matrixImg = new Color[widthImg][heightImg];
		this.matrixPoint = new Point[widthImg][heightImg];
		
		this.setMatrixPoint();
		}

		public void setMatrixPoint(){
			
			
			double scale=(listPointInOppsite.get(0).getZ()-listPointInOppsite.get(3).getZ())/heightImg;
		
			double x=projectionInOp.getX();
			double y=projectionInOp.getY();
			double z=projectionInOp.getZ();

			
			if (Calculate3D.scalar(oppositePlane.getN(), new Vector3D(1, 0, 0)) == 0) {
				if((projectionInOp.y-camPosition.y)>0)scale =-scale;
				for(int i = 0;i< widthImg;i++) {
					for(int j=0;j<heightImg;j++) {
					 matrixPoint[i][j]=new Point(x+(i-widthImg/2)*scale,y,z+(j-heightImg/2)*Math.abs(scale));
					}
				}
			}
			else {
				if((projectionInOp.x-camPosition.x)<0)scale =-scale;
				for(int i = 0;i< widthImg;i++) {
					for(int j=0;j<heightImg;j++) {
					 matrixPoint[i][j]=new Point(x,y+(i-widthImg/2)*scale,z+(j-heightImg/2)*Math.abs(scale));
					}
				}
			}

			System.out.println(matrixPoint[widthImg/2][0]);
		}
	}
}
