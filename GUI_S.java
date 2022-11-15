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

public class GUI_S extends JFrame{
	
	JButton GetButton;
	JButton PostButton;
	JButton PinButton;
	JButton UnpinButton;
	JButton ClearButton;
	JButton ShakeButton;
	JButton ConnectButton;
	JButton DisconnectButton;
	
	JTextField IP_Text;
	JTextField Port_Text;
	JTextField Input_Message;
	public static JTextArea Server_Message;
	
	JLabel IP_Label;
	JLabel Port_Label;
	JLabel Input_Label;
	JLabel Server_Label;
	JLabel Title_Label;
	JLabel Names_Label;
	
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
    public static Socket kkSocket = null;
    public static PrintWriter out = null;
    public static BufferedReader in = null;
    public static BufferedReader stdIn;
	
	GUI_S(){
		//Terminates Program after clicking close on window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLayout(null);
		this.setTitle("Client Server Note Application");
		this.setLayout(new BorderLayout());
		
		//Buttons 
		GetButton= new JButton("GET");
		PostButton= new JButton("POST");
		PinButton= new JButton("PIN");
		UnpinButton= new JButton("UNPIN");
		ClearButton= new JButton("CLEAR");
		ShakeButton= new JButton("SHAKE");
		ConnectButton= new JButton("CONNECT");
		DisconnectButton= new JButton("DISCONNECT");
		
		//Add Action Listener to Components
		PinButton.addActionListener(this::PinHandler);
		UnpinButton.addActionListener(this::UnpinHandler);
		GetButton.addActionListener(this::GetHandler);
		
		ShakeButton.addActionListener(e -> {
			try {
				ShakeHandler(e);
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});
		ClearButton.addActionListener(e -> {
			try {
				ClearHandler(e);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		ConnectButton.addActionListener(e -> {
			try {
				ConnectHandler(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		DisconnectButton.addActionListener(e -> {
			try {
				DisconnectHandler(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		PostButton.addActionListener(e -> {
			try {
				PostHandler(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Text Fields
		IP_Text= new JTextField();
		Port_Text = new JTextField();
		Input_Message = new JTextField();
		Server_Message = new JTextArea();
		Server_Message.setLineWrap(true);
		Server_Message.setWrapStyleWord(true);
		
		
		//Labels
		IP_Label= new JLabel("IP Address");
		Port_Label= new JLabel("Port Number");
		Input_Label= new JLabel("Input Message");
		Server_Label= new JLabel("Server Message");
		Title_Label= new JLabel("Client Server Note Application");
		Names_Label= new JLabel("Created by Daniel Zhou & Sumeet Sandhu");
		
		
		// (Panel 3) Set Bounds for Server Panel 
		Server_Label.setBounds(20, 0, 150, 20);
		Server_Label.setFont(new Font(null, Font.BOLD, 15));
		Server_Message.setBounds(20,20, 210,210);
		
		
		// (Panel 2) Set Bounds for Input Panel
		Input_Label.setBounds(70, 0, 150, 20);
		Input_Label.setFont(new Font(null, Font.BOLD, 15));
		Input_Message.setBounds(70,20, 210,210);
				
		// (Panel 1) Set bounds for IP/Port/Buttons/Names
		IP_Label.setBounds(10, 30, 100,20);
		IP_Text.setBounds(10, 60, 200, 50);
		Port_Label.setBounds(250, 30, 100, 20);
		Port_Text.setBounds(250, 60, 200, 50);
		Title_Label.setBounds(400,0,300,20);
		ConnectButton.setBounds(550 , 60, 200, 50);
		DisconnectButton.setBounds(780 , 60, 200, 50);
		Names_Label.setBounds(780,0,300,20);
		
		
		
		Title_Label.setFont(new Font(null, Font.TYPE1_FONT, 18));
		IP_Label.setFont(new Font(null, Font.BOLD, 15));
		Port_Label.setFont(new Font(null, Font.BOLD, 15));
		Names_Label.setFont(new Font(null, Font.ITALIC, 10));
		
		
		
		//Panels
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel4= new JPanel();
		
		panel1.setBackground(new Color(219, 238, 236));
		panel2.setBackground(new Color(219, 238, 236));
		panel3.setBackground(new Color(219, 238, 236));
		panel4.setBackground(new Color(219, 238, 236));
		
		
		panel1.setPreferredSize(new Dimension(1000, 200));
		panel2.setPreferredSize(new Dimension(300, 400));
		panel3.setPreferredSize(new Dimension(300, 400));
		panel4.setPreferredSize(new Dimension(400, 400));
		
		//Button Colors
		ShakeButton.setBackground(new Color(110, 215, 208));
		ConnectButton.setBackground(new Color(110, 215, 208));
		DisconnectButton.setBackground(new Color(110, 215, 208));
		PinButton.setBackground(new Color(110, 215, 208));
		UnpinButton.setBackground(new Color(110, 215, 208));
		ClearButton.setBackground(new Color(110, 215, 208));
		GetButton.setBackground(new Color(110, 215, 208));
		PostButton.setBackground(new Color(110, 215, 208));
		
		
		//Panel 1 Components (IP, Port)
		panel1.setLayout(null);
		panel1.add(IP_Label);
		panel1.add(IP_Text);
		panel1.add(Port_Label);
		panel1.add(Port_Text);
		panel1.add(ConnectButton);
		panel1.add(DisconnectButton);
		panel1.add(Title_Label);
		panel1.add(Names_Label);
		

		//Panel 2 Components (Input Box)
		panel2.setLayout(null);
		panel2.add(Input_Label);
		panel2.add(Input_Message);
		
		//Panel 3 Components (Server Box)
		panel3.setLayout(null);
		panel3.add(Server_Label);
		panel3.add(Server_Message);
		
		//Panel 4 Components (Buttons)
		panel4.setLayout(new GridLayout(5,0));
		
		panel4.add(PostButton);
		panel4.add(ShakeButton);
		panel4.add(GetButton);
		panel4.add(ClearButton);
		panel4.add(PinButton);
		panel4.add(UnpinButton);
		
		
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.WEST);
		this.add(panel3, BorderLayout.EAST);
		this.add(panel4, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	
	
	public void PinHandler(ActionEvent e) {
		if(e.getSource()==PinButton) {
			new PIN_GUI();
		}
	}
	
	public void UnpinHandler(ActionEvent e) {
		if(e.getSource()==UnpinButton) {
			new UNPIN_GUI();
		}
	}
	
	public void GetHandler(ActionEvent e) {
		if(e.getSource()==GetButton) {
			//Closes original window
			new GET_GUI();
		}
	}
	public void ConnectHandler(ActionEvent e) throws UnknownHostException, IOException {
		if(e.getSource()==ConnectButton) {
			try {
				int getPort= Integer.parseInt(Port_Text.getText());
				String getIP= IP_Text.getText();
			    kkSocket = new Socket(getIP, getPort);
			    out = new PrintWriter(kkSocket.getOutputStream(), true);
			    in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		        stdIn = new BufferedReader(new InputStreamReader(System.in));
		        out.println("Hello! I am a new Client!");
		        loop_connection();				
			}catch(IOException e1) {
				Server_Message.setText("Wrong server IP and/or port number");
			}

		}
	}
	public static void loop_connection() throws IOException {
	 		String fromServer;
			while ((fromServer = in.readLine()) != null) {
				Server_Message.setText(fromServer);
				break;
			}
   }
	public void DisconnectHandler(ActionEvent e) throws IOException{
		if(e.getSource()==DisconnectButton) {
		        out.close();
		        in.close();
		        stdIn.close();
		        kkSocket.close();
		        this.dispose();
			}
		}


	public void PostHandler(ActionEvent e) throws IOException {
		if(e.getSource()==PostButton) {
			String getValue= Input_Message.getText();
			out.println("POST "+getValue);
			loop_connection();
			}	
		}
	public void ClearHandler(ActionEvent e) throws IOException {
		if(e.getSource()==ClearButton) {
			out.println("CLEAR");
			loop_connection();
			}	
		}
	public void ShakeHandler(ActionEvent e) throws IOException {
		if(e.getSource()==ShakeButton) {
			out.println("SHAKE");
			loop_connection();
			}	
		}
	}
	
	
	
	
