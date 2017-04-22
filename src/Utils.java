import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
	
}
