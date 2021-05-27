package space3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rectangular {
    public List<Point> cornerOfRec = new ArrayList<Point>();
	private List<Plane3D> planes = new ArrayList<Plane3D>(4); 
    public Rectangular() {}
    
    public Rectangular(List<Point> a) {
    	if (checkReg(a)) {
    		this.cornerOfRec = a;
    		planes.add(new Plane3D(a.get(0),a.get(2),a.get(3)));
    		planes.add(new Plane3D(a.get(0),a.get(4),a.get(5)));
    		planes.add(new Plane3D(a.get(4),a.get(6),a.get(7)));
    		planes.add(new Plane3D(a.get(2),a.get(3),a.get(6)));
    		System.out.println("Create successfull!!!");
    	}
    	else
    		System.out.println("Error, input not reg");
    }
    
//    public double calcAreaBottom () {
//    	double sum = 0;
//    	sum += (this.cornerOfRec.get(0).getX() * a.get(2).getY() - a.get(0).getY() * a.get(2).getX())
//				+ (a.get(2).getX() * a.get(6).getY() - a.get(2).getY() * a.get(6).getX())
//				+ (a.get(6).getX() * a.get(4).getY() - a.get(6).getY() * a.get(4).getX())
//				+ (a.get(4).getX() * a.get(0).getY() - a.get(4).getY() * a.get(0).getX());
//    	double area = Math.abs(sum / 2);
//    	return area;
//    }
    
    public boolean checkReg (List<Point> a) {
		Collections.sort(a);	// Tra ve danh sach 8 diem, sort theo x -> y -> z
    	
    	// x tang dan theo cap (0,1)(2,3)(4,5)(6,7)
    	// Mat y: (0, 1), (4, 5), (2, 3), (6, 7)
    	// Mat z nho: 0, 2, 4, 6
    	// Mat z to: 1, 3, 5, 7
//    	for (int i =0; i<8; i++) {
//    		System.out.println(i + ":");
//    		System.out.println("a" + a.get(i).getX() + " "+  a.get(i).getY() +" "+ a.get(i).getZ());
//    	}
		
		// Check hinh hop
		// 2 diem gan nhau thi phai giong x, y, khac z
		for (int i=0;i<8;i+=2) {
			if ((a.get(i).getX() != a.get(i+1).getX())
					|| (a.get(i).getY() != a.get(i+1).getY())
					|| (a.get(i).getZ() == a.get(i+1).getZ()))
				return false;
		}
		
		// 0, 2, 4, 6 cung z, 1, 3, 5, 7 cung z
		if (a.get(0).getZ() != a.get(2).getZ() || a.get(0).getZ() != a.get(4).getZ()
				|| a.get(0).getZ() != a.get(6).getZ())
			return false;
		if (a.get(1).getZ() != a.get(3).getZ() || a.get(1).getZ() != a.get(5).getZ()
				|| a.get(1).getZ() != a.get(7).getZ())
			return false;
		
		// Check hinh chu nhat (Xet 4 diem 0, 2, 4, 6))
		// Neu dien tich hinh binh hanh ko = hcn thi sai
		// Cong thuc tinh dien tich da giac
		double sum = 0;
		sum += (a.get(0).getX() * a.get(2).getY() - a.get(0).getY() * a.get(2).getX())
				+ (a.get(2).getX() * a.get(6).getY() - a.get(2).getY() * a.get(6).getX())
				+ (a.get(6).getX() * a.get(4).getY() - a.get(6).getY() * a.get(4).getX())
				+ (a.get(4).getX() * a.get(0).getY() - a.get(4).getY() * a.get(0).getX());
		double area = Math.abs(sum / 2);
		double ab = a.get(0).distanceTwoPoints(a.get(0), a.get(2));
		double ac = a.get(0).distanceTwoPoints(a.get(0), a.get(4));
		if (Math.abs(area - ab*ac) >= 1e-7)
			return false;
    	return true;
    }
    
    public boolean pointInRec(Point position) {
    	
    	double sumArea = 0;
    	sumArea += position.calcAreaFrom3Points(position,
					this.cornerOfRec.get(0), this.cornerOfRec.get(2))
    			+ position.calcAreaFrom3Points(position,
    					this.cornerOfRec.get(2), this.cornerOfRec.get(6))
    			+ position.calcAreaFrom3Points(position,
    					this.cornerOfRec.get(4), this.cornerOfRec.get(6))
    			+ position.calcAreaFrom3Points(position,
    					this.cornerOfRec.get(4), this.cornerOfRec.get(0));
    	
    	double areaBottom = 0;
    	areaBottom += position.calcAreaFrom3Points(this.cornerOfRec.get(0)
    						, this.cornerOfRec.get(2), this.cornerOfRec.get(6))
    				+ position.calcAreaFrom3Points(this.cornerOfRec.get(0)
    						, this.cornerOfRec.get(4), this.cornerOfRec.get(6));
    	
    	if (Math.abs(areaBottom - sumArea) >= 1e-7)
    		return false;
    	
    	return true;
    }
    
    public List<Point> getcornerOfRec() {
		return cornerOfRec;
	}

	public void setcornerOfRec(List<Point> cornerOfRec) {
		this.cornerOfRec = cornerOfRec;
	}
}