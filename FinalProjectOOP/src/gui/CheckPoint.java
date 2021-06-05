package gui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.CalculateHiddenArea;
import room.Camera;
import room.Room;
import space3D.Point;


public class CheckPoint extends JFrame {
	private static final long serialVersionUID = 1L;


	  //Create the frame.
	 
	public CheckPoint(Room rm) {
		JFrame f2 = new JFrame("CHECK A POINT");
		//set frame
		f2.setVisible(true);
		f2.setBounds(100, 100, 350, 239);
		f2.getContentPane().setLayout(null);
		f2.setLocationRelativeTo(null);
		f2.setVisible(true);
		f2.setBounds(100, 100, 500, 350);
		f2.setLocationRelativeTo(null);
		//JLabel
		JLabel lblX = new JLabel("X");
		JLabel lblY = new JLabel("Y");
		JLabel lblZ= new JLabel("Z");
		JLabel lbl1 = new JLabel("");
		JLabel lblMess = new JLabel("Enter the coordinate point you want to check below: ");
		//JTextField
		JTextField textX= new JTextField();
		JTextField textY = new JTextField();
		JTextField textZ = new JTextField();
		//JButton
		JButton btnCheck = new JButton("Check");
		//set font
		lblX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblY.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblZ.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		//set bounds
		lblX.setBounds(36, 91, 16, 14);
		lblY.setBounds(182, 91, 16, 14);
		lblZ.setBounds(334, 91, 16, 14);
		textX.setBounds(54, 84, 112, 28);
		textY.setBounds(197, 84, 112, 28);
		textZ.setBounds(350, 84, 112, 28);
		lbl1.setBounds(57, 62, 72, 28);
		lblMess.setBounds(72, 24, 419, 28);
		btnCheck.setBounds(259, 149, 72, 28);
		//add
		f2.getContentPane().add(lblX);
		f2.getContentPane().add(lblY);
		f2.getContentPane().add(lblZ);
		f2.getContentPane().add(textX);
		f2.getContentPane().add(textY);
		f2.getContentPane().add(textZ);
		f2.getContentPane().add(btnCheck);
		f2.getContentPane().add(lbl1);
		f2.getContentPane().add(lblMess);
		//set columns
		textX.setColumns(10);
		textY.setColumns(10);
		textZ.setColumns(10);
		
		//add Mouse Listener
		textX.addMouseListener(new MouseAdapter() {
			//click chuột vào thì text biến mất
			@Override
			public void mouseClicked(MouseEvent e) {
				textX.setText("");
			}
		});
		textY.addMouseListener(new MouseAdapter() {
			//click chuột vào thì text biến mất
			@Override
			public void mouseClicked(MouseEvent e) {
				textY.setText("");
			}
		});
		textZ.addMouseListener(new MouseAdapter() {
			//click chuột vào thì text biến mất
			@Override
			public void mouseClicked(MouseEvent e) {
				textZ.setText("");
			}
		});
		
		// add Action Listener
		btnCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				 if(!textX.getText().equals("")&& !textY.getText().equals("")&& !textZ.getText().equals("")) {
					Point position = new Point(Double.parseDouble(textX.getText()),
										Double.parseDouble(textY.getText()),
                            			Double.parseDouble(textZ.getText()));
					System.out.println(position.toString());
					GuiMain.mess = GuiMain.mess + "ok \n";
					GuiMain.textArea.setText(GuiMain.mess);
					CalculateHiddenArea hidden = new CalculateHiddenArea(rm);
					Camera cam = hidden.pointIsHidden(position);
					if(cam == null) {
						GuiMain.mess = GuiMain.mess + "This point is hidden \n";
						GuiMain.textArea.setText(GuiMain.mess);
					}
					else {
						GuiMain.mess = GuiMain.mess + "This point could see. \n+Is seen by camera: " + cam.camPosition.toString() + "\n";
						GuiMain.textArea.setText(GuiMain.mess);
					}
				 }
				f2.dispose();
				 
			}
		});

	}
}
