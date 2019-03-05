package testingPack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Items.DryItem;
import Items.RefrigeratedItem;
import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Temperatures;
import store.Stock;
import store.Store;

/**
 * @author John Layson
 * @author Nathan Thai
 *
 */
public class TestDrivenClass {

	public static void main(String[] args) throws IOException {
		
		
		//--------------BLOCK OF CODE 1 LOAD ITEMS------------------------
	    Path path = Paths.get("item_properties.csv");
	    final String splitter = ",";
	    		
//	    try {
	      List<String> lines = Files.readAllLines(path);
	      List<RefrigeratedItem> RItems = new ArrayList<RefrigeratedItem>();
	      List<DryItem> DItems = new ArrayList<DryItem>();	      
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
		  //------------- END OF CODE 1 ------------------
		  //------------- BLOCK CODE 2: Give each Item 0 Quantity -------
		    Set<Stock> inventory = new LinkedHashSet<Stock>();
		    
		    for(int i = 0; i < 8; i++) {
		    	inventory.add(new Stock(DItems.get(i).itemName(), 0));
		    }
		    for(int i = 0; i < 16; i++) {
		    	inventory.add(new Stock(RItems.get(i).itemName(), 0));
		    }
		    
		  //-------------END BLOCK CODE 2: Give each Item 0 Quantity -------
		    
		    
		    
		    //---------------BLOCK CODE 3 MAP THE INVENTORY---------------
		    Iterator iterator = inventory.iterator();
		    Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
		    
		    while(iterator.hasNext())
		    {	
		    		Stock object = (Stock) iterator.next();
		    		int currentQuantity = object.getQuantity();
		    		inventoryMap.put(object.getName(), currentQuantity);
		    }
		    
		  //---------------END BLOCK CODE 3---------------
		   
		    
		  //---------------BLOCK CODE 4 MAP THE ITEM FOR REORDER STOCK---------------
		    Map<String, Integer> reorderPointMap = new HashMap<String, Integer>();
		    Map<String, Integer> reorderAmountMap = new HashMap<String, Integer>();
		    
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
		  //---------------END BLOCK CODE 4 MAP THE ITEM FOR REORDER STOCK---------------
		    
		    
		    // --------------------BLOCK CODE 5: Generate Manifest------------------
		    
		    /*
		     * NOTE: You might have to change the following directory to downloaded project path.
		     */
		    
		    Map<String, Integer> reStock = new HashMap<String, Integer>();
		    //File file = new File("C:/Users/Nathan/Documents/inventory-management-application-john-layson-nathan-thai/project/Manifest1.csv");
			File file = new File("D:/Work/git/inventory-management-application-john-layson-nathan-thai/project/Manifest1.csv");
			if(file.exists()) {
				file.delete();
			} 
		    
			for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
				Integer token = entry.getValue();
				String token1 = entry.getKey();
				
				if(reorderPointMap.containsKey(token1) /*== currentObject/*object2.getName().toString() && currentQuantity <= Integer.parseInt(token[3])*/) 
	    		{
					
					
	    			if (token <= reorderPointMap.get(token1).intValue())
	    			{
	    				
	    				
	    				Manifest manifest = new Manifest(token1, reorderAmountMap.get(token1).intValue());//Manifest Generator
		    			manifest.generateManifest(token1, reorderAmountMap.get(token1).intValue());//Manifest Generator
		    			
		    			
		    			
		    			/*
		    			 * Generate reStock HashMap
		    			 */
		    			reStock.put(token1, reorderAmountMap.get(token1).intValue());
		    			
		    			
	    			}
		    	}
			}
			
			// --------------------END BLOCK CODE 5------------------------------
			
