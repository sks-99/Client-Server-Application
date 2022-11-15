
import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.util.Arrays;

final class MessageProtocol implements Runnable {
    Socket socket;
    private ArrayList<NoteEntry> noteDictionary;
    private ArrayList<Pin> pinList;
    // Constructor
    public MessageProtocol(Socket socket) throws Exception {
	this.socket = socket;
    }
    
    public void run() {
	try {
	    processRequest();
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

 private void processRequest() throws Exception {
	// Get a reference to the socket's input and output streams.
	InputStream is = socket.getInputStream();
	BufferedReader in = new BufferedReader(new InputStreamReader(is));
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	
	String requestMessage;
	String colours="";
	int i;
	for(i=0;i<Server.colours.length;i++) {
		colours+=Server.colours[i]+" ";
	}
	String outputLine = "The dimension of the board is "+Server.board_width+"x"+Server.board_height+". The list of available colours is "+colours;
    out.println(outputLine);
    
    NoteEntry note;
    Pin pin;
    Pin check_pin;
    String request_type;
    int lower_x;
    int lower_y;
    int width;
    int height;
    String colour;
    String message;
    String server_message;
    Boolean flag;

    while ((requestMessage = in.readLine()) != null) {
    	System.out.println("Client says "+requestMessage);
        requestMessage = requestMessage.trim();
        String[] words = requestMessage.split(" ");
        
        request_type = words[0];
        noteDictionary = new ArrayList<NoteEntry>();
        noteDictionary = (ArrayList<NoteEntry>)Server.noteDictionary.clone();

        //noteDictionary = Server.noteDictionary;
        pinList = Server.pinList;
        if (request_type.equals("DISCONNECT")) {
            out.println("Bye.");
            break;
        }else if (request_type.equals("POST")) {
            lower_x = Integer.parseInt(words[1]);
            lower_y = Integer.parseInt(words[2]);
            width = Integer.parseInt(words[3]);
            height = Integer.parseInt(words[4]);
            colour = words[5];
            List<String> list = Arrays.asList(Server.colours);
            if(!list.contains(colour)) {    	
            	out.println("Please select a valid colour from the list of available colours. Error.");
            }else if((lower_x >Server.board_width)||(lower_y>Server.board_height)||(lower_x+width)<0||(lower_y+height)<0) {
            	out.println("Please post a note that fits within the board dimensions. Error.");
            }
            else{
                message ="";
                if(words.length>6) {
                    for(i=6;i<words.length;i++) {
                    	if(i==words.length-1) {
                    		message+=words[i];
                    	}else {
                        	message+=words[i]+" ";
                    	}          	
                    }
                }
            	note = new NoteEntry(lower_x,lower_y,width,height,colour,message);
            	Server.noteDictionary.add(note);
            	out.println("Your note was successfully posted!");
            }
        	
        //CASE FOR WHEN CLIENT REQUEST IS GET
        }else if (request_type.equals("GET")) {
        	if(noteDictionary.size()!=0) {
                String color = words[1];
                String data = words[2];
                String refersTo = words[3];
                
                int data_x=0;
                int data_y=0;
                if(color.length()>6) {
                	color = color.substring(6);
                	System.out.println(color);
                }else {
                	color="";
                }
                
                if(data.length()>5) {
                	data=data.substring(5);
                	System.out.println(data);
                	String[] coordinates = data.split(",",2);
                	data_x = Integer.parseInt(coordinates[0]);
                	data_y = Integer.parseInt(coordinates[1]);
                	System.out.println(data_x);
                }else{
                	data="";
                };
                
                if(refersTo.length() > 9) {
                	refersTo = refersTo.substring(9);
                	if (words.length>4) {
                		for(i = 4; i<words.length;i++) {
                			refersTo+= " "+words[i];
                		}
                	}
                }else {
                	refersTo="";
                }
                
                final String final_color = color;
                if(color!="") {
                	noteDictionary.removeIf(theNote -> !theNote.getcolor().equals(final_color));
                }
                
                final String final_refersTo = refersTo;
                if(refersTo!="") {
                	System.out.println(refersTo);
                	noteDictionary.removeIf(theNote -> !theNote.getmessage().contains(final_refersTo));
                }
                
                final int fin_data_x = data_x;
                final int fin_data_y = data_y;
                if(data!="") {
                	noteDictionary.removeIf(theNote->(fin_data_x<(theNote.getx()))||(fin_data_x>(theNote.getx()+theNote.getwidth())));
                	noteDictionary.removeIf(theNote->(fin_data_y<(theNote.gety()))||(fin_data_y>(theNote.gety()+theNote.getheight())));
                }
        		server_message="";
                if(noteDictionary.size()==0) {
                	server_message="No notes with the given criteria can be found.";;
                }else {
            		for(i=0;i<noteDictionary.size();i++) {
            			note = noteDictionary.get(i);
            			server_message+="Found note with lower left-corner of " + note.getx() +","+note.gety()+" Width: "+note.getwidth()+" Height: "+note.getheight()+" Color: "+note.getcolor()+" PinCount: "+note.getpinCount()+" Message: "+note.getmessage();
            			if(i!=noteDictionary.size()-1) {
            				server_message+=", ";
            			}
            		}
                }
    			out.println(server_message);
        	}else {
        		out.println("There are no existing notes on the board. Please try again later.");
        	}
        }
        
        else if (request_type.equals("PIN")) {
        	int x = Integer.parseInt(words[1]);
        	int y = Integer.parseInt(words[2]);
    		pin = new Pin(x,y);
    		
        	if((x>=0 && x<=Server.board_width) && (y>=0&&y<=Server.board_height)) {
        		flag = false;
        		for (i=0;i<pinList.size();i++) {
        			check_pin = pinList.get(i);
        			if(x==check_pin.getX() && y == check_pin.getY()) {
        				flag = true;
        			}
        		}
        		if (flag==true){
        			out.println("Pin already exists. Invalid input - please try again.");
	        		}else {
	        			Server.pinList.add(pin);
	            		for (i=0;i<noteDictionary.size();i++) {
	            			note = noteDictionary.get(i);
	            			int x_min = note.getx();
	            			int x_max = x_min + note.getwidth();
	            			int y_min = note.gety();
	            			int y_max = y_min + note.getheight();
	            			if((x>=x_min&&x<=x_max) && (y>=y_min&&y<=y_max)) {
	            				note.changePinCount(true);
	            				Server.noteDictionary.set(i, note);
	            				}	
	            			}
	            		out.println("Successfully added pin to the board at coordinates "+x+","+y);
	        		}         		
        		}else {
        			out.println("Please insert a pin with valid coordinates that fits in the board");
        		}
        	}
        
        else if (request_type.equals("UNPIN")){
        	int x = Integer.parseInt(words[1]);
        	int y = Integer.parseInt(words[2]);
        	
        	if(pinList.size()==0) {
        		out.println("There are no existing pins. Invalid input.");
        	}
        	
        	else if((x>=0 && x<=Server.board_width) && (y>=0&&y<=Server.board_height)) {
        		flag = false;
        		for (i=0;i<pinList.size();i++) {
        			check_pin = pinList.get(i);
        			if(x==check_pin.getX() && y == check_pin.getY()) {
        				flag = true;
        				Server.pinList.remove(i);
        			}
        		}
        		
        		if (flag==false){
        			out.println("Pin does not exist. Invalid input - please try again.");
	        		}else {
	            		for (i=0;i<noteDictionary.size();i++) {
	            			note = noteDictionary.get(i);
	            			int x_min = note.getx();
	            			int x_max = x_min + note.getwidth();
	            			int y_min = note.gety();
	            			int y_max = y_min + note.getheight();
	            			if((x>=x_min&&x<=x_max) && (y>=y_min&&y<=y_max)) {
	            				note.changePinCount(false);
	            				Server.noteDictionary.set(i, note);
	            				}	
	            			}
	            		out.println("Successfully UNPINNED from the board at coordinates "+x+","+y);
	        		} 		
        	}else {
        		out.println("Please try to unpin a pin with valid coordinates that fits in the board");
        	}
        }else if(request_type.equals("SHAKE")){
        	Server.noteDictionary.removeIf(thenote -> thenote.getpinCount()==0);
        	out.println("Removed all notes that do not have a pin.");
        }else if (request_type.equals("CLEAR")) {
        	Server.noteDictionary.clear();
        	Server.pinList.clear();
        	out.println("All notes and pins have been cleared.");
        }
    }
}
}
