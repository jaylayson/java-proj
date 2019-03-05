package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Items.DryItem;
import Items.RefrigeratedItem;
import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Temperatures;
import exceptions.StockException;
import store.Stock;
import store.Store;



/**
 * @author John Layson
 * 
 */
/**
 * @author Nathan Thai
 *
 */
public class SuperMartGUI extends JFrame implements ActionListener{
	
	//VARIABLE INITIALIZATION
    Path itemPropertiesPath = Paths.get("item_properties.csv");
    final String splitter = ",";
    List<RefrigeratedItem> RItems = new ArrayList<RefrigeratedItem>();
    List<DryItem> DItems = new ArrayList<DryItem>();
    Set<Stock> inventory = new LinkedHashSet<Stock>();
    Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
    Map<String, Integer> reorderPointMap = new HashMap<String, Integer>();
    Map<String, Integer> reorderAmountMap = new HashMap<String, Integer>();
    Map<String, Integer> reStock = new HashMap<String, Integer>();
    Map<String, Integer> sales0 = new HashMap<String, Integer>();
    Map<String, Integer> sales1 = new HashMap<String, Integer>();
    Map<String, Integer> sales2 = new HashMap<String, Integer>();
    Map<String, Integer> sales3 = new HashMap<String, Integer>();
    Map<String, Integer> sales4 = new HashMap<String, Integer>();
	Store SuperMart = Store.getInstance();
	private String value;
	double capital = SuperMart.getCapital();
    double totalCost;
	double income;
	
	
	//GUI COMPONENTS INITIALIZATION
	JComboBox comboBox = new JComboBox();
	JButton btnLoad = new JButton("LOAD");
	JButton btnGenerateManifest = new JButton("GENERATE MANIFEST");
	JTextArea textArea = new JTextArea();
	JLabel capValueLabel = new JLabel("$100,000");
	JTable inventoryTable = new JTable();
	JPanel smallContentPanel = new JPanel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SuperMartGUI();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This creates the Frame/GUI as well as setting each components properties such as:
	 * Panels, labels, buttons, text area, and combobox.
	 */
	public SuperMartGUI() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Store.getName());
		
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		frame.getContentPane().add(contentPanel);
				
		
		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setBounds(5,5,785,100);
		topPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPanel.add(topPanel);
		
		
		JLabel capLabel = new JLabel("CAPITAL:");
		capLabel.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		topPanel.add(capLabel);
		

		
		capValueLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		topPanel.add(capValueLabel);
		
		
		smallContentPanel.setBackground(Color.LIGHT_GRAY);
		smallContentPanel.setBounds(5,105,600,350);
		smallContentPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPanel.add(smallContentPanel);
		
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.setBounds(605,105,185,350);
		rightPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		rightPanel.setLayout(null);
		contentPanel.add(rightPanel);
		
		
		btnLoad.setSize(80, 20);
		btnLoad.setLocation(60, 80);
		btnLoad.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		btnLoad.isVisible();
		btnLoad.addActionListener(this);
		rightPanel.add(btnLoad);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Item Properties", "Manifest", "Sales Log 0", "Sales Log 1", "Sales Log 2", "Sales Log 3", "Sales Log 4"}));
		comboBox.setSize(155, 25);
		comboBox.setLocation(15, 50);
		rightPanel.add(comboBox);
		
		
		btnGenerateManifest.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		btnGenerateManifest.setBounds(10, 162, 170, 23);
		btnGenerateManifest.addActionListener(this);
		btnGenerateManifest.setEnabled(false);
		rightPanel.add(btnGenerateManifest);
		
			
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		bottomPanel.setBounds(5,455,785,115);
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottomPanel.setLayout(null);
		contentPanel.add(bottomPanel);
		
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setSize(775, 80);
		textArea.setLocation(5, 30);
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setSize(775, 105);
		scroll.setLocation(5, 5);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		bottomPanel.add(scroll);

		
		JLabel lblItemProperties = new JLabel("ITEM PROPERTIES");
		lblItemProperties.setSize(140, 20);
		lblItemProperties.setLocation(330, 5);
		lblItemProperties.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		scroll.add(lblItemProperties);
		
		
		inventoryTable.setLayout(new FlowLayout());
		inventoryTable.setBounds(5,5,590,340);		
		inventoryTable.setPreferredScrollableViewportSize(new Dimension(575,340));
		inventoryTable.setFillsViewportHeight(true);
		inventoryTable.setVisible(true);
		smallContentPanel.add(inventoryTable);
		
		
		JScrollPane jsp;
		jsp = new JScrollPane(inventoryTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setSize(590, 340);
		jsp.setLocation(5, 5);
		smallContentPanel.add(jsp);	
			
	}
	/*
	 * Initializes the table giving it rows and columns.
	 */
	public void initializeTable() throws IOException {
		String [] columnNames = {"Name", "Cost", "Price", "Reorder Point", "Reorder Amount", "Temperature", "Quantity"};

			    		Object[][] dataSet1 = {
			    				{},
	    					};
			    		

			    		TableModel model;
			    		model = new DefaultTableModel(dataSet1, columnNames);
			    		inventoryTable.setModel(model);
			    		populateTable();
			    		
			    		
			    		
	}
	
	/*
	 * Adds rows to the table.
	 */
	public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        
        Object rowData[] = new Object[7];
        for(int i = 0; i < DItems.size(); i++){
            rowData[0] = DItems.get(i).itemName();
            rowData[1] = DItems.get(i).itemCost();
            rowData[2] = DItems.get(i).itemPrice();
            rowData[3] = DItems.get(i).reorderPoint();
            rowData[4] = DItems.get(i).reorderAmount();
            rowData[6] = inventoryMap.get(DItems.get(i).itemName());
            model.addRow(rowData);
        }
        
        for(int i = 0; i < RItems.size(); i++){
            rowData[0] = RItems.get(i).itemName();
            rowData[1] = RItems.get(i).itemCost();
            rowData[2] = RItems.get(i).itemPrice();
            rowData[3] = RItems.get(i).reorderPoint();
            rowData[4] = RItems.get(i).reorderAmount();
            rowData[5] = RItems.get(i).temperature();
            rowData[6] = inventoryMap.get(RItems.get(i).itemName());
            model.addRow(rowData);
        }
	}
	

	/*
	 * This method sets the value and keeps track of which option is selected in comboBox
	 */
	public void comboTrack(JComboBox box){
	    this.comboBox = box;
	    this.value = box.getSelectedItem().toString();
	}
	
	
	/*
	 * This method changes the value when a option is selected in comboBox.
	 */
	public void update(){
	    this.value = comboBox.getSelectedItem().toString();
	}
	
	
	/*
	 * This method gets the selected value of the comboBox.
	 */
	public String getValue(){
	    return value;
	}
	
	/*
	 * This method changes the text in textArea component.
	 */
	public void printTextField(String text) {
	    textArea.setText(text);
	}
	
	/*
	 * This method loads item properties and gives all items 0 quantity to begin with.
	 */
	public void loadItems() throws IOException {
	      List<String> lines = Files.readAllLines(itemPropertiesPath);	
	    	if(DItems.isEmpty()) {
			    for(String line : lines) {
			    	String[] token = line.split(splitter);
			    		if(token.length == 5) {
			    			DItems.add(new DryItem(token[0],Integer.parseInt(token[1]), 
					    		   Integer.parseInt(token[2]), Integer.parseInt(token[3]), 
					    		   Integer.parseInt(token[4])));
			    		} else {
			    			RItems.add(new RefrigeratedItem(token[0],Integer.parseInt(token[1]), 
					    		   Integer.parseInt(token[2]), Integer.parseInt(token[3]), 
					    		   Integer.parseInt(token[4]),Integer.parseInt(token[5])));
			    		}		          	
			    }
			    textArea.append((DItems.size() + RItems.size()) + "items loaded" + "\n");
			    for(Object o : DItems) {
					textArea.append(o + "\n");
				}
			    
			    for(Object o : RItems) {
			    	textArea.append(o + "\n");
			    }
			    
			    
	    	} else {
	    		textArea.setText(null);
	    		printTextField("NOTE: Item Properties already loaded, no action taken");
	    	}
		    //Creates Stock in inventory collection, and gives them 0 quantity
		    for(int i = 0; i < 8; i++) {
		    	inventory.add(new Stock(DItems.get(i).itemName(), 0));
		    }
		    for(int i = 0; i < 16; i++) {
		    	inventory.add(new Stock(RItems.get(i).itemName(), 0));
		    }
		}
	/*
	 * This creates the inventory from the list created when items were created.
	 */
	public void mapInventory() {
	    Iterator iterator = inventory.iterator();   
	    while(iterator.hasNext())
	    {	
	    		Stock object = (Stock) iterator.next();
	    		int currentQuantity = object.getQuantity();
	    		inventoryMap.put(object.getName(), currentQuantity);
	    }
	}
	
	/*
	 * This creates a hashmap for the reorder amounts for each item.
	 */
	public void mapReorders() throws IOException {

		List<String> lines = Files.readAllLines(itemPropertiesPath);
	    for(String line : lines)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int reorderP = Integer.parseInt(token[3]);
	    	
	    	reorderPointMap.put(name, reorderP);
	    	
	    }
	    
	    for(String line : lines)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int reorderA = Integer.parseInt(token[4]);
	    	
	    	reorderAmountMap.put(name, reorderA);
	    	
	    }
	}
	
	/*
	 * This method checks the inventory for which items need restocking
	 * which is then included in the manifest creation.
	 */
	public void generateManifest() throws IOException {
		File file = new File("D:/Work/git/inventory-management-application-john-layson-nathan-thai/project/Manifest1.csv");
		if(file.exists()) {
			file.delete();
		} 
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();
			
			if(reorderPointMap.containsKey(token1)) 
    		{
    			if (token <= reorderPointMap.get(token1).intValue())
    			{
    				Manifest manifest = new Manifest(token1, reorderAmountMap.get(token1).intValue());
	    			manifest.generateManifest(token1, reorderAmountMap.get(token1).intValue());
	    			
	    			if(!reStock.containsKey(token1)) {
	    				reStock.put(token1, reorderAmountMap.get(token1).intValue());
	    			} else {
	    				reStock.clear();
	    				reStock.put(token1, reorderAmountMap.get(token1).intValue());
	    			}
	    			
    			}
	    	}
		}
		textArea.setText(null);
		printTextField("Manifest Generated");
		
	}
	
	/*
	 * This calculates the cost of buying all the items to go into stock
	 * and also the cost for the amount of trucks needed to deliver the cargo.
	 */
	public void calculateCosts() {
		int sumOfRef = 0;
		int sumOfOrd = 0;
		int sumOfICost = 0;
		double requiredTemp = 0;
		
		for (Map.Entry<String, Integer> entry : reStock.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();
			
			switch(token1) {
			
			case "mushrooms":
				if(Temperatures.mushrooms.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.mushrooms.getTemp();
				}
				sumOfICost += (token * 2);
				sumOfRef += token;
				continue;
				
			case "tomatoes":
				if(Temperatures.tomatoes.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.tomatoes.getTemp();
				}
				sumOfICost += (token * 1);
				sumOfRef += token;
				continue;
			
			case "celery":
				if(Temperatures.celery.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.celery.getTemp();
				}
				sumOfICost += (token * 2);
				sumOfRef += token;
				continue;
			
			case "ice cream":
				if(Temperatures.icecream.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.icecream.getTemp();
				}
				sumOfICost += (token * 8);
				sumOfRef += token;
				continue;
			
			case "lettuce":
				if(Temperatures.lettuce.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.lettuce.getTemp();
				}
				sumOfICost += (token * 1);
				sumOfRef += token;
				continue;
			
			case "grapes":
				if(Temperatures.grapes.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.grapes.getTemp();
				}
				sumOfICost += (token * 4);
				sumOfRef += token;
				continue;
			
			case "asparagus":
				if(Temperatures.asparagus.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.asparagus.getTemp();
				}
				sumOfICost += (token * 2);
				sumOfRef += token;
				continue;
			
			case "fish":
				if(Temperatures.fish.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.fish.getTemp();
				}
				sumOfICost += (token * 13);
				sumOfRef += token;
				continue;
			
			case "yoghurt":
				if(Temperatures.yoghurt.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.yoghurt.getTemp();
				}
				sumOfICost += (token * 10);
				sumOfRef += token;
				continue;
				
			case "chicken":
				if(Temperatures.chicken.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.chicken.getTemp();
				}
				sumOfICost += (token * 10);
				sumOfRef += token;
				continue;
			
			case "beef":
				if(Temperatures.beef.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.beef.getTemp();
				}
				sumOfICost += (token * 12);
				sumOfRef += token;
				continue;
			
			case "milk":
				if(Temperatures.milk.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.milk.getTemp();
				}
				sumOfICost += (token * 2);
				sumOfRef += token;
				continue;
			
			case "cheese":
				if(Temperatures.cheese.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.cheese.getTemp();
				}
				sumOfICost += (token * 4);
				sumOfRef += token;
				continue;
			
			case "ice":
				if(Temperatures.ice.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.ice.getTemp();
				}
				sumOfICost += (token * 2);
				sumOfRef += token;
				continue;
			
			case "frozen vegetable mix":
				if(Temperatures.frozenvegetablemix.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.frozenvegetablemix.getTemp();
				}
				sumOfICost += (token * 5);
				sumOfRef += token;
				continue;
				
			case "frozen meat":
				if(Temperatures.frozenmeat.getTemp() < requiredTemp) {
					requiredTemp = Temperatures.frozenmeat.getTemp();
				}
				sumOfICost += (token * 10);
				sumOfRef += token;
				continue;
			
			case "rice":
				sumOfICost += (token * 2);
				sumOfOrd += token;
				continue;
				
			case "beans":
				sumOfICost += (token * 4);
				sumOfOrd += token;
				continue;
				
			case "pasta":
				sumOfICost += (token * 3);
				sumOfOrd += token;
				continue;
				
			case "biscuits":
				sumOfICost += (token * 2);
				sumOfOrd += token;
				continue;
				
			case "nuts":
				sumOfICost += (token * 5);
				sumOfOrd += token;
				continue;
				
			case "chips":
				sumOfICost += (token * 2);
				sumOfOrd += token;
				continue;
				
			case "chocolate":
				sumOfICost += (token * 5);
				sumOfOrd += token;
				continue;
				
			case "bread":
				sumOfICost += (token * 2);
				sumOfOrd += token;
				continue;
			}//end of switch	
		}
		
		
        RefrigeratedTruck refTruck = new RefrigeratedTruck(requiredTemp);
        OrdinaryTruck ordTruck = new OrdinaryTruck(sumOfOrd);
        int refTruckAmount;
        int roundedTruckAmount;
        //int remainder;
        int remainderOrd;
        double unfilledTruckPriceOrd = 0.0;
        int truckIfRemainderRef;
        int finalRemainderRef;
        
        int ordTruckAmount;
        int truckIfRemainderOrd;
        int finalRemainderOrd;
        
        
        if (sumOfRef >= refTruck.getCapacity())
        {    
            if (sumOfRef % refTruck.getCapacity() == 0)
            {    
                refTruckAmount = sumOfRef/refTruck.getCapacity();
                finalRemainderRef = refTruckAmount;
            }
            else
            {
                //remainder = sumOfRef % refTruck.getCapacity();
                roundedTruckAmount = (int) Math.floor(sumOfRef/refTruck.getCapacity());
                truckIfRemainderRef = (roundedTruckAmount) + 1;
                finalRemainderRef = truckIfRemainderRef;
            }
            refTruck.setQuantity(finalRemainderRef);
        } 
        
        if (sumOfRef > 0 && sumOfRef < refTruck.getCapacity())  
        {    
            finalRemainderRef = 1;
            refTruck.setQuantity(finalRemainderRef);
        }
        
        /*
         * Ordinary truck calculations
         */
        
        if(sumOfOrd >= ordTruck.getCapacity()) 
        {
            if (sumOfOrd % ordTruck.getCapacity() == 0)
            {
                ordTruckAmount = sumOfOrd/ordTruck.getCapacity();
                finalRemainderOrd = ordTruckAmount;
            }
            else
            {
                remainderOrd = sumOfOrd % ordTruck.getCapacity();
                unfilledTruckPriceOrd = ordTruck.calculatingCostO(remainderOrd);
                
                roundedTruckAmount = (int) Math.floor(sumOfOrd/refTruck.getCapacity());
                
                truckIfRemainderOrd = (roundedTruckAmount);
                finalRemainderOrd = truckIfRemainderOrd;
            }
            ordTruck.setTruckQuantity(finalRemainderOrd);
        }
        
        if (sumOfOrd > 0 && sumOfRef < ordTruck.getCapacity())
        {
            
//            finalRemainderOrd = 1;
//            ordTruck.setTruckQuantity(finalRemainderOrd);
        }
        
        double costOfRefTruck;
        double costOfOrdTruck;
        double costOfTotalTrucks;
        double costOfAllItems;

        
        costOfRefTruck = refTruck.calculatingCostR(requiredTemp) * refTruck.quantity();
        costOfOrdTruck = ordTruck.calculatingCostO(sumOfOrd) * ordTruck.getTruckQuantity() + unfilledTruckPriceOrd;
        costOfAllItems = sumOfICost;
        costOfTotalTrucks = costOfRefTruck + costOfOrdTruck;
        
        totalCost = costOfAllItems + costOfTotalTrucks;
	}
	/*
	 * This updates inventory(increase) and capital(decrease).
	 * If there is insufficient Store money then the store cannot
	 * purchase items and update it's stock.
	 */
	public void updateStoreFromManifest() throws StockException {
		if(totalCost >= capital) {
			throw new StockException();
		} else if(totalCost < capital) {
			capital = capital - totalCost;
			textArea.setText(null);
			printTextField("Manifest Loaded" + "\n");
			textArea.append("Total Items Cost: " + String.format("%.2f", totalCost) + "\n");
			textArea.append("Capital: " + String.format("%.2f", capital) + "\n");
			
			for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
				Integer token = entry.getValue();
				String token1 = entry.getKey();
				
				if(reorderAmountMap.containsKey(token1)){
					if(token <= reorderPointMap.get(token1)) {
						int temp = token += reorderAmountMap.get(token1).intValue();
						inventoryMap.replace(token1, temp);
					}
					
				}
				textArea.append(entry + "," + "\n");
			}

			textArea.append("Restocked items." + "\n");
		}
	}
	
	/*
	 * This calculates the income when any of the sales log is loaded.
	 * The price is price is taken and multiplied to how much items are sold.
	 */
	public void calculateIncome(Map<String, Integer> map) {
		income = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();
			
			switch(token1) {
			
			case "mushrooms":
				income += (token * 4);
				continue;
				
			case "tomatoes":
				income += (token * 2);
				continue;	
			case "celery":
				income += (token * 3);
				continue;
			
			case "ice cream":
				income += (token * 14);
				continue;
			
			case "lettuce":
				income += (token * 2);
				continue;
			
			case "grapes":
				income += (token * 6);
				continue;
			
			case "asparagus":
				income += (token * 4);
				continue;
			
			case "fish":
				income += (token * 16);
				continue;
			
			case "yoghurt":
				income += (token * 12);
				continue;
				
			case "chicken":
				income += (token * 14);
				continue;
			
			case "beef":
				income += (token * 17);
				continue;
			
			case "milk":
				income += (token * 3);
				continue;
			
			case "cheese":
				income += (token * 7);
				continue;
			
			case "ice":
				income += (token * 5);
				continue;
			
			case "frozen vegetable mix":
				income += (token * 8);
				continue;
				
			case "frozen meat":
				income += (token * 14);
				continue;
			
			case "rice":
				income += (token * 3);
				continue;
				
			case "beans":
				income += (token * 6);
				continue;
				
			case "pasta":
				income += (token * 4);
				continue;
				
			case "biscuits":
				income += (token * 5);
				continue;
				
			case "nuts":
				income += (token * 9);
				continue;
				
			case "chips":
				income += (token * 4);
				continue;
				
			case "chocolate":
				income += (token * 8);
				continue;
				
			case "bread":
				income += (token * 3);
				continue;
			}//end of switch	
		}
	}

	/*
	 * This method updates the capital and inventory when sales_log_0 is loaded
	 */
	public void updateStoreFromLog0() throws IOException {
	    Path salesLog0path = Paths.get("sales_log_0.csv");
	    List<String> salesList0 = Files.readAllLines(salesLog0path);
	    
	    for(String line : salesList0)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int amountSold = Integer.parseInt(token[1]);  	
	    	sales0.put(name, amountSold);
	    	
	    }
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();			
			if(sales0.containsKey(token1)){
				int temp = token -= sales0.get(token1).intValue();
				inventoryMap.replace(token1, temp);
			}
		}
		calculateIncome(sales0);
		capital = capital + income;
		textArea.setText(null);
		printTextField("Sales Log 0 Loaded."+ "\n");
		textArea.append("Total Income: " + income + "\n");
		String capitalTemp = String.format("%.2f", capital);
		textArea.append("Capital: " + capitalTemp);
		textArea.append("\n" + "Map: " + inventoryMap.entrySet());//Items remaining
		
	}
	
	/*
	 * This method updates the capital and inventory when sales_log_1 is loaded
	 */
	public void updateStoreFromLog1() throws IOException {
	    Path salesLog1path = Paths.get("sales_log_1.csv");
	    List<String> salesList1 = Files.readAllLines(salesLog1path);
	    
	    for(String line : salesList1)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int amountSold = Integer.parseInt(token[1]);  	
	    	sales1.put(name, amountSold);
	    	
	    }
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();			
			if(sales1.containsKey(token1)){
				int temp = token -= sales1.get(token1).intValue();
				inventoryMap.replace(token1, temp);
			}
		}
		calculateIncome(sales1);
		capital = capital + income;
		textArea.setText(null);
		printTextField("Sales Log 1 Loaded."+ "\n");
		textArea.append("Total Income: " + income + "\n");
		String capitalTemp = String.format("%.2f", capital);
		textArea.append("Capital: " + capitalTemp);
		textArea.append("\n" + "Map: " + inventoryMap.entrySet());//Items remaining
		
	}
	
	
	/*
	 * This method updates the capital and inventory when sales_log_2 is loaded
	 */
	public void updateStoreFromLog2() throws IOException {
	    Path salesLog2path = Paths.get("sales_log_2.csv");
	    List<String> salesList2 = Files.readAllLines(salesLog2path);
	    
	    for(String line : salesList2)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int amountSold = Integer.parseInt(token[1]);  	
	    	sales2.put(name, amountSold);
	    	
	    }
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();			
			if(sales2.containsKey(token1)){
				int temp = token -= sales2.get(token1).intValue();
				inventoryMap.replace(token1, temp);
			}
		}
		calculateIncome(sales2);
		capital = capital + income;
		textArea.setText(null);
		printTextField("Sales Log 2 Loaded."+ "\n");
		textArea.append("Total Income: " + income + "\n");
		String capitalTemp = String.format("%.2f", capital);
		textArea.append("Capital: " + capitalTemp);
		textArea.append("\n" + "Map: " + inventoryMap.entrySet());//Items remaining
		
	}
	
	/*
	 * This method updates the capital and inventory when sales_log_3 is loaded
	 */
	public void updateStoreFromLog3() throws IOException {
	    Path salesLog3path = Paths.get("sales_log_3.csv");
	    List<String> salesList3 = Files.readAllLines(salesLog3path);
	    
	    for(String line : salesList3)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int amountSold = Integer.parseInt(token[1]);  	
	    	sales3.put(name, amountSold);
	    	
	    }
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();			
			if(sales3.containsKey(token1)){
				int temp = token -= sales3.get(token1).intValue();
				inventoryMap.replace(token1, temp);
			}
		}
		calculateIncome(sales3);
		capital = capital + income;
		textArea.setText(null);
		printTextField("Sales Log 3 Loaded."+ "\n");
		textArea.append("Total Income: " + income + "\n");
		String capitalTemp = String.format("%.2f", capital);
		textArea.append("Capital: " + capitalTemp);
		textArea.append("\n" + "Map: " + inventoryMap.entrySet());//Items remaining
		
	}
	
	/*
	 * This method updates the capital and inventory when sales_log_4 is loaded
	 */
	public void updateStoreFromLog4() throws IOException {
	    Path salesLog4path = Paths.get("sales_log_4.csv");
	    List<String> salesList4 = Files.readAllLines(salesLog4path);
	    
	    for(String line : salesList4)
	    {
	    	String[] token = line.split(splitter);
	    	String name = token[0];
	    	int amountSold = Integer.parseInt(token[1]);  	
	    	sales4.put(name, amountSold);
	    	
	    }
	    
		for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
			Integer token = entry.getValue();
			String token1 = entry.getKey();			
			if(sales4.containsKey(token1)){
				int temp = token -= sales4.get(token1).intValue();
				inventoryMap.replace(token1, temp);
			}
		}
		calculateIncome(sales4);
		capital = capital + income;
		textArea.setText(null);
		printTextField("Sales Log 4 Loaded."+ "\n");
		textArea.append("Total Income: " + income + "\n");
		String capitalTemp = String.format("%.2f", capital);
		textArea.append("Capital: " + capitalTemp);
		textArea.append("\n" + "Map: " + inventoryMap.entrySet());//Items remaining
		
	}

	/*
	 * This method changes the capital label each time it is affected by
	 * loading sales log and loading manifest.
	 */
	public void changeLblName(JLabel lbl) {
		double i = capital;
		String s = NumberFormat.getNumberInstance().format(i);
		lbl.setText("$"+ String.format("%,.2f", capital));
	}
	
	/*
	 * disables the generate manifest button accordingly when needed
	 */
	public void disableGenerateManifestBtn() {
		btnGenerateManifest.setEnabled(false);
	}
	
	/*
	 * enables the generate manifest button accordingly when needed
	 */
	public void enableGenerateManifestBtn() {
		btnGenerateManifest.setEnabled(true);
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * This is the action listener that executes methods when the button is clicked
	 * and/or if the condition is met.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		comboTrack(comboBox);
		update();
		
		if(src == btnGenerateManifest) {
			
			try {
				mapReorders();
				generateManifest();
				disableGenerateManifestBtn();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(value == "Item Properties" && src == btnLoad  ) {
			try {
				loadItems();
				
				mapInventory();
				initializeTable();
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		
		if(value == "Manifest" && src == btnLoad) {
			calculateCosts();
			try {
			updateStoreFromManifest();
			initializeTable();
			}catch(StockException e1) {
				printTextField("Insufficient capital funds: Items not purchased");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			changeLblName(capValueLabel);
		}
		
		if(value == "Sales Log 0" && src == btnLoad) {
			try {
				updateStoreFromLog0();
				initializeTable();
				changeLblName(capValueLabel);
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(value == "Sales Log 1" && src == btnLoad) {
			try {
				updateStoreFromLog1();
				initializeTable();
				changeLblName(capValueLabel);
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(value == "Sales Log 2" && src == btnLoad) {
			try {
				updateStoreFromLog2();
				initializeTable();
				changeLblName(capValueLabel);
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(value == "Sales Log 3" && src == btnLoad) {
			try {
				updateStoreFromLog3();
				initializeTable();
				changeLblName(capValueLabel);
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(value == "Sales Log 4" && src == btnLoad) {
			try {
				updateStoreFromLog4();
				initializeTable();
				changeLblName(capValueLabel);
				enableGenerateManifestBtn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
				
	}//End actionPerformed
}//End class
