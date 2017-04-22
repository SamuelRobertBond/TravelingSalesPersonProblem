import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		int maxGeneration = 50;
		int populationSize = 10;
		double rateMut=0.1;
		double rateXO = 0.5;
		for(City city : cities){
			System.out.println("City Coordinates\n  X:" + city.x + "\n  Y: " + city.y + "\n" );
		}
		
		GeneticAlgorithm geneAl = new GeneticAlgorithm(populationSize,rateMut,rateXO);
		
		int population[][] = geneAl.InitPop(cities.length);
		
		for(int i = 0; i < populationSize; ++i){
			Tour tour = new Tour(cities, population[i]);
		}
		
		//Preforms Mutation
		System.out.println(Utils.mutate(.1f, population[9]));
		
		/*for(int i = 0; i < populationSize; ++i){
			System.out.println(population[i][]);
			Tour initTour = new Tour(cities, population[i]);
		}*/
		
	}
	
}
