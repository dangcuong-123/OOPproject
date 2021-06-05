package input;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import gui.GuiMain;
import room.Camera;
import space3D.Point;
import space3D.Rectangular;

public class InputFile {
	private String path;
	private File file;
	private Scanner myReader;
	private Rectangular room;
	private float rate = (float) 0.1; // Rate cang nho thi cang dung
	private List<Rectangular> recInRoom = new ArrayList<Rectangular>();
	public List<Camera> camInRoom = new ArrayList<Camera>();

	public InputFile(String path) {
		super();
		this.path = path;
		try {
			this.file = new File(this.path);
			this.myReader = new Scanner(this.file);
		} catch (FileNotFoundException e) {
			GuiMain.mess = GuiMain.mess + "An error occurred.\n";
			GuiMain.textArea.setText(GuiMain.mess);
			e.printStackTrace();
		}
	}

	public InputFile() {
	}

	public Rectangular GetRectangular(String line) {
		List<Point> points = new ArrayList<Point>();
		// chuyen thanh "x, x, x)"
		String[] point = line.split("\\(");
		if (point.length != 9) {
			GuiMain.mess = GuiMain.mess + "Format input is wrong!!.\n";
			GuiMain.textArea.setText(GuiMain.mess);
		}
		for (int i = 1; i < point.length; i++) {
			// chuyen thanh "x, x, x"
			point[i] = point[i].replace(")", "");

			// chuyen thanh [x, x, x])
			String[] temp = point[i].split(", ");
			if (temp.length != 3) {
				GuiMain.mess = GuiMain.mess + "Format input is wrong!!.\n";
				GuiMain.textArea.setText(GuiMain.mess);
			}

			// add point (se co 8 point)
			points.add(new Point(Float.parseFloat(temp[0]) / rate, Float.parseFloat(temp[1]) / rate,
					Float.parseFloat(temp[2]) / rate));
		}

		return new Rectangular(points);
	}

	public Camera GetCamera(String line) {
		// chuyen thanh ["(x, x, x"," x x"]
		String[] pointAndAngle = line.split("\\)");
		if (pointAndAngle.length != 2) {
			GuiMain.mess = GuiMain.mess + "Format input is wrong!!.\n";
			GuiMain.textArea.setText(GuiMain.mess);
		}
		// chuyen thanh ["x, x, x"," x x"]
		pointAndAngle[0] = pointAndAngle[0].replace("(", "");

		// chuyen thanh [[x, x, x]," x x"]
		String[] stringPoint = pointAndAngle[0].split(", ");
		if (stringPoint.length != 3) {
			GuiMain.mess = GuiMain.mess + "Format input is wrong!!.\n";
			GuiMain.textArea.setText(GuiMain.mess);
		}
		Point point = new Point(Float.parseFloat(stringPoint[0]) / rate, Float.parseFloat(stringPoint[1]) / rate,
				Float.parseFloat(stringPoint[2]) / rate);

		// chuyen thanh [[x, x, x],[x, x]]
		String[] angle = pointAndAngle[1].split(" ");
		if (angle.length != 3) {
			GuiMain.mess = GuiMain.mess + "Format input is wrong!!.\n";
			GuiMain.textArea.setText(GuiMain.mess);
		}
		float angleHeight = Float.parseFloat(angle[1]);
		float angleWidth = Float.parseFloat(angle[2]);

		return new Camera(point, angleHeight, angleWidth);
	}

	public void GetInput() {
		String line = this.myReader.nextLine();
		this.room = GetRectangular(line);

		line = this.myReader.nextLine();
		int n = Integer.parseInt(line);
		for (int i = 0; i < n; i++) {
			line = this.myReader.nextLine();
			this.recInRoom.add(GetRectangular(line));
		}
		line = this.myReader.nextLine();
		int m = Integer.parseInt(line);

		for (int i = 0; i < m; i++) {
			line = this.myReader.nextLine();
			this.camInRoom.add(GetCamera(line));
		}
	}

	public Rectangular getRoom() {
		return room;
	}

	public void setRoom(Rectangular room) {
		this.room = room;
	}

	public List<Rectangular> getRecInRoom() {
		return recInRoom;
	}

	public void setRecInRoom(List<Rectangular> recInRoom) {
		this.recInRoom = recInRoom;
	}

	public List<Camera> getCamInRoom() {
		return camInRoom;
	}

	public void setCamInRoom(List<Camera> camInRoom) {
		this.camInRoom = camInRoom;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}