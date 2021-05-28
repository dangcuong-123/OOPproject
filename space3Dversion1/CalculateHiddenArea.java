package space3Dversion1;

public class CalculateHiddenArea {
	public Room myRoom;
	public int[][][] roomMaxtrix3D;
	public int wideRoom;
	public int longRoom;
	public int highRoom;
	//height
	
	public CalculateHiddenArea(Room myRoom) {
		// TODO Auto-generated constructor stub
		this.myRoom = myRoom;
		this.wideRoom = (int) (myRoom.getRoom().getcornerOfRec().get(0).getX() - myRoom.getRoom().getcornerOfRec().get(2).getX());
		this.longRoom = (int) (myRoom.getRoom().getcornerOfRec().get(0).getY() - myRoom.getRoom().getcornerOfRec().get(4).getY());
		this.highRoom = (int) (myRoom.getRoom().getcornerOfRec().get(0).getZ() - myRoom.getRoom().getcornerOfRec().get(1).getZ());
		//System.out.println(this.wideRoom + " " + this.longRoom + " " + this.highRoom);
		roomMaxtrix3D = new int[this.wideRoom + 10][this.longRoom + 10][this.highRoom + 10];
	}
	
	public boolean pointIsHidden(Point p) {
		for(int i=0; i < myRoom.getCamInRoom().size();i++) {
			for(int j=0; j < myRoom.getRecInRoom().size(); j++) {
				
			}
		}
		return true;
	}
}

 