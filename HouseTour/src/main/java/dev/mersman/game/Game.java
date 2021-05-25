package dev.mersman.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import dev.mersman.things.Person;
import dev.mersman.things.Room;
import dev.mersman.things.Thing;



public class Game {
	
	private static Person player = new Person();
	private static ArrayList<Room> gameMap = new ArrayList<Room>();
	private static Thing key = new Thing("Key", "The spare key to your bedroom");
	private static ArrayList<Thing> lrThingList;
	private static ArrayList<Thing> kitchThingList;
	private static ArrayList<Thing> diningThingList;
	private static ArrayList<Thing> bathThingList;	
	private static final ArrayList<String> commands = new ArrayList<>(Arrays.asList("go", "take", "examine"));
	private static ArrayList<String> thingNames = new ArrayList<>(Arrays.asList("north", "south", "east", "west","room","key"));
		
	
	
	public static void main(String[] args) {
		
		
		Scanner userInput = new Scanner(System.in);
		String in;
		createThingLists();
		createMap();
		player.location = gameMap.get(0);
		
		//Starting text
		System.out.println("Welcome home! You've locked yourself out of your room! You need to find your spare key.");
		System.out.println();
		System.out.println("Type 'go' and a direction 'north, south, east, west' to move to another room.");
		System.out.println("Type 'examine' and the name of an item to inspect something.");
		System.out.println("Type take and the name of an item to put something in your inventory");
		System.out.println();
		System.out.println("Press Enter to begin...");
		userInput.nextLine();
		
		player.location.Examine();
		
		//game loop
		do {
			
			in = userInput.nextLine().toLowerCase();
			if (!in.equals("quit")) {
				parseInput(in);
			}
		
		}while (!in.equals("quit"));
		
		userInput.close();
		
	}
	
	
	private static void createThingLists() {
		ArrayList<Thing> list;
		
		//living room things create
		Thing tv = new Thing("Television", "A flat screen television.");
		Thing couch = new Thing("Couch", "A black faux leather couch, it's sticky from the heat and humidity.");
		Thing recliner = new Thing("Recliner", "A comfortable looking recliner.");
		Thing fpic = new Thing("Family Picture","An old family picture, you were only 10.");
		Thing therm = new Thing("Thermostat", "The thermostat says it's 83 degrees, it's turned off... you see a note on the"
				+ " wall next to it that says: DON'T TOUCH!");
		Thing cTable = new Thing("Coffee Table","A coffee table in front of the couch.");
		//add to list
		lrThingList = new ArrayList<Thing>(Arrays.asList(tv,couch,recliner,fpic,therm,cTable));
		addNames(thingNames,lrThingList);
		
		//dining room things
		Thing dTable = new Thing("Dining Table", "A rectangular table surround by 8 chairs");
		
		list = new ArrayList<Thing>(Arrays.asList(key));
		Thing book1 = new Thing("Moby Dick", "The tale of a sea captain and his mortal enemy. It looks like there is something between the pages", list);
		Thing book2 = new Thing("The Name of The Wind", "Believe it or not, its name is Jim");
		Thing book3 = new Thing("The Hobbit", "A Classic");
		list = new ArrayList<Thing>(Arrays.asList(book1,book2,book3));
		Thing bookcase = new Thing("Bookcase","A solid bookcase made of oak", list);
		diningThingList = new ArrayList<Thing>(Arrays.asList(dTable,bookcase));
		addNames(thingNames,diningThingList);
		
		//Kitchen things
		Thing cabinets = new Thing("Cabinets", "Various cabinets full of food and kitchen items");
		Thing stove = new Thing("Stove", "Just a normal stove, maybe you should heat up  the left over pizza");
		Thing fridge =  new Thing("Refrigerator", "A white fridge covered in alphabet magnets. Someone spelled 'Butts' with the magnets...it was me :)");
		Thing sink = new Thing("Kitchen Sink", "A stainless steel sink with two basins");
		kitchThingList = new ArrayList<Thing>(Arrays.asList(cabinets,stove,fridge, sink));
		addNames(thingNames,kitchThingList);
		
		//Bathroom Things
		
		Thing medCab = new Thing("Medicine Cabinet", "A medicine cabinet full of medicine and toiletries, the door is also a mirror.");
		Thing toilet = new Thing("Toilet", "A typical toilet, be surue to flush!");
		Thing bSink = new Thing("Bathroom Sink", "A small porcelain sink. you see a tiny snake carved into one of the levers");
		Thing shower = new Thing("Shower", "A shower with no bathtub.");
		bathThingList = new ArrayList<Thing>(Arrays.asList(medCab,toilet,bSink, shower));
		addNames(thingNames,bathThingList);
		
		
		
	}
	
