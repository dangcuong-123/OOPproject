package space3D;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private Rectangular room = new Rectangular();
	public List<Rectangular> recInRoom = new ArrayList<Rectangular>();
	public List<Camera> camInRoom = new ArrayList<Camera>();

	public Room() {
	}

	public Room(Rectangular room, List<Rectangular> recInRoom, List<Camera> camInRoom) {
		if (checkCanBeTheRoom(room) && checkAllRecInRoom(recInRoom) == true) {
			setRoom(room);
			setRecInRoom(recInRoom);
			if (checkAllCamInRoom(camInRoom))
				setCamInRoom(camInRoom);
			else {
				System.out.println("Not room");
				System.exit(0);
			}
		} else
			System.out.println("Not room");
	}
	
	

	// Room bat buoc co dinh diem (0, 0, 0)
	public boolean checkCanBeTheRoom(Rectangular room) {
		if (room.cornerOfRec.get(0).getX() != 0 || room.cornerOfRec.get(0).getY() != 0
				|| room.cornerOfRec.get(0).getZ() != 0) {
			return false;
		}
		return true;
	}

	// ===================Check diem trong phong====================
	public boolean checkPointInRoom(Point position) {
		if ((position.getX() >= 0) && (position.getX() <= this.room.cornerOfRec.get(4).getX()) && (position.getY() >= 0)
				&& (position.getY() <= this.room.cornerOfRec.get(2).getY()) && (position.getZ() >= 0)
				&& (position.getZ() <= this.room.cornerOfRec.get(1).getZ()))
			return true;

		return false;
	}

	// ===================Check vat trong phong=================
	// Check xem vat co nam ngoai phong hay ko
	public boolean checkRecOutOfRoom(Rectangular rec) {
		for (int i = 0; i < 8; i++) {
			if (checkPointInRoom(rec.cornerOfRec.get(i)) == false)
				return true;
		}
		return false;
	}

	// Check xem 2 vat co nam tren nhau ko
	// Vat tren nam hoan toan trong vat duoi
	public boolean checkUpAndDownRec(Rectangular rec1, Rectangular rec2) {
		// vat tren rec1
		// vat duoi rec2
		if (rec1.cornerOfRec.get(0).getZ() < rec2.cornerOfRec.get(0).getZ()) {
			Rectangular temp = rec1;
			rec1 = rec2;
			rec2 = temp;
		}
		// z mat tren vat duoi = z mat duoi vat tren
		if (rec1.cornerOfRec.get(0).getZ() != rec2.cornerOfRec.get(1).getZ())
			return false;
		// toa do cua mat duoi vat tren rec1 : 0, 2, 4, 6 : z
		// toa do cua mat tren vat duoi rec2 : 1, 3, 5, 7 : z
		// toa do 4 dinh mat duoi vat tren thuoc mat tren vat duoi
		for (int i = 0; i < 8; i += 2)
			if (rec2.pointInRec(rec1.cornerOfRec.get(i)) == false)
				return false;
		return true;
	}

	// Check 2 vat long nhau khong
	public boolean checkRecUnionRec(Rectangular rec1, Rectangular rec2) {
		for (int i = 0; i < 8; i += 2) {
			// Neu co 1 diem trong hinh la sai
			if (rec2.pointInRec(rec1.cornerOfRec.get(i)) == true)
				return true;
			if (rec1.pointInRec(rec2.cornerOfRec.get(i)) == true)
				return true;
		}
		System.out.println("Rec cannot in rec!!!");
		return false;
	}

	// Check cac vat trong phong co thoa man cac dieu kien khong
	public boolean checkAllRecInRoom(List<Rectangular> recInRoom) {
		
		// Tim cac vat co z_duoi > 0 (Co kha nang o tren vat khac)
		List<Integer> positionOfFlyRec = new ArrayList<Integer>();
		List<Integer> positionOfGroundRec = new ArrayList<Integer>();
		for (int i = 0; i < recInRoom.size(); i++) {
			if (recInRoom.get(i).getcornerOfRec().get(0).getZ() > 0)
				positionOfFlyRec.add(i);
			else
				positionOfGroundRec.add(i);
		}

		// Check vat o tren co vat o duoi khong
		int countCanBeFly = 0;
		for (int i = 0; i < positionOfFlyRec.size(); i++) {
			Rectangular recUp = recInRoom.get(positionOfFlyRec.get(i));
			for (int j = 0; j < positionOfGroundRec.size(); j++) {
				Rectangular recDown = recInRoom.get(positionOfFlyRec.get(j));
				if (checkUpAndDownRec(recUp, recDown) == true)
					countCanBeFly += 1;
			}
		}
		if (countCanBeFly < positionOfFlyRec.size()) {
			System.out.println("Rec cannot fly!!!");
			return false;
		}
		// Check vat co long nhau ko
		for (int i = 0; i < positionOfGroundRec.size() - 1; i++) {
			Rectangular rec1 = recInRoom.get(positionOfFlyRec.get(i));
			for (int j = i + 1; j < positionOfGroundRec.size(); j++) {
				Rectangular rec2 = recInRoom.get(positionOfFlyRec.get(j));
				if (checkRecUnionRec(rec1, rec2) == true)
					return false;
			}
		}
		return true;
	}
	// ========================End============================

	
	// =====================Check Cam trong phong=================
	public boolean camInWall(Camera cam) {
		// Check cam co trong phong ko
		if (checkPointInRoom(cam.getCamPosition()) == false) {
			System.out.println("Camera not in room!!!");
			return false;
		}

		// Neu cam duoi dat hoax tren tran thi sai
		if ((cam.getCamPosition().getZ() == 0)
				|| (cam.getCamPosition().getZ() == this.room.getcornerOfRec().get(1).getZ()))
			return false;

		// Check cam thuoc 4 mat tuong hay ko
		for (int i = 1; i < 5; i++) {
			if (Calculate3D.distancePointPlane(cam.getCamPosition(), this.room.getPlanes().get(i)) <= 1e-5)
			{ 
				if((i== 1) || (i==2))cam.setOppositePlane(this.room.getPlanes().get(i+2)); //  mat day cua camera trong truong hop nay khong gioi han nen chon luon mat tuong doi dien
				else cam.setOppositePlane(this.room.getPlanes().get(i-2));
				return true;
			}
		}
		return false;
	}
	
	
	// Check 2 cam co trung nhau ko
	public boolean checkCoincidenceCam(Camera cam1, Camera cam2) {
		return cam1.camPosition.equals(cam2.camPosition);
	}
	

	public boolean checkAllCamInRoom(List<Camera> camInRoom) {
//		 Check trung nhau
		for (int i = 0; i < camInRoom.size() - 1; i++) {
			for (int j = i + 1; j < camInRoom.size(); j++)
				if (checkCoincidenceCam(camInRoom.get(i), camInRoom.get(j))) {
					System.out.println("Camera cannot Coincidence!!!");
					return false;
				}
		}
		// Check cam tren tuong ko
		for (Camera cam : camInRoom) {
			if (camInWall(cam) == false) {
				System.out.println("Camera not in wall!!!");
				return false;
			}
		}
		return true;
	}
	// ======================================

	public List<Rectangular> getRecInRoom() {
		return recInRoom;
	}

	public void setRecInRoom(List<Rectangular> recInRoom) {
		this.recInRoom = recInRoom;
	}

	public Rectangular getRoom() {
		return room;
	}

	public void setRoom(Rectangular room) {
		this.room = room;
	}

	public List<Camera> getCamInRoom() {
		return camInRoom;
	}

	public void setCamInRoom(List<Camera> camInRoom) {
		this.camInRoom = camInRoom;
	}
}