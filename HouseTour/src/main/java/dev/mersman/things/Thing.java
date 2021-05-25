package dev.mersman.things;

import java.util.ArrayList;


public class Thing {

	public String name;
	public String description;
	public ArrayList<Thing> inventory;
	
	public Thing(String name, String desc) {
		this.name= name;
		this.description = desc;
	}
	
	public Thing(String name, String desc, ArrayList<Thing> inv) {
		this.name = name;
		this.description = desc;
		this.inventory = inv;
	}
	
	public void Examine() {
		System.out.println("You examine " + name);
		System.out.println(description);
		if (inventory != null) {
			System.out.println("The " + name + " holds: ");
			for (Thing item : inventory) {
				System.out.println(item.name);
			}
		}
	}
	
}
