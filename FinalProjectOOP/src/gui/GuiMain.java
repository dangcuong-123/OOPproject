package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import input.InputFile;
import model.CalculateHiddenArea;
import room.Room;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import space3D.*;

public class GuiMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private Room rm;
	public static String mess = "";
	public CalculateHiddenArea hidden = null;
	public static JTextArea textArea = new JTextArea();

	public GuiMain() {
		init();

	}

	public void init() {
		// Create Button
		JButton btnReadFile = new JButton("Read File");
		JButton btnCheckPoint = new JButton("Check a point ");
		JButton btnCaculate = new JButton("Caculate The Hidden Area");
		JButton btnViewTheHidden = new JButton("Display The Hidden Area");
		JButton btnVisionCam = new JButton("Display The Vision Of Camera");
		btnVisionCam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");
				} else {
					JFrame frame[] = new JFrame[100];
					for (int i = 0; i < rm.getCamInRoom().size(); i++) {
						GeneratorMatrixPixel m = new GeneratorMatrixPixel(rm.getCamInRoom().get(i), rm);
						Color[][] cl = rm.getCamInRoom().get(i).img.matrixImg;
						int width = rm.getCamInRoom().get(i).img.widthImg;
						int height = rm.getCamInRoom().get(i).img.heightImg;
						frame[i] = new JFrame("Direct draw demo");

						drawImage panel = new drawImage(width, height, cl);

						frame[i].getContentPane().add(panel);
						frame[i].pack();
						frame[i].setVisible(true);
						frame[i].setResizable(false);
					}
				}
			}
		});
		JButton btnSetMinCam = new JButton("Set minimum the number of camera");
		JButton btnPositionCam = new JButton("Determine Camera Position ");
		JButton btnExit = new JButton("Exit");

		// Create contentPane
		JPanel contentPane = new JPanel();

		// frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 700, 600);
		this.setTitle("CAMERA APP");
		this.setLocationRelativeTo(btnCheckPoint);
		;

		// layout
		contentPane.setLayout(null);

		// setBorder
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// set Font
		btnReadFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCaculate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewTheHidden.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetMinCam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPositionCam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVisionCam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// set Bounds
		btnReadFile.setBounds(35, 31, 133, 56); // x, y, width, height
		btnCaculate.setBounds(35, 111, 274, 56);
		btnCheckPoint.setBounds(178, 31, 133, 56);
		btnViewTheHidden.setBounds(35, 181, 274, 56);
		btnVisionCam.setBounds(35, 251, 274, 56);
		btnSetMinCam.setBounds(35, 321, 274, 56);
		btnPositionCam.setBounds(35, 391, 274, 56);
		btnExit.setBounds(35, 461, 274, 56);

		textArea.setBounds(347, 44, 313, 443);
		// add
		getContentPane().add(btnReadFile);
		getContentPane().add(btnCheckPoint);
		getContentPane().add(btnCaculate);
		getContentPane().add(btnViewTheHidden);
		contentPane.add(btnVisionCam);
		getContentPane().add(btnSetMinCam);
		getContentPane().add(btnPositionCam);
		contentPane.add(btnExit);
		contentPane.add(textArea);

		// add Action Listener
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiMain.mess = GuiMain.mess + "\n\n========== Read file ==========\n";
				GuiMain.textArea.setText(GuiMain.mess);
				// Create a file chooser
				final JFileChooser fc = new JFileChooser("./src/input/");
				// In response to a button click:
				fc.showOpenDialog(fc);
				InputFile file = new InputFile(fc.getSelectedFile().toString());
				file.GetInput();
				
				rm = new Room(file.getRoom(), file.getRecInRoom(), file.getCamInRoom());
				
			}
		});

		btnCheckPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");

				} else {
					new CheckPoint(rm);
				}

			}
		});

		btnCaculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");

				} else {
					System.out.println("Calculating...........");
					
					if (hidden == null)
						hidden = new CalculateHiddenArea(rm, 5000000);
					double startTime = System.currentTimeMillis();

					double percentage = hidden.calculateHiddenVs3();
					long elapsedTimeMillis = (long) (System.currentTimeMillis() - startTime);
					GuiMain.mess = GuiMain.mess + "time : " + elapsedTimeMillis + "\n";
					GuiMain.mess = GuiMain.mess + "Percentage light is: " + percentage + "%\n";
					textArea.setText(GuiMain.mess);
					
					startTime = System.currentTimeMillis();
					percentage = hidden.calculateHidden();
					double elapsedTimeMillis1 = System.currentTimeMillis() - startTime;
					GuiMain.mess = GuiMain.mess + "time : " + elapsedTimeMillis1 + "\n";
					GuiMain.mess = GuiMain.mess + "Percentage light is: " + percentage + "%\n";
					textArea.setText(GuiMain.mess);
					
					System.out.println("Calculated successful\n");
				}
			}
		});

		btnViewTheHidden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");

				} else {
					if (hidden == null) {
						hidden = new CalculateHiddenArea(rm, 5000000);
					}
					Color[][] color1, color2, color3, color4, color5;
					
					// mat 0, 1, 2
					List<Point> diem = rm.getRoom().cornerOfRec;
					color1 = hidden.planeHidden(diem.get(0), diem.get(2), diem.get(1), diem.get(3), 300000);
					int width1 = color1.length;
					int height1 = color1[0].length;
					
					// mat 0, 1, 4
					color2 = hidden.planeHidden(diem.get(0), diem.get(4), diem.get(1), diem.get(5), 300000);
					int width2 = color2.length;
					int height2 = color2[0].length;

					// mat 0, 2, 4: Mat day
					color3 = hidden.planeHidden(diem.get(0), diem.get(4), diem.get(2), diem.get(6), 300000);
					int width3 = color3.length;
					int height3 = color3[0].length;

					// mat 2, 3, 6
					color4 = hidden.planeHidden(diem.get(2), diem.get(6), diem.get(3), diem.get(7), 300000);
					int width4 = color4.length;
					int height4 = color4[0].length;

					// mat 4, 5, 6
					color5 = hidden.planeHidden(diem.get(4), diem.get(6), diem.get(5), diem.get(7), 300000);
					int width5 = color5.length;
					int height5 = color5[0].length;
					
					JFrame frame1 = new JFrame("Mat ben 1");
					JFrame frame2 = new JFrame("Mat ben 2");
					JFrame frame3 = new JFrame("Mat dat");
					JFrame frame4 = new JFrame("Mat ben 4");
					JFrame frame5 = new JFrame("Mat ben 3");

					drawImage panel1 = new drawImage(width1, height1, color1);
					drawImage panel2 = new drawImage(width2, height2, color2);
					drawImage panel3 = new drawImage(width3, height3, color3);
					drawImage panel4 = new drawImage(width4, height4, color4);
					drawImage panel5 = new drawImage(width5, height5, color5);
					
					frame1.getContentPane().add(panel1);
					frame2.getContentPane().add(panel2);
					frame3.getContentPane().add(panel3);
					frame4.getContentPane().add(panel4);
					frame5.getContentPane().add(panel5);

					frame1.pack();
					frame1.setVisible(true);
					frame1.setResizable(false);

					frame2.pack();
					frame2.setVisible(true);
					frame2.setResizable(false);

					frame3.pack();
					frame3.setVisible(true);
					frame3.setResizable(false);

					frame4.pack();
					frame4.setVisible(true);
					frame4.setResizable(false);

					frame5.pack();
					frame5.setVisible(true);
					frame5.setResizable(false);
				}
			}
		});

		btnSetMinCam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");

				} else {
					JFrame f5 = new JFrame("Set Min Camera");
					f5.setVisible(true);
					f5.setBounds(100, 100, 550, 450);
					f5.getContentPane().setLayout(null);
					f5.setLocationRelativeTo(null);
				}
			}
		});
		btnPositionCam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isBlank() == true) {
					textArea.setText("Please input the file first!");

				} else {
					JFrame f6 = new JFrame("Position set camera");
					f6.setVisible(true);
					f6.setBounds(100, 100, 550, 450);
					f6.getContentPane().setLayout(null);
					f6.setLocationRelativeTo(null);
				}

			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				GuiMain frame = new GuiMain();
				frame.setVisible(true);
			}
		});
	}
}