	private static void createMap() {
		
		gameMap.add(new Room("The Living Room", "The floor is covered in yellow shag carpet...Groovy!", lrThingList, 2,-1,1,4));
		gameMap.add(new Room("The Dining Room", "A small dining room, the walls are painted lime green.", diningThingList, 3,-1,-1,0));
		gameMap.add(new Room("The Kitchen", "A kitchen with an island in the middle. There is a window above the sink that brightens the entire room.",
				kitchThingList, -1,0,3,-1));
		gameMap.add(new Room("The Bathroom", "A bathroom with white linoleum florring.", bathThingList, -1,1,-1,2));
		gameMap.add(new Room("The Stairway", "A carpeted stairway", 5,-1,0,-1));
		gameMap.add(new Room("The Upstairs Landing", "A small landing with doors on either side. The wall with out a door has a samll window.", -1,4,6,7));
		gameMap.add(new Room("Your Bedroom", "You made it, Congratualtions!!! It's a mess in here....", -1,-1,-1,5, false));
		gameMap.add(new Room("Your Parent's Bedroom", "You're not allowed in here. You should leave. Now.", -1,-1,5,-1));
		
	}
	
	public static void parseInput(String input) {
		
		
		if (input.equals("")){
			System.out.println("You must enter a command");
			return;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(input, " ");
		ArrayList<String> commandString = new ArrayList<String>();
		String sToken;
		
		while(tokenizer.hasMoreTokens()) {
			sToken = tokenizer.nextToken();
			commandString.add(sToken);
		}
		
		//if (commandString.size() != 2) {
		//	System.out.println("2 word commands only please. You input: " + commandString.size() + " word(s)");
		//	return;
		//}
		
		String cmd = commandString.get(0);;
		String cmd2 = commandString.get(1);
		
		for (int i =2; i < commandString.size(); i++) {
			cmd2 = cmd2 +" "+ commandString.get(i);
		}
		
		if (!commands.contains(cmd)) {
			System.out.println("The first word of the command you entered isn't valid.");
			return;
		}
		else if(!thingNames.contains(cmd2)) {
			System.out.println("The second word of the command you entered isn't valid.");			
			return;
		}
		else {
			//System.out.println("That is a valid command");
			//switch statement
			switch (cmd) {
				case "go":{
					
					int tempLocation = player.move(cmd2);
					if (tempLocation == -1) {
						System.out.println("There is nothing in that direction.");
						break;
					}
					else if((tempLocation == 6)&&(player.inventory == null)){
						System.out.println("This is your room. The door is locked, you need to find the key.");
						break;
					}
					else {
						player.location = gameMap.get(tempLocation);
						player.location.Examine();
						break;
					}
					
				}
				case "take": {
					//take(cmd2);
					if (!cmd2.equals("key")) {
						System.out.println("You can't take that.");
						break;
					}
					else if(!player.location.name.equals("The Dining Room")) {
						break;
					}
					else {
						player.inventory = new ArrayList<Thing>();
						player.inventory.add(key);
						(diningThingList.get(1)).inventory.get(0).inventory.remove(0);
						System.out.println("Key has been added to your inventory");
					}
				}
				case "examine":{
					//only works for one word names...
					if (cmd2.equals("room")) {
						player.location.Examine();
						break;
					}
					for (Thing t : player.location.inventory) {
						if (t.name.toLowerCase().equals(cmd2)) {
							t.Examine();
							break;
						}
						else if (t.inventory != null) {
							for (Thing s : t.inventory) {
								if (s.name.toLowerCase().equals(cmd2)) {
									s.Examine();
									break;
								}
							}
						}
						
					}
				} 
				default :{break;}
					
			}
		}
		
		
		
	}
	
	public static void addNames(ArrayList<String> nameList, ArrayList<Thing> thingList) {
		
		for (Thing t : thingList) {
			nameList.add(t.name.toLowerCase());
			if(t.inventory != null) {
				for (Thing s : t.inventory) {
					nameList.add(s.name.toLowerCase());
				}
			}
			
		}
		
	}
}


