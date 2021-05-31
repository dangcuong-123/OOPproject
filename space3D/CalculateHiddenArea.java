package space3D;

import java.util.Stack;

public class CalculateHiddenArea {
	public Room myRoom;
	public int widthRoom;
	public int longRoom;
	public int highRoom;
	public int[][][] matrix;
	public double scale;
	public int w, l, h;
	// height
	
	
	public CalculateHiddenArea(Room myRoom) {
		// TODO Auto-generated constructor stub
		this.myRoom = myRoom;
		this.widthRoom = (int) (myRoom.getRoom().getcornerOfRec().get(6).getX());
		this.longRoom = (int) (myRoom.getRoom().getcornerOfRec().get(6).getY());
		this.highRoom = (int) (myRoom.getRoom().getcornerOfRec().get(1).getZ());

		System.out.println(this.widthRoom);
		System.out.println(this.longRoom);
		System.out.println(this.highRoom);
	}

	public CalculateHiddenArea(Room myRoom, int numberPoint) {
		this.myRoom = myRoom;
		this.widthRoom = (int) (myRoom.getRoom().getcornerOfRec().get(6).getX());
		this.longRoom = (int) (myRoom.getRoom().getcornerOfRec().get(6).getY());
		this.highRoom = (int) (myRoom.getRoom().getcornerOfRec().get(1).getZ());
		this.scale = Math.cbrt((double) numberPoint / (widthRoom * highRoom * longRoom));
		System.out.println(scale);
		w = (int) (widthRoom * scale) + 1;
		l = (int) (longRoom * scale) + 1;
		h = (int) (highRoom * scale) + 1;
		matrix = new int[w][l][h];
		System.out.println(w + " " + l + " " + h);
		System.out.println(widthRoom + " " + longRoom + " " + highRoom);

	}

