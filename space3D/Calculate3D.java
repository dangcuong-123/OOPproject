package space3D;

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
		double rate = (p1.getA() + p1.getB() + p1.getC()) / (p2.getA() + p2.getB() + p2.getC());
		return Math.abs((p1.getD() - p2.getD() * rate) / lenghVector(p1.getN()));
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
		return new Point(line.getX0() + line.getA()*t, line.getY0() + line.getB()*t, line.getZ0() + line.getC()*t);
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem tren Oxy
	 */
	public static double distanceOxyTwoPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2)
				+ Math.pow((p1.getY() - p2.getY()), 2));
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem bat ky
	 */
	public static double distanceTwoPoints(Point p1, Point p2) {
		double len = lenghVector(new Vector3D(p1, p2));
		if(len<1e-4)len=0;
		return len;
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
	
	// Tim hinh chieu 1 diem den mat phang bat ky
	public static Point ProjectionPointToPlane(Point p, Plane3D plane) {
		double k = - (plane.getA() * p.getX() + plane.getB() * p.getY() + plane.getC() * p.getZ() + + plane.getD())
					/ Math.pow(lenghVector(plane.getN()), 2);
		Point projectionPoint = new Point(p.getX() + plane.getA() * k, p.getY() + plane.getB() * k, p.getZ() + plane.getC() * k);
		return projectionPoint;
	}
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem theo phuong 0x
	 */
	public static double distanceOxTwoPoint(Point p1, Point p2) {
		return Math.abs(p1.getX()-p2.getX());
	}
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem theo phuong 0y
	 */
	public static double distanceOyTwoPoint(Point p1, Point p2) {
		return Math.abs(p1.getY()-p2.getY());
	}
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return khoang cach 2 diem theo phuong 0x
	 */
	public static double distanceOzTwoPoint(Point p1, Point p2) {
		return Math.abs(p1.getZ()-p2.getZ());
	}
	
	/**
	 * 
	 * @param plane
	 * @param p1
	 * @param p2
	 * @return Check 2 diem co cung phia voi mat phang ko
	 */
	public static boolean checkPointSameSidePointWithPlane(Plane3D plane, Point p1, Point p2) {
		double fp1 = plane.getA() * p1.getX() + plane.getB() * p1.getY() + plane.getC() * p1.getZ() + plane.getD();
		double fp2 = plane.getA() * p2.getX() + plane.getB() * p2.getY() + plane.getC() * p2.getZ() + plane.getD();
		if (fp1 * fp2 >= 0)
			return true;
		return false;
	}
	/**
	 * 
	 * @param triangle
	 * @return dien tich tam giac
	 */
	public static double areaTriangle(Triangle triangle) {
		double a = Calculate3D.lenghVector(new Vector3D(triangle.getVertexA(), triangle.getVertexB())); 
		double b = Calculate3D.lenghVector(new Vector3D(triangle.getVertexB(), triangle.getVertexC()));
		double c = Calculate3D.lenghVector(new Vector3D(triangle.getVertexC(), triangle.getVertexA()));
		double p = (a + b + c)/2;
		return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}
	/**
	 * 
	 * @param rectangle
	 * @return dien tich hinh hop chu nhat
	 */
	public static double areaRectangle(Rectangle rectangle) {
		return Calculate3D.areaTriangle(new Triangle(rectangle.getVertexA(),rectangle.getVertexB(), rectangle.getVertexC()))
				+Calculate3D.areaTriangle(new Triangle(rectangle.getVertexD(),rectangle.getVertexB(), rectangle.getVertexC()));
	}
	
	
	

	public static boolean checkIntersectPointInRec(Point camPos, Point position, Line3D line, Rectangular rec) {
		int check = 0;
		
		for (int i=0;i<6;i++) {
			Point intersectPoint = Calculate3D.intersectionLinePlane(line, rec.getPlanes().get(i));	
			// nhìn thấy vật 
			if(intersectPoint == null) {
				continue;
			}	
			// neu kc (cam, diem dang xet)> (cam, giao diem) thi co the khong nhin thay vat 
			if (Calculate3D.distanceTwoPoints(camPos, position)+1e-7 
					> Calculate3D.distanceTwoPoints(camPos, intersectPoint)&&scalar(new Vector3D(camPos, position), new Vector3D(camPos, intersectPoint))>0) {
				// giao nam tren vat thi khong nhin thay 
				if (rec.pointInRec3D(intersectPoint)) {
					check = 1;
				}
			}
			// else se nhin thay vat 
			if(check == 1) {
				break;
			}
		}
		if (check == 1)
			return true;
		return false;
	}
}
