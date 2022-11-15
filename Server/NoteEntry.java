
public class NoteEntry {
    private int x;
    private int y;
    private int width;
    private int height;
    private int pinCount = 0;
    private String color;
    private String message;

    public NoteEntry(int x, int y, int width, int height, String color, String message) {
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height=height;
    	this.color = color;
    	this.message=message;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }
    
    public int getwidth() {
    	return width;
    }
    
    public int getheight() {
    	return height;
    }
    
    public int getpinCount() {
    	return pinCount;
    }

    public void changePinCount(boolean increase) {
        if(increase == true){
        	this.pinCount+=1;
        }else {
        	this.pinCount-=1;
        }
    }
    
    public void resetPins() {
    	this.pinCount = 0;
    }

    public String getcolor() {
        return color;
    }

    public String getmessage() {
        return message;
    }
}
