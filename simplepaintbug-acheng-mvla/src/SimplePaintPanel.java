
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SimplePaintPanel extends JPanel {

	private int prevX, prevY;     // The previous location of the mouse.
    private boolean dragging;      // This is set to true while the user is drawing.
    private Color currentColor = Color.BLACK;  // The currently selected drawing color    
    private ArrayList<Line> lines = new ArrayList<Line>();
    
    SimplePaintPanel() {
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).

        for(int i = 0; i < lines.size(); i++) {
            g.setColor(lines.get(i).getColor());
        		g.drawLine(lines.get(i).getX1(), lines.get(i).getY1(), lines.get(i).getX2(), lines.get(i).getY2());
        }
    } // end paintComponent()

    public ArrayList<Line> getLines() {
    		return lines;
    }
    
    public void addLine(int prevX, int prevY, int currentX, int currentY) {
    		lines.add(new Line(prevX, prevY, currentX, currentY, currentColor));
    }
    
    public void clearAllLines() {
    		lines = new ArrayList<Line>();
    }
    
    public void removeOneLine() {
    		if(lines.size() == 0)
    			return;
    		else
    			lines.remove(lines.size() - 1);
    }
    
	public int getPrevX() {
		return prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}

	public boolean isDragging() {
		return dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

    public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}


} // end class SimplePaintPanel