	// neu ko thay thi tra ve true
	public Camera pointIsHidden(Point position) {
		for (Camera cam : myRoom.getCamInRoom()) {
			// check = 1 la vat khong nhin thay
			// kiem tra xem co trong vung ma camera co the chieu den
			if (cam.checkPointInVisibleAreaOfCam(position)) {
				int check = 0;
				for (Rectangular object : myRoom.getRecInRoom()) {
					if (Calculate3D.checkIntersectPointInRec(cam.getCamPosition(), position,
							new Line3D(cam.getCamPosition(), position), object))
						check = 1;
					if (check == 1) {
						break;
					}
				}
				if (check == 0) {
					return cam;
				}
			}
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public double calculateHidden() {
		int count = 0;
		for (int i = 0; i < this.widthRoom; i++) {
			for (int j = 0; j < this.longRoom; j++) {
				for (int k = 0; k < this.highRoom; k++) {
					if (pointIsHidden(new Point(i, j, k)) == null) {
						count += 1;
					}
				}
			}
		}
		System.out.println(count + " " + this.widthRoom * this.longRoom * this.highRoom);
		return 100 * (1 - (float) (count) / (this.widthRoom * this.longRoom * this.highRoom));
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Point toReal(int x, int y, int z) {
		return new Point(x / scale, y / scale, z / scale);
	}

	private int[] toVirtual(Point p) {
		int[] c = { (int) (p.getX() * scale), (int) (p.getY() * scale), (int) (p.getZ() * scale) };
		return c;
	}

	private boolean checkInMatrix(int x, int y, int z) {
		if (0 <= x && x < w && 0 <= y && y < l && 0 <= z && z < h)
			return true;
		return false;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void dfs(int x, int y, int z , boolean t) {
		if (checkInMatrix(x, y, z)) {
			if (matrix[x][y][z] == 0) {
				Camera cam = pointIsHidden(toReal(x, y, z));
				if (cam != null||t ==true){
					matrix[x][y][z] = 1;
					dfs(x, y, z - 1 ,false);
					dfs(x - 1, y, z,false);
					dfs(x + 1, y, z,false);
					dfs(x, y - 1, z,false);
					dfs(x, y + 1, z,false);
					dfs(x, y, z + 1,false);
				} else
					matrix[x][y][z] = -1;
			}
		}
	}
	
	public double calculateHiddenVs1() {
		int count = 0;
		for (Camera cam : myRoom.getCamInRoom()) {
			int arrOxyz[] = toVirtual(cam.camPosition);
			matrix[arrOxyz[0]][arrOxyz[1]][arrOxyz[2]] =0;
			dfs(arrOxyz[0], arrOxyz[1], arrOxyz[2],true);
		}

		for (int i = 0; i < w; i++)
			for (int j = 0; j < l; j++)
				for (int k = 0; k < h; k++) {
					if (matrix[i][j][k] == 1)
						count++;
				}

		return 100 * (double) count / (w * l * h);
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void dfsPromax(int x, int y, int z,boolean t) {
		Stack<IF> s = new Stack<IF>();
		s.push(new IF(x, y, z));
		int i, j, k;
		while (!s.isEmpty()) {
			IF p = s.peek();
			s.pop();
			i = p.x;
			j = p.y;
			k = p.z;				
			if (matrix[i][j][k] == 0) {
				Camera cam = pointIsHidden(toReal(i, j, k));
				if (cam != null||t == true) {
					t=false;
					matrix[i][j][k] = 1;
					if (checkInMatrix(i, j, k - 1) && matrix[i][j][k - 1] == 0)
						s.push(new IF(i, j, k - 1));
					if (checkInMatrix(i - 1, j, k) && matrix[i - 1][j][k] == 0)
						s.push(new IF(i - 1, j, k));
					if (checkInMatrix(i + 1, j, k) && matrix[i + 1][j][k] == 0)
						s.push(new IF(i + 1, j, k));
					if (checkInMatrix(i, j - 1, k) && matrix[i][j - 1][k] == 0)
						s.push(new IF(i, j - 1, k));
					if (checkInMatrix(i, j + 1, k) && matrix[i][j + 1][k] == 0)
						s.push(new IF(i, j + 1, k));
					if (checkInMatrix(i, j, k + 1) && matrix[i][j][k + 1] == 0)
						s.push(new IF(i, j, k + 1));
				} else
					matrix[i][j][k] = -1;

			}
		}
	}
	
	public double calculateHiddenVs2() {
		int count = 0;
		for (Camera cam : myRoom.getCamInRoom()) {
			int arrOxyz[] = toVirtual(cam.camPosition);
			matrix[arrOxyz[0]][arrOxyz[1]][arrOxyz[2]] =0;
			dfsPromax(arrOxyz[0], arrOxyz[1], arrOxyz[2],true);
		}

		for (int i = 0; i < w; i++)
			for (int j = 0; j < l; j++)
				for (int k = 0; k < h; k++) {
					if (matrix[i][j][k] == 1)
						count++;
				}

		return 100 * (double) count / (w * l * h);
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private void dfsPromax1(int x, int y, int z) {
		Stack<IF> s = new Stack<IF>();
		s.push(new IF(x, y, z));
		int i, j, k;
		while (!s.isEmpty()) {
			IF p = s.peek();
			s.pop();
			i = p.x;
			j = p.y;
			k = p.z;				
			if (matrix[i][j][k] == 0) {
				Camera cam = pointIsHidden(toReal(i, j, k));
				if (cam != null) {
					matrix[i][j][k] = 1;
					Point point = Calculate3D.intersectionLinePlane(new Line3D(toReal(i, j, k), new Vector3D(0, 0, 1)),
							cam.gettopPlane());
					for (int v = k + 1; v < (int) (point.getZ() * scale); v++) {
		
						if (checkInMatrix(i, j, v)) {
							if (matrix[i][j][v] == 1)
								break;
							matrix[i][j][v] = 2;
							s.push(new IF(i, j, v));
						}
					}
					if (checkInMatrix(i, j, k - 1) && matrix[i][j][k - 1] == 0)
						s.push(new IF(i, j, k - 1));
					if (checkInMatrix(i - 1, j, k) && matrix[i - 1][j][k] == 0)
						s.push(new IF(i - 1, j, k));
					if (checkInMatrix(i + 1, j, k) && matrix[i + 1][j][k] == 0)
						s.push(new IF(i + 1, j, k));
					if (checkInMatrix(i, j - 1, k) && matrix[i][j - 1][k] == 0)
						s.push(new IF(i, j - 1, k));
					if (checkInMatrix(i, j + 1, k) && matrix[i][j + 1][k] == 0)
						s.push(new IF(i, j + 1, k));
					if (checkInMatrix(i, j, k + 1) && matrix[i][j][k + 1] == 0)
						s.push(new IF(i, j, k + 1));
				} else
					matrix[i][j][k] = -1;

			}
			if (matrix[i][j][k] == 2) {
				matrix[i][j][k] = 1;
				if (checkInMatrix(i, j, k - 1) && matrix[i][j][k - 1] == 0)
					s.push(new IF(i, j, k - 1));
				if (checkInMatrix(i - 1, j, k) && matrix[i - 1][j][k] == 0)
					s.push(new IF(i - 1, j, k));
				if (checkInMatrix(i + 1, j, k) && matrix[i + 1][j][k] == 0)
					s.push(new IF(i + 1, j, k));
				if (checkInMatrix(i, j - 1, k) && matrix[i][j - 1][k] == 0)
					s.push(new IF(i, j - 1, k));
				if (checkInMatrix(i, j + 1, k) && matrix[i][j + 1][k] == 0)
					s.push(new IF(i, j + 1, k));
				if (checkInMatrix(i, j, k + 1) && matrix[i][j][k + 1] == 0)
					s.push(new IF(i, j, k + 1));
			}
		}
	}
	public double calculateHiddenVs3() {
		int count = 0;
		for (Camera cam : myRoom.getCamInRoom()) {
			int arrOxyz[] = toVirtual(cam.camPosition);
			matrix[arrOxyz[0]][arrOxyz[1]][arrOxyz[2]] =2;
			dfsPromax1(arrOxyz[0], arrOxyz[1], arrOxyz[2]);
		}

		for (int i = 0; i < w; i++)
			for (int j = 0; j < l; j++)
				for (int k = 0; k < h; k++) {
					if (matrix[i][j][k] == 1)
						count++;
				}

		return 100 * (double) count / (w * l * h);
	}

/////////////////////////////////////////////////////////////////////////////////////////
	private boolean checkAround(int i, int j, int k) {
		if(matrix[i][j][k]!=0) {
		if (checkInMatrix(i, j, k - 1) && matrix[i][j][k - 1] + matrix[i][j][k] == 0)
			return false;
		if (checkInMatrix(i - 1, j, k) && matrix[i - 1][j][k] + matrix[i][j][k] == 0 )
			return false;
		if (checkInMatrix(i + 1, j, k) && matrix[i + 1][j][k] + matrix[i][j][k] == 0)
			return false;
		if (checkInMatrix(i, j - 1, k) && matrix[i][j - 1][k] + matrix[i][j][k] == 0)
			return false;
		if (checkInMatrix(i, j + 1, k) && matrix[i][j + 1][k] + matrix[i][j][k] == 0)
			return false;
		if (checkInMatrix(i, j, k + 1) && matrix[i][j][k + 1] + matrix[i][j][k] == 0)
			return false;
		
		}

		return true;
	}
	
	
	public double calculateHiddenVs4() {
		double s=0;
		float count = 0;
		filterBlock3D filter = new filterBlock3D(9,widthRoom/w);
		for (Camera cam : myRoom.getCamInRoom()) {
			int arrOxyz[] = toVirtual(cam.camPosition);
			matrix[arrOxyz[0]][arrOxyz[1]][arrOxyz[2]] =2;
			dfsPromax1(arrOxyz[0], arrOxyz[1], arrOxyz[2]);
		}
		for (int i = 0; i < w; i++)
			for (int j = 0; j < l; j++)
				for (int k = 0; k < h; k++) {
					if(checkAround(i, j, k)) {
						if (matrix[i][j][k] == 1)
							count++;
						}
					else {
						filter.setCenterMatrix(toReal(i, j, k));
						count+=filter.percentageHidden();
						s++;
					}	
				}
		System.out.println(s);
		return 100 * count / (w * l * h);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	

	
	
	
	
	
	
	
	class IF {
		public int x;
		public int y;
		public int z;

		public IF(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}
	

	class filterBlock3D {
		public int[][][] matrix;
		public int u;
		public float length;
		public Point centerMatrix;

		public filterBlock3D(int u, double length) {
			super();
			this.u = u;
		}

		public Point convertToOxyz(int x, int y, int z) {
			double x1 = ((x - (int) (u / 2)) * length /u + centerMatrix.getX());
			double y1 = ((y - (int) (u / 2)) * length /u + centerMatrix.getY());
			double z1 = ((z - (int) (u / 2)) * length /u + centerMatrix.getZ());
			Point p = new Point(x1, y1, z1);
			return p;
		}

		public double percentageHidden(){
			double count = 0;
			for (int i = 0; i < u; i++)
				for (int j = 0; j < u; j++)
					for (int k = 0; k < u; k++) {
						if (pointIsHidden(convertToOxyz(i, j, k)) != null) {
							count += 1;
						}
					}
			return count / (u * u * u);
		}

		public int getu() {
			return u;
		}

		public void setu(int u) {
			this.u = u;
		}

		public Point getCenterMatrix() {
			return centerMatrix;
		}

		public void setCenterMatrix(Point centerMatrix) {
			this.centerMatrix = centerMatrix;
		}

	}
}
