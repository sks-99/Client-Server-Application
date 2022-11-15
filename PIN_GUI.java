import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PIN_GUI {
	JFrame frame = new JFrame();
	JPanel Panel = new JPanel();
	JLabel label = new JLabel("PIN a message");
	JLabel X_Label= new JLabel("X Coordinate");
	JLabel Y_Label= new JLabel("Y Coordinate");
	
	
	JTextField X_Input= new JTextField();
	JTextField Y_Input= new JTextField();
	
	JButton EnterButton= new JButton();
	
	
	
	
	PIN_GUI(){
		EnterButton.addActionListener(e -> {
			try {
				EnterHandler(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		frame.setSize(500, 300 );
		
		label.setBounds(20, 0, 150, 20);
		label.setFont(new Font(null, Font.BOLD, 15));
		
		EnterButton.setText("ENTER");
		EnterButton.setBounds(200, 180, 100, 50);
		EnterButton.setFont(new Font(null, Font.PLAIN, 14));
		
		X_Label.setBounds(100, 50, 100, 50);
		X_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		Y_Label.setBounds(300, 50, 100, 50);
		Y_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		X_Input.setBounds(100,100, 100, 50);
		Y_Input.setBounds(300,100, 100, 50);
		
		EnterButton.setBackground(new Color(110, 215, 208));
		frame.setBackground(new Color(219, 238, 236));
		Panel.setSize(500, 300 );
		Panel.setLayout(null);
		Panel.setBackground(new Color(219, 238, 236));
		Panel.add(EnterButton);
		Panel.add(Y_Input);
		Panel.add(X_Input);
		Panel.add(Y_Label);
		Panel.add(X_Label);
		Panel.add(label);
		frame.add(Panel);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	private void EnterHandler(ActionEvent e) throws IOException {
	// TODO Auto-generated method stub
	if(e.getSource()==EnterButton) {
			String x = X_Input.getText();
			String y = Y_Input.getText();
			
			GUI_S.out.println("PIN "+x+" "+y);
			frame.dispose();
			GUI_S.loop_connection();
			}	
	}
}
