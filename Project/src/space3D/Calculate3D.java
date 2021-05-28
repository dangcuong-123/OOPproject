package space3D;

public class Calculate3D {
	
	public static double scalar(Vector3D u1 , Vector3D u2 ) {
		double k = u1.getX()*u2.getX() + u1.getY()*u2.getY() + u1.getZ()*u2.getZ() ;
		if(Math.abs(k) < 1e-5) k = 0;
		return k ;
	}
	
	
	public static Vector3D det(Vector3D u1, Vector3D u2) {		
		return new Vector3D(u1.getY()*u2.getZ() - u1.getZ()*u2.getY(),
							u1.getZ()*u2.getX() - u1.getX()*u2.getZ(),
							u1.getX()*u2.getY() - u1.getY()*u2.getX());
	}
	
	
	public static double lenghVector(Vector3D u){
		return Math.sqrt(Math.abs(scalar(u, u)));
	}
	
	public static double distancePointPlane(Point p ,Plane3D plane ) {
		return Math.abs(plane.getA()*p.getX()+plane.getB()*p.getY()+plane.getC()*p.getZ()+plane.getD())/lenghVector(plane.getN());
	} 
	
	
	public static Point intersectionLinePlane(Line3D line, Plane3D plane) {
		if(scalar(line.getU(), plane.getN()) == 0) {
			return null;
		}
		double t = -(plane.getD() + plane.getA()*line.getX0() + plane.getB()*line.getY0() + plane.getC()*line.getZ0())/
				(plane.getA()*line.getA() + plane.getB()*line.getB() + plane.getC()*line.getC());
		System.out.println(t+ " " +line.getX0() +" "+ line.getA());
		return new Point(line.getX0() + line.getA()*t, line.getY0() + line.getB()*t, line.getZ0() + line.getC()*t);
	}
}