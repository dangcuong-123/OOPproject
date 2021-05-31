package space3D;

import java.util.Iterator;

public class GeneratorMatrixPixel {
	public Camera cam;
	public Room room;

	private final int[] color = { 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220 };

	public GeneratorMatrixPixel(Camera cam, Room room) {
		super();
		this.cam = cam;
		this.room = room;
		for (int i = 0; i < cam.img.heightImg; i++) {
			for (int j = 0; j < cam.img.widthImg; j++) {
				setValuePixel(i, j);
			}
		}
	}

	public void setValuePixel(int i, int j) {
		double distance = 10000000;
		Point intersecp;
		Line3D line = new Line3D(cam.camPosition, cam.img.matrixPoint[i][j]);
		for (int k = 0; k < 6; k++) {
			intersecp = Calculate3D.intersectionLinePlane(line, room.getRoom().getPlanes().get(k));
			if (Calculate3D.checkIntersectPointInRec(cam.camPosition, intersecp, line, room.getRoom())) {
				if (distance > Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition())) {
					cam.img.matrixImg[i][j] = color[k];
				}
			}
		}
		for (Rectangular rec : room.getRecInRoom()) {
			for (int k = 0; k < 6; k++) {
				intersecp = Calculate3D.intersectionLinePlane(line, rec.getPlanes().get(k));
				if (Calculate3D.checkIntersectPointInRec(cam.camPosition, intersecp, line, rec)) {
					if (distance > Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition())) {
						cam.img.matrixImg[i][j] = color[k + 6];
					}
				}
			}
		}

	}
}
