

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
public class Server {
    
	public static int port;
    public static int board_width;
    public static int board_height;
    public static String colours[];
    public static ArrayList<NoteEntry> noteDictionary = new ArrayList<NoteEntry>();
    public static ArrayList<Pin> pinList = new ArrayList<Pin>();
    
    public static void main(String argv[]) throws Exception {
	// Get the port number from the command line.
    if (argv.length >= 4) {
	    port = Integer.parseInt(argv[0]);
	    board_width = Integer.parseInt(argv[1]);
	    board_height = Integer.parseInt(argv[2]);
	    
	    colours = new String[argv.length-3];
	    		
	
	    for(int i=3; i< argv.length;i++) {
	    	colours[i-3]=argv[i];
	    }
	    
		// Establish the listen socket.
		ServerSocket socket = new ServerSocket(port);
	
		// Process HTTP service requests in an infinite loop.
		while (true) {
		    // Listen for a TCP connection request.
		    Socket clientSocket = socket.accept();

		    // Construct an object to process the HTTP request message.
		    MessageProtocol request = new MessageProtocol(clientSocket);
		    
		    // Create a new thread to process the request.
		    Thread thread = new Thread(request);
		    
		    // Start the thread.
		    thread.start();
			}
	    }
    }
}

