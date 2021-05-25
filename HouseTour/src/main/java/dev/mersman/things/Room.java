package dev.mersman.things;

import java.util.ArrayList;

public class Room extends Thing{

	public int north,south,west,east; 
	boolean canEnter = true;
	
	
	public Room(String name, String desc, ArrayList<Thing> items, int n, int s, int w, int e) {
		super(name,desc,items);
		this.north = n;
		this.south = s;
		this.west = w;
		this.east = e;
	}
	
	public Room(String name, String desc, int n, int s, int w, int e) {
		super(name,desc);
		this.north = n;
		this.south = s;
		this.west = w;
		this.east = e;
	}
	
	public Room(String name, String desc, int n, int s, int w, int e, boolean enterable) {
		super(name,desc);
		this.north = n;
		this.south = s;
		this.west = w;
		this.east = e;
		this.canEnter = enterable;
	}
	
	public void Examine() {
		System.out.println("You look around the room. You see: ");
		System.out.println(name);
		System.out.println(description);
		
		if (inventory == null) {
			//System.out.println("nothing");
			return;
		}
		System.out.println("In the room you see: ");
		for(Thing i : inventory) {
			System.out.println(i.name);
		}
	}
	
	
}
