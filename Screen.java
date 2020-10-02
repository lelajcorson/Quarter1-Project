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
import javax.swing.JScrollPane;

public class Screen extends JPanel implements ActionListener{
    private JButton add;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantField;
    private JTextArea display;
	private Font large;
	private Font small;
    private Font medium;
	private String groceryList;

	public Screen(){
		setLayout(null);

        Set<Item> hash = new HashSet<Item>();
        Set<Item> tree = new TreeSet<Item>();

        try {
			Scanner scan = new Scanner(new FileReader("StoreA.txt"));
			
			//reads one line at a time
			while (scan.hasNextLine()){
			   //System.out.println(scan.nextLine());
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
		small = new Font("Arial", Font.PLAIN, 25);
        medium = new Font("Arial", Font.PLAIN, 35);
		groceryList = "";

		Iterator it = tree.iterator();

		while(it.hasNext()){
			groceryList += it.next();
		}

        add = new JButton("Add Item");
		add.setBounds(335, 300, 100, 50);
		add.addActionListener(this);
		add(add);

        nameField = new JTextField();
		nameField.setBounds(345, 140, 150, 30);
		add(nameField);

		priceField = new JTextField();
		priceField.setBounds(345, 200, 150, 30);
		add(priceField);

		quantField = new JTextField();
		quantField.setBounds(345, 260, 150, 30);
		add(quantField);

        display = new JTextArea(groceryList);

		JScrollPane scrollPane = new JScrollPane(display); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30,80,200,500);
		add(scrollPane);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
		g.setFont(large);
		g.drawString("Food 'n Stuff", 250, 50);

		g.setFont(medium);
		g.drawString("Add to Cart", 275, 110);

		g.setFont(small);
		g.drawString("Name:", 240, 162);
		g.drawString("Price:", 240, 222);
		g.drawString("Quantity:", 240, 282);

		for(int i = 10; i <= 800; i += 10){
			g.drawLine(i, 0, i, 600);
		}
		for(int l = 10; l <= 600; l += 10){
			g.drawLine(0, l, 800, l);
		}
	}
	
	public void actionPerformed(ActionEvent eV){
        
		repaint();
	}
}