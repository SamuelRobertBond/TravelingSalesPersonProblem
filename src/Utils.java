import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Utils {
	
	public static City[] getCities(File file){
		
		Scanner in = null;
		
		try {
			in = new Scanner(file); //Inits the Scanner with the file arg
		} catch (FileNotFoundException e) {
			System.out.print("Util : getCities: File Not Found");
			e.printStackTrace();
		}
		
		boolean loop = true;
		int count = 0;
		
		while(loop){
			String cur = in.next();
			
			if(cur.contains("DIMENSION")){
				while(count == 0){
					if(in.hasNextInt()){
						count = in.nextInt();
					}else{
						in.next();
					}
				}
			}else if(cur.equals("NODE_COORD_SECTION")){
				loop = false;
			}
		}
		
		System.out.println();
		
		City[] cities = new City[count];
		
		
		for(int i = 0; i < count; ++i){
			
			//String input = in.nextLine();
			//System.out.println(input);
			//String[] parsed = input.split("\\s+");//
			int waste = in.nextInt();
			double[] parsed = {in.nextDouble(), in.nextDouble()};
			
			//in.nextInt(); //Gets rid of the index in the text file
			//float x = in.nextFloat();
			//float y = in.nextFloat();
			//System.out.println(x+" "+y);
			
			cities[i] = new City(parsed[0] , parsed[1]);
		}
		
		
		return cities;
	}
	
	
	public static int[] mutate(double rate, int cities[]){
		
		Random r = new Random();
		
		boolean swap = false;
		int swapIndex = 0;
		
		//Gets a number between 0 and 1
		//Only needs to generate 1 random number
		//This will serve as the basis for future mutation
		double random = Math.abs(r.nextGaussian());
		
		for(int i = 0; i < cities.length; ++i){
			
			//Checks if the random number is less than the mutation rate
			if(random < rate){
				//If a number has already been selected to be mutated, the two indicies are swapped
				if(swap){
					//If there are 2 indices that have been selected to be mutates, they are swapped
					int tmp = cities[i];
					cities[i] = cities[swapIndex];
					cities[swapIndex] = tmp;
					swap = false;
				}else{
					//First index is selected to be swapped
					swapIndex = i;
					swap = true;
				}
			}
			
			random += rate; //Adds the rate to random
			
			if(random > 1){
				random -= 1;
			}
			
		}
		
		for(int i = 0; i < cities.length; ++i){
			System.out.print(cities[i] + " -> ");
		}
		
		return cities;
	}
}
