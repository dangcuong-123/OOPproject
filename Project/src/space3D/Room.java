package space3D;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private Rectangular room = new Rectangular();
	public List<Rectangular> recInRoom = new ArrayList<Rectangular>();
	public List<Camera> camInRoom = new ArrayList<Camera>();
	
	// Room bat buoc co dinh diem (0, 0, 0)
	public boolean checkCanBeTheRoom(Rectangular room) {
		if (room.cornerOfRec.get(0).getX() != 0 || room.cornerOfRec.get(0).getY() != 0
				|| room.cornerOfRec.get(0).getZ() != 0) {
			return false;
		}
		return true;
	}
	// ===================Check diem trong phong====================
	public boolean checkPointInRoom (Point position) {
		if ((position.getX() >= 0)
			&& (position.getX() <= this.room.cornerOfRec.get(4).getX())
			&& (position.getY() >= 0)
			&& (position.getY() <= this.room.cornerOfRec.get(2).getY())
			&& (position.getZ() >= 0)
			&& (position.getZ() <= this.room.cornerOfRec.get(1).getZ()))
			return true;
		
		return false;
	}
	
	// ===================Check vat trong phong=================
	// Check xem vat do co nam hoan toan trong phong hay ko
	public boolean checkRecOutOfRoom(Rectangular rec) {
		for (int i=0; i<8; i++) {
			if (checkPointInRoom(rec.cornerOfRec.get(i)) == false)
				return false;
		}	
		return true;
	}
	
	// Check xem 2 vat co nam tren nhau ko
	public boolean checkUpAndDownRec(Rectangular rec1, Rectangular rec2) {
		// vat tren rec1
		// vat duoi rec2
		if(rec1.cornerOfRec.get(0).getZ() < rec2.cornerOfRec.get(0).getZ()) {
			Rectangular temp = rec1;
			rec1 = rec2;
			rec2 = temp;
		}
		// z mat tren vat duoi = z mat duoi vat tren
		if (rec1.cornerOfRec.get(0).getZ() != rec2.cornerOfRec.get(1).getZ())
			return false;
		// toa do cua mat duoi vat tren rec1 : 0, 2, 4, 6 : z
		// toa do cua mat tren vat duoi rec2 : 1, 3, 5, 7 : z
		// Neu x_nho_rec2 <= x_rec1 <= x_lon_rec2
		// va y_nho_rec2 <= y_rec1 <= y_lon_rec2
		if ((rec1.cornerOfRec.get(0).getX() <= rec2.cornerOfRec.get(1).getX()) &&
				(rec1.cornerOfRec.get(4).getX() <= rec2.cornerOfRec.get(5).getX()) &&
				(rec1.cornerOfRec.get(0).getY() <= rec2.cornerOfRec.get(1).getY()) &&
				(rec1.cornerOfRec.get(2).getY() <= rec2.cornerOfRec.get(3).getY()))
			return true;
		else
			return false;
	}
	
	public boolean checkRecInRoom(List<Rectangular> recInRoom) {
		for(int i=0;i<recInRoom.size();i++) {
			int check = 0;
			for(int j=i+1; j<recInRoom.size();j++) {
				if (checkUpAndDownRec(recInRoom.get(i), recInRoom.get(j)))
					check = 1;
			}
			if(check == 1) {
				return false;
			}
		}
		return true;
	}
	//========================End============================
	
	//=====================Check Cam trong phong=================
	public boolean camInWall(Point camPosition) {
		// Check cam co trong phong ko
		if (checkPointInRoom(camPosition) == false)
			return false;
		// Neu cam duoi dat hoac tren tran thi sai
		if ((camPosition.getZ() == 0) ||
				(camPosition.getZ() == this.room.getcornerOfRec().get(1).getZ()))
			return false;
		// Dem so tuong ma cam o tren day, neu > 1 thi o trong goc hoac canh -> loai
		int count = 0;
		if (camPosition.getX() == this.room.getcornerOfRec().get(0).getX())
			count += 1;
		if (camPosition.getX() == this.room.getcornerOfRec().get(4).getX())
			count += 1;
		if (camPosition.getY() == this.room.getcornerOfRec().get(0).getY())
			count += 1;
		if (camPosition.getY() == this.room.getcornerOfRec().get(2).getY())
			count += 1;
		
		if (count > 1 || count == 0)
			return false;
		
		return true;
	}
	// Check 2 cam co trung nhau ko
	public boolean checkCoincidenceCam (Camera cam1, Camera cam2) {
		if (cam1.camPosition.compareTo(cam2.camPosition) == 0)
			return false;
		return true;
	}
	// ======================================
	public Room(Rectangular room) {
		if (checkCanBeTheRoom(room)) 
			setRoom(room);
		else 
			System.out.println("Not room");
	}
	
	public Room(Rectangular room, List<Rectangular> recInRoom) {
		if (checkCanBeTheRoom(room)) 
			setRoom(room);
		else 
			System.out.println("Not room");
		
	}
	
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
}
