package space3D;

import java.awt.Image;
import java.awt.image.MemoryImageSource;

public class GeneratorImageFromMatrix extends javax.swing.JPanel {
	public  Image generator(int[] matrix, int width,int height) {
		Image img = createImage(new MemoryImageSource(width,height,matrix, 0, 1024));
		return null;
		
	};
}
