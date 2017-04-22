import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		int maxGeneration = 100;
		int populationSize = 100;
		double rateMut=0.1;
		double rateXO = 0.5;
		int generation=0;
		Tour solution;
		
		//create genetic algorithm object
		GeneticAlgorithm geneAl = new GeneticAlgorithm(populationSize,rateMut,rateXO, Utils.getCities(new File("res/dj38.txt")));
		
		//Initalize Population
		int population[][] = geneAl.InitPop(cities.length);
		
		//check terminiation conditions
		while(generation<maxGeneration){
		
			//perform crosover
			geneAl.PMX(cities.length);
		
			//Preforms Mutation
			//System.out.println(Utils.mutate(.1f, population[9]));
			//evaluate population
			//System.out.println(geneAl.getElite().toString());
			generation++;
		}//end generation while
	}
	
}
