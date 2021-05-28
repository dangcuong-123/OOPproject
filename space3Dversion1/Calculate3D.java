package space3Dversion1;

public class Calculate3D {
	
	/**
	 * 
	 * @param u1 
	 * @param u2
	 * @return tich vo huong 2 vector
	 */
	public static double scalar(Vector3D u1 , Vector3D u2 ) {
		double k = u1.getX()*u2.getX() + u1.getY()*u2.getY() + u1.getZ()*u2.getZ() ;
		if(Math.abs(k) < 1e-5) k = 0;
		return k ;
	}
	/**
	 * 
	 * @param u1
	 * @param u2
	 * @return tich co huong cua 2 vector
	 */
	
	public static Vector3D det(Vector3D u1, Vector3D u2) {		
		return new Vector3D(u1.getY()*u2.getZ() - u1.getZ()*u2.getY(),
							u1.getZ()*u2.getX() - u1.getX()*u2.getZ(),
							u1.getX()*u2.getY() - u1.getY()*u2.getX());
	}
	
	/**
	 * 
	 * @param u
	 * @return do dai vector 
 	 */
	public static double lenghVector(Vector3D u){
		return Math.sqrt(Math.abs(scalar(u, u)));
	}
	
	/**
	 * 
	 * @param p
	 * @param plane
	 * @return khoang cach tu diem den mat phang
	 */
	public static double distancePointPlane(Point p ,Plane3D plane ) {
		return Math.abs(plane.getA()*p.getX()+plane.getB()*p.getY()+plane.getC()*p.getZ()+plane.getD())/lenghVector(plane.getN());
	} 
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 mat phang song song
	 */
	public static double distanceTwoPlane(Plane3D p1, Plane3D p2) {
		return Math.abs(p1.getD() - p2.getD());
	}
	/**
	 * 
	 * @param line
	 * @param plane
	 * @return giao diem cua duong thang voi mat , tra ve null neu khong co hoac duong nam tren mat
	 */
	public static Point intersectionLinePlane(Line3D line, Plane3D plane) {
		if(scalar(line.getU(), plane.getN()) == 0) {
			return null;
		}
		double t = -(plane.getD() + plane.getA()*line.getX0() + plane.getB()*line.getY0() + plane.getC()*line.getZ0())/
				(plane.getA()*line.getA() + plane.getB()*line.getB() + plane.getC()*line.getC());
		//System.out.println(t+ " " +line.getX0() +" "+ line.getA());
		return new Point(line.getX0() + line.getA()*t, line.getY0() + line.getB()*t, line.getZ0() + line.getC()*t);
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem
	 */
	public static double distanceOxyTwoPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2)
				+ Math.pow((p1.getY() - p2.getY()), 2));
	}
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return dien tich tam giac chieu tren oxy
	 */
	public static double calcAreaOxyFrom3Points (Point p1, Point p2, Point p3) {
		double sum = 0;
		sum += (p1.getX() * p2.getY() - p1.getY() * p2.getX())
				+ (p2.getX() * p3.getY() - p2.getY() * p3.getX())
				+ (p3.getX() * p1.getY() - p3.getY() * p1.getX());
		
		double area = Math.abs(sum / 2);
		return area;
	}
	/**
	 * 
	 * @param p
	 * @return hinh chieu cua 1 diem len Oxy
	 */
	public static Point ProjectionOxyPoint(Point p) {
		Point pOxy = new Point();		
		pOxy.setX(p.getX());
		pOxy.setY(p.getY());
		pOxy.setZ(0);
		return pOxy;
		
	}
	
	
}
