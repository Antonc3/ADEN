
import java.awt.*;
import javax.swing.*;

public class PlainPanelMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Simple Paint Enhanced");
		PlainPanel p = new PlainPanel();
		window.setSize(700,500);
		p.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

		window.setContentPane( p );
		window.setJMenuBar(p.getMenuBar());
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(true);
        

	}
    
}