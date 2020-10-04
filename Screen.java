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
	private JButton add2;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantField;
	private JTextField nameAddField;
	private JTextField priceAddField;
    private JTextArea display;
	private Font large;
	private Font small;
    private Font medium;
	private String groceryList;
	private ArrayList<Pair<Item, Integer>> cart;
	private Set<Item> hash;
	private Set<Item> tree;
	private JTextArea display2;
	private String cartString;
	private boolean cartContains;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private int index;
	private String totalPrice;
	private double totalPriceD;
	private boolean priceChange;

	public Screen(){
		setLayout(null);

        hash = new HashSet<Item>();
        tree = new TreeSet<Item>();
		cart = new ArrayList<Pair<Item, Integer>>();

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
		cartString = "Item\tPrice\tQuantity\tTotal\n";
		cartContains = false;
		index = 0;
		totalPrice = "";
		totalPriceD = 0.0;
		priceChange = true;

		Iterator it = tree.iterator();

		while(it.hasNext()){
			groceryList += it.next();
		}

        add = new JButton("Add to Cart");
		add.setBounds(275, 300, 100, 50);
		add.addActionListener(this);
		add(add);

		add2 = new JButton("Add to Store");
		add2.setBounds(265, 530, 120, 50);
		add2.addActionListener(this);
		add(add2);

        nameField = new JTextField();
		nameField.setBounds(295, 140, 150, 30);
		add(nameField);

		priceField = new JTextField();
		priceField.setBounds(295, 200, 150, 30);
		add(priceField);

		quantField = new JTextField();
		quantField.setBounds(295, 260, 150, 30);
		add(quantField);

		nameAddField = new JTextField();
		nameAddField.setBounds(295, 430, 150, 30);
		add(nameAddField);

		priceAddField = new JTextField();
		priceAddField.setBounds(295, 480, 150, 30);
		add(priceAddField);

        display = new JTextArea(groceryList);

		scrollPane = new JScrollPane(display); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20,80,160,500);
		add(scrollPane);

		display2 = new JTextArea(cartString);
		scrollPane2 = new JScrollPane(display2); 
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(460,80,320,450);
		add(scrollPane2);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
		g.setFont(large);
		g.drawString("Food 'n Stuff", 250, 50);

		g.setFont(medium);
		g.drawString("Add to Cart", 225, 110);
		g.drawString("Add to Store", 220, 400);

		g.setFont(small);
		g.drawString("Name:", 190, 162);
		g.drawString("Price:", 190, 222);
		g.drawString("Quantity:", 190, 282);
		g.drawString("Name:", 190, 450);
		g.drawString("Price:", 190, 510);
		g.drawString("Total: " + totalPrice, 460, 560);

		/*for(int i = 10; i <= 800; i += 10){
			g.drawLine(i, 0, i, 600);
		}
		for(int l = 10; l <= 600; l += 10){
			g.drawLine(0, l, 800, l);
		}*/
	}
	
	public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
			String nameInput = nameField.getText();
			double priceInput = Double.parseDouble(priceField.getText());
			Integer quantInput = Integer.parseInt(quantField.getText());
			Item itemAdd = new Item(nameInput, priceInput);

			if(hash.contains(itemAdd)){
				for(int i = 0; i < cart.size(); i ++){
					if(cart.get(i).getItem().equals(itemAdd)){
						index = i;
						cartContains = true;
					}
				}
				if(cartContains == false && quantInput > 0){
					cart.add(new Pair<Item, Integer>(itemAdd, quantInput));
				}
				else if(quantInput >= 0 || Math.abs(quantInput) < cart.get(index).getQuantInt()){
					int oldQuant = cart.get(index).getQuantInt();
					cart.get(index).setQuant(oldQuant + quantInput);
					cartContains = false;					
				}
				else if(Math.abs(quantInput) - cart.get(index).getQuantInt() == 0){
					cart.remove(index);
					totalPriceD = 0;
					priceInput = 0;
				}
				else{
					priceChange = false;
				}
				
				if(priceChange){
					totalPriceD += priceInput * quantInput;
					totalPrice = "";
					totalPrice = "" + totalPriceD;
				}
				else{
					priceChange = true;
				}
			}
			else{
				System.out.println("Sorry, that Item could not be found.");
			}

			cartString = "Item\tPrice\tQuantity\tTotal\n";
			for(int i = 0; i < cart.size(); i ++){
				cartString += cart.get(i).toString();
			}

			display2.setText(cartString);
		}
		else if(e.getSource() == add2){
			String nameInput2 = nameAddField.getText();
			double priceInput2 = Double.parseDouble(priceAddField.getText());

			Item itemAdd2 = new Item(nameInput2, priceInput2);

			if(!hash.contains(itemAdd2)){
				hash.add(itemAdd2);
				tree.add(itemAdd2);
			}

			Iterator it = tree.iterator();
			groceryList = "";

			while(it.hasNext()){
				groceryList += it.next();
			}

			display.setText(groceryList);
		}
		repaint();
	}
}