		    //-------------------- BLOCK CODE 6 CALCULATE COSTS-----------------------

			
//			System.out.print(reStock.keySet() + "\n");
//			System.out.print(reStock.values());
		    
			
			int sumOfRef = 0;
			int sumOfOrd = 0;
			int sumOfICost = 0;
			double requiredTemp = 20;
			
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
			
//			System.out.println(sumOfRef);
//            System.out.println(sumOfOrd);
            
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
                
//                finalRemainderOrd = 1;
//                ordTruck.setTruckQuantity(finalRemainderOrd);
            }
//            System.out.println(refTruck.quantity());
//            System.out.println(ordTruck.getTruckQuantity());
            double costOfRefTruck;
            double costOfOrdTruck;
            double costOfTotalTrucks;
            double costOfAllItems;
            double totalCost;
            
            costOfRefTruck = refTruck.calculatingCostR(requiredTemp) * refTruck.quantity();
            costOfOrdTruck = ordTruck.calculatingCostO(sumOfOrd) * ordTruck.getTruckQuantity() + unfilledTruckPriceOrd;
            costOfAllItems = sumOfICost;
            costOfTotalTrucks = costOfRefTruck + costOfOrdTruck;
            
            totalCost = costOfAllItems + costOfTotalTrucks;
//            System.out.println(costOfRefTruck);
//            System.out.println(costOfOrdTruck);
//            System.out.println(costOfTotalTrucks);
//            System.out.println(costOfAllItems);
//            System.out.println(totalCost);            
            
            //-------------------- END BLOCK CODE 6------------------
            
			// -------------------- BLOCK CODE 7 UPDATE CAPITAL------------------
			Store SuperMart = Store.getInstance();
			double capital = SuperMart.getCapital();
			capital = capital - totalCost;
			String capitalTemp = String.format("%.2f", capital);
			System.out.println(capitalTemp);
			// -------------------- END BLOCK CODE 7-----------------------------
			
			// -------------------- BLOCK CODE 8 RESTOCK (FROM MANIFEST)------------------
//			System.out.println(inventoryMap.entrySet());
//			System.out.println(inventoryMap.size());
			for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
				Integer token = entry.getValue();
				String token1 = entry.getKey();
				
				if(reorderAmountMap.containsKey(token1)){
					if(token <= reorderPointMap.get(token1)) {
						int temp = token += reorderAmountMap.get(token1).intValue();
						inventoryMap.replace(token1, temp);
					}
					
				}
			}
			System.out.println(inventoryMap.entrySet());
			System.out.println(inventoryMap.size());			
			// -------------------- BLOCK CODE 8 RESTOCK (FROM MANIFEST)------------------
			
			// -------------------- BLOCK CODE 9 LOAD SALES LOG(Create map) & UPDATE STORE------------------
			
		    Path salesLog0path = Paths.get("sales_log_0.csv");
		    List<String> salesList0 = Files.readAllLines(salesLog0path);
		    
		    Map<String, Integer> sales0 = new HashMap<String, Integer>();
		    
		    for(String line : salesList0)
		    {
		    	String[] token = line.split(splitter);
		    	String name = token[0];
		    	int amountSold = Integer.parseInt(token[1]);
		    	
		    	sales0.put(name, amountSold);
		    	
		    }
		    
		    System.out.println(sales0.entrySet());
			System.out.println(sales0.size());
		    
			//Update Inventory
			for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
				Integer token = entry.getValue();
				String token1 = entry.getKey();
				
				if(sales0.containsKey(token1)){
					int temp = token -= sales0.get(token1).intValue();
					inventoryMap.replace(token1, temp);
				}
			}
			System.out.println(inventoryMap.entrySet());
			System.out.println(inventoryMap.size());
			
			int income = 0;
			for (Map.Entry<String, Integer> entry : sales0.entrySet()) {
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
			
			System.out.println(income);
			capital = capital + income;
			capitalTemp = String.format("%.2f", capital);
			System.out.println(capitalTemp);
				    
		    // -------------------- END BLOCK CODE 9 LOAD SALES LOG------------------
			
			
	
	
	
	
	}
	
	
}
