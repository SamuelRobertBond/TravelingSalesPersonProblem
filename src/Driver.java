import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		
		for(City city : cities){
			System.out.println("City Coordinates\n  X:" + city.x + "\n  Y: " + city.y + "\n" );
		}
		
	}
	
}
