import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GET_GUI extends JFrame{
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel("GET a message");
	JLabel Color_Label= new JLabel("Color");
	JLabel Contains_Label= new JLabel("Contains");
	JLabel Refer_Label= new JLabel("Refers to");
	JLabel X_Label= new JLabel("X");
	JLabel Y_Label= new JLabel("Y");
	JPanel Panel= new JPanel();
	
	
	
	
	JTextField Color_Input= new JTextField("");
	JTextField Contains_x_Input= new JTextField("");
	JTextField Contains_y_Input= new JTextField("");
	
	
	JTextField Refers_Input= new JTextField("");
	
	JButton EnterButton= new JButton();
	
	
	
	GET_GUI(){
		EnterButton.addActionListener(e -> {
			try {
				EnterHandler(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		label.setBounds(0, 0, 150, 50);
		label.setFont(new Font(null, Font.BOLD, 15));
		
		Color_Label.setBounds(50, 50, 100, 50);
		Color_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		Contains_Label.setBounds(200, 50, 100, 50);
		Contains_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		EnterButton.setText("ENTER");
		EnterButton.setBounds(200, 200, 100, 50);
		EnterButton.setFont(new Font(null, Font.PLAIN, 14));
		
		Refer_Label.setBounds(350, 50, 100, 50);
		Refer_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		
		Color_Input.setBounds(50,100, 100, 50);
		Contains_y_Input.setBounds(250,100, 50, 50);
		Contains_x_Input.setBounds(200,100, 50, 50);
		
		
		X_Label.setBounds(225,130, 50, 50);
		Y_Label.setBounds(275,130, 50, 50);
		
		
		
		frame.setSize(500,300);
		Refers_Input.setBounds(350,100, 100, 50);
		
		EnterButton.setBackground(new Color(110, 215, 208));
		
		Panel.setSize(500, 300 );
		Panel.setBackground(new Color(219, 238, 236));
		Panel.setLayout(null);
		
		Panel.add(X_Label);
		Panel.add(Y_Label);
		Panel.add(EnterButton);
		Panel.add(Color_Input);
		Panel.add(Contains_x_Input);
		Panel.add(Contains_y_Input);
		Panel.add(Refers_Input);
		Panel.add(Refer_Label);
		Panel.add(Contains_Label);
		Panel.add(Color_Label);
		Panel.add(label);
		

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(Panel);
		frame.setVisible(true);
	}
	private void EnterHandler(ActionEvent e) throws IOException {
	// TODO Auto-generated method stub
	if(e.getSource()==EnterButton) {
			String color = Color_Input.getText();
			String x = Contains_x_Input.getText();
			String y = Contains_y_Input.getText();
			String refersTo = Refers_Input.getText();
			
			String message = "";
			if(x.equals("")&&y.equals("")) {
				message="GET color="+color+" data="+" refersTo="+refersTo;
			}else{
				message = "GET color="+color+" data="+x+","+y+" refersTo="+refersTo;
			}
			GUI_S.out.println(message);
			frame.dispose();
			GUI_S.loop_connection();
			}	
	}
}
