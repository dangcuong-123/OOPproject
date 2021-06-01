package space3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class drawImage extends JPanel{


	    private BufferedImage canvas;

	    public drawImage(int width, int height ,Color[][] colors) {
	        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        fillCanvas(colors);
	        
	    }
	    public Dimension getPreferredSize() {
	        return new Dimension(canvas.getWidth(), canvas.getHeight());
	    }
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.drawImage(canvas, null, null);
	    }
	    
	    public void fillCanvas(Color[][] s) {
	       
	        for (int x = 0; x < canvas.getWidth(); x++) {
	            for (int y = 0; y < canvas.getHeight(); y++) {
	            	if(s[x][y]==null)System.out.println(x+" "+y);
	                canvas.setRGB(x, y, s[x][canvas.getHeight()-1-y].getRGB());
	            }
	        }
	        repaint();
	    }

	   
}
	

