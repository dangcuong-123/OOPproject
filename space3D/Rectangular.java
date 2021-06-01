package space3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rectangular {
	public List<Point> cornerOfRec = new ArrayList<Point>();
	private List<Plane3D> planes = new ArrayList<Plane3D>(6);

	public Rectangular() {
	}

	public Rectangular(List<Point> a) {
		if (checkRec(a)) {
			this.cornerOfRec = a;

			// planes0 >< planes 5
			// 1 >< 3
			// 2 >< 4
			
			planes.add(new Plane3D(a.get(0), a.get(2), a.get(4))); // Mat day 0
			planes.add(new Plane3D(a.get(0), a.get(2), a.get(3))); // Mat ben 1
			planes.add(new Plane3D(a.get(0), a.get(4), a.get(5))); // Mat ben 2
			planes.add(new Plane3D(a.get(4), a.get(6), a.get(7))); // Mat ben 3
			planes.add(new Plane3D(a.get(2), a.get(3), a.get(6))); // Mat ben 4
			planes.add(new Plane3D(a.get(1), a.get(3), a.get(5))); // Mat dinh 5
			System.out.println(planes.get(0));
//    		for (Plane3D plan : planes) {
//    			System.out.println(plan);
//    		}
			System.out.println("Create successfull!!!");
		} else
			System.out.println("Error, input not rec");
	}

	public boolean checkRec(List<Point> a) {
		// x tang dan theo cap (0,1)(2,3)(4,5)(6,7)
		// Mat y: (0, 1), (4, 5), (2, 3), (6, 7)
		// Mat z nho: 0, 2, 4, 6
		// Mat z to: 1, 3, 5, 7

		Collections.sort(a); // Tra ve danh sach 8 diem, sort theo x -> y -> z

		for (int i = 0; i < 6; i += 2) { // check z cua 4 cap dinh tren va duoi
			if (a.get(i).getZ() != a.get(i + 2).getZ() || a.get(i + 1).getZ() != a.get(i + 3).getZ())
				return false;
		}

		for (int i = 0; i < 8; i += 2) { // check hinh chieu cua cac cap dien tuong ung tren Oxy
			if (Calculate3D.ProjectionOxyPoint(a.get(i)).equals(Calculate3D.ProjectionOxyPoint(a.get(i + 1))) == false)
				return false;
		}

		// tinh tich vo huong de kiem tra hinh chu nhat
		Vector3D u02 = new Vector3D(a.get(0), a.get(2));
		Vector3D u26 = new Vector3D(a.get(2), a.get(6));
		Vector3D u46 = new Vector3D(a.get(4), a.get(6));
		Vector3D u04 = new Vector3D(a.get(0), a.get(4));

		return Math
				.abs(Calculate3D.scalar(u02, u04) + Calculate3D.scalar(u02, u26) + Calculate3D.scalar(u26, u46)) < 1e-5;
	}

	/**
	 * kiem tra xem 1 diem co nam trong 1 khoi hop chu nhat ko
	 * 
	 * @param possition
	 * @return false: ngoai
	 */

	public boolean pointInRec3D(Point position) {

		if (position == null)
			return false;

		if (((Math.max(Calculate3D.distancePointPlane(position, planes.get(0)),
				Calculate3D.distancePointPlane(position, planes.get(5)))) <= 1e-5+ Calculate3D.distanceTwoPlane(planes.get(0),
						planes.get(5)))
				&& ((Math.max(Calculate3D.distancePointPlane(position, planes.get(1)),
						Calculate3D.distancePointPlane(position, planes.get(3)))) <=1e-5+ Calculate3D
								.distanceTwoPlane(planes.get(1), planes.get(3)))
				&& ((Math.max(Calculate3D.distancePointPlane(position, planes.get(2)),
						Calculate3D.distancePointPlane(position, planes.get(4)))) <= 1e-5+ Calculate3D
								.distanceTwoPlane(planes.get(2), planes.get(4))))
			return true;

		return false;
	}

	public boolean pointInRec(Point position) {

		double sumArea = 0;
		sumArea += Calculate3D.calcAreaOxyFrom3Points(position, this.cornerOfRec.get(0), this.cornerOfRec.get(2))
				+ Calculate3D.calcAreaOxyFrom3Points(position, this.cornerOfRec.get(2), this.cornerOfRec.get(6))
				+ Calculate3D.calcAreaOxyFrom3Points(position, this.cornerOfRec.get(4), this.cornerOfRec.get(6))
				+ Calculate3D.calcAreaOxyFrom3Points(position, this.cornerOfRec.get(4), this.cornerOfRec.get(0));

		double areaBottom = 0;
		areaBottom += Calculate3D.calcAreaOxyFrom3Points(this.cornerOfRec.get(0), this.cornerOfRec.get(2),
				this.cornerOfRec.get(6))
				+ Calculate3D.calcAreaOxyFrom3Points(this.cornerOfRec.get(0), this.cornerOfRec.get(4),
						this.cornerOfRec.get(6));

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

	public List<Plane3D> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane3D> planes) {
		this.planes = planes;
	}

	@Override
	public String toString() {
		return "Rectangular [cornerOfRec=" + cornerOfRec + "]";
	}

}
