package gui;

import java.awt.Color;

import room.Camera;
import room.Room;
import space3D.Calculate3D;
import space3D.Line3D;
import space3D.Point;
import space3D.Rectangular;

public class GeneratorMatrixPixel {
	public Camera cam;
	public Room room;

	private final Color[] color = { new Color(143, 18, 43), new Color(222, 184, 120), new Color(222, 184, 130),
			new Color(222, 184, 140), new Color(222, 184, 150), new Color(200, 248, 235), new Color(220, 20, 60),
			new Color(139, 0, 139), new Color(255, 127, 80), new Color(0, 255, 255), new Color(85, 107, 47),
			new Color(0, 0, 139) };

	public GeneratorMatrixPixel(Camera cam, Room room) {
		this.cam = cam;
		this.room = room;
		for (int i = 0; i < cam.img.widthImg; i++) {
			for (int j = 0; j < cam.img.heightImg; j++) {
				setValuePixel(i, j, cam.numberOp);
			}
		}
	}

	public void setValuePixel(int i, int j, int u) {
		cam.img.matrixImg[i][j] = color[0];
		double distance = 100000000;
		Point intersecp;
		Line3D line = new Line3D(cam.camPosition, cam.img.matrixPoint[i][j]);
		for (int k = 0; k < 6; k++) {
			intersecp = Calculate3D.intersectionLinePlane(line, room.getRoom().getPlanes().get(k));
			if (intersecp != null)
				if (Calculate3D.checkIntersectPointInRec(cam.camPosition, intersecp, line, room.getRoom())) {
					if (distance > Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition())) {
						cam.img.matrixImg[i][j] = color[k];
						distance = Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition());
					}
				}
			}

		for (Rectangular rec : room.getRecInRoom()) {
			for (int k = 0; k < 6; k++) {
				intersecp = Calculate3D.intersectionLinePlane(line, rec.getPlanes().get(k));
				if (intersecp != null)
					if (Calculate3D.checkIntersectPointInRec(cam.camPosition, intersecp, line, rec)) {
						if (distance - 1e-3 > Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition())) {
							cam.img.matrixImg[i][j] = color[k + 6];
							distance = Calculate3D.distanceTwoPoints(intersecp, cam.getCamPosition());
						}
					}
			}
		}

	}
}