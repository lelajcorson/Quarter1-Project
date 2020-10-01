import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Screen extends JPanel implements ActionListener{
    private JButton add;
	private JTextField searchField;
    private JTextArea display;
	private Font large;
	private Font small;
    private Font verySmall;

	public Screen(){
		setLayout(null);

		large = new Font("Arial", Font.PLAIN, 40);
		small = new Font("Arial", Font.PLAIN, 20);
        verySmall = new Font("Arial", Font.PLAIN, 15);

        add = new JButton("Add Class");
		add.setBounds(560, 250, 100, 50);
		add.addActionListener(this);
        add.setVisible(false);
		add(add);

        searchField = new JTextField();
		searchField.setBounds(50, 100, 200, 30);
		add(searchField);

        display = new JTextArea(displayWord);
		display.setBounds(50, 175, 400, 350);
        display.setFont(small);
        display.setEditable(false);
		add(display);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
		
	}
	
	public void actionPerformed(ActionEvent eV){
        
		repaint();
	}
}