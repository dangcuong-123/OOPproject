package space3D;
import java.util.ArrayList;
import java.util.List;



public class TestMain {
	
	public static List<Point> test = new ArrayList<Point>();
	
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		test.add(new Point(0,0,0));
//		test.add(new Point(2,0,0));
//		test.add(new Point(2,1,0));
//		test.add(new Point(0,1,0));
//		test.add(new Point(0,0,1));
//		test.add(new Point(2,0,1));
//		test.add(new Point(2,1,1));
//		test.add(new Point(0,1,1));
//		Rectangular hey = new Rectangular(test);
		
		InputFile test = new InputFile("C:\\Users\\CuongDang\\eclipse-workspace\\Project\\src\\input.txt");
		test.GetInput();
		
		
	}

}