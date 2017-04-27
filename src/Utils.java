import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class Utils {
	static Comparator<Tour> sortByFitness;
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
		
		
		City[] cities = new City[count];
		
		for(int i = 0; i < count; ++i){
			
			in.nextInt(); //Waste
			double[] parsed = {in.nextDouble(), in.nextDouble()};
			
			cities[i] = new City(parsed[0] , parsed[1]);
		}
		
		
		return cities;
	}
	
	static {
        sortByFitness = new Comparator<Tour>(){
            @Override
            public int compare(Tour t1, Tour t2){
                return Double.compare(t1.getTourDistance(), t2.getTourDistance());
            }
        };
	}//end static
}
