package space3D;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class InputFile {
	private String path;
	private File file;
	private Scanner myReader;
	private Rectangular room;
	private List<Rectangular> recInRoom = new ArrayList<Rectangular>();
	public List<Camera> camInRoom = new ArrayList<Camera>();
	
	public InputFile(String path) {
		super();
		this.path = path;
		try {
		      this.file = new File(this.path);
		      this.myReader = new Scanner(this.file);
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public InputFile() {}
	
	public Rectangular GetRectangular(String line) {
		List<Point> points = new ArrayList<Point>();
		// chuyen thanh "x, x, x)"
		String[] point = line.split("\\(");
		if(point.length != 9) {
			System.out.println("sai format input !!");
			System.exit(0);
		}
        for(int i=1; i < point.length;i++) {
        	// chuyen thanh "x, x, x"
        	point[i] = point[i].replace(")","");
        	
        	// chuyen thanh [x, x, x])
        	String[] temp = point[i].split(", ");
        	if(temp.length != 3) {
        		System.out.println("sai format input !!");
        		System.exit(0);
        	}
        	
        	// add point (se co 8 point)
        	points.add(new Point(Float.parseFloat(temp[0]), Float.parseFloat(temp[1]), Float.parseFloat(temp[2])));
        }
        
        return new Rectangular(points);
	}
	
	public Camera GetCamera(String line) {
		//System.out.println(line);
		// chuyen thanh ["(x, x, x"," x x"]
		String[] pointAndAngle = line.split("\\)");
		if(pointAndAngle.length != 2) {
			System.out.println("sai format input !!");
    		System.exit(0);
		}
		// chuyen thanh ["x, x, x"," x x"]
		pointAndAngle[0] = pointAndAngle[0].replace("(","");
		
		// chuyen thanh [[x, x, x]," x x"]
		String[] stringPoint = pointAndAngle[0].split(", ");
		if(stringPoint.length != 3) {
			System.out.println("sai format input !!");
    		System.exit(0);
		}
		Point point = new Point(Float.parseFloat(stringPoint[0]), Float.parseFloat(stringPoint[1]), Float.parseFloat(stringPoint[2]));
		
		// chuyen thanh [[x, x, x],[x, x]]
		String[] angle = pointAndAngle[1].split(" ");
		if(angle.length != 3) {
			System.out.println("sai format input !!");
    		System.exit(0);
		}
		float angleHeight = Float.parseFloat(angle[1]);
		float angleWidth = Float.parseFloat(angle[2]);
		
		return new Camera(point, angleHeight, angleWidth);
	}
	
	public void GetInput() {
		//while (this.myReader.hasNextLine()) {
	          String line = this.myReader.nextLine();
	          this.room = GetRectangular(line);
	          
	          line = this.myReader.nextLine();
	          int n = Integer.parseInt(line);
	          for(int i=0;i <n; i++) {
	        	  line = this.myReader.nextLine();
	        	  this.recInRoom.add(GetRectangular(line));
	          }
	          line = this.myReader.nextLine();
	          int m = Integer.parseInt(line);
	          
	          for (int i=0; i < m; i++) {
	        	  line = this.myReader.nextLine();
	        	  this.camInRoom.add(GetCamera(line));
	          }
	          
	    //}
	}

}
