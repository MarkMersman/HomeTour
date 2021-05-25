package dev.mersman.things;



public class Person extends Thing{

	public Room location;
	
	public Person() {
		super("The Player","This is you!");
		
	}
	
	public int move(String direction) {
		switch(direction) {
			case "north":{ return this.location.north;}
			case "south" :{return this.location.south;}
			case "west":{return this.location.west;}
			case "east": {return this.location.east;}
			default: {return -1;}
		}
	}
	
}
