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
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Screen extends JPanel implements ActionListener{
    private JButton add;
	private JTextField searchField;
    private JTextArea display;
	private Font large;
	private Font small;
    private Font verySmall;

	public Screen(){
		setLayout(null);

        Set<Item> hash = new HashSet<Item>();
        Set<Item> tree = new TreeSet<Item>();

        try {
			Scanner scan = new Scanner(new FileReader("StoreA.txt"));
			
			//reads one line at a time
			while (scan.hasNextLine()){
			   System.out.println(scan.nextLine());
               String word = scan.nextLine();
               String[] array = word.split(",");
               hash.add(new Item(array[0], Double.parseDouble(array[1])));
               tree.add(new Item(array[0], Double.parseDouble(array[1])));
			}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

        display = new JTextArea("hi");
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