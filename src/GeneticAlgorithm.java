import java.util.LinkedList;
import java.util.Random;

//Imports go here

public class GeneticAlgorithm {
    /********************************************************************************
     *This Class is used to store the functions used in the Genetic Algorithm.  	*
     ********************************************************************************/
  	private int populationSize;
  	private double rateMutation;
  	private double rateXO;
  	private int population[][];
 	 
  	public GeneticAlgorithm(int populationSize, double rateMutation, double rateXO) {
  		this.populationSize = populationSize;
  		this.rateMutation = rateMutation;
  		this.rateXO = rateXO;
	}
  	
  	//Initial Population
  	public int[][] InitPop(int tourSize){
  		
  		population = new int[populationSize][tourSize];
  		
  		LinkedList<Integer> list = new LinkedList<Integer>();
  		
  		
  		
  		Random r = new Random(); //Replace with something more efficient
  		int cityIndexes[] = new int[tourSize];
  		
  		//Manages Population index
  		for(int j = 0; j < populationSize; ++j){
  			
  			System.out.println("Population: " + j + "\n---------------------");
  			
  			//Populates List
  			for(int i = 0; i < tourSize; ++i){
  	  			list.add(i);
  	  		}
  			
  			//Generates Parents
  			for(int i = 0; i < tourSize; ++i){
  	  			int index = r.nextInt(list.size());
  	  	  		population[j][i] = list.remove(index);
  	  	  		System.out.print(population[j][i] + " -> ");
  	  		}
  			
  			
  			System.out.println("\n");
  		}
  		
 
  		
  		return population;
  	}
  	
  	//Evaluate Population
  	
  	
  	
  	
  	//Find Fitness
  	/********************************************************************************
  	 *Fitness will be determined by:                                            	*
  	 *If current distance > best previously found distance fitness = 0          	*
  	 *else (best previous distance - current distance)/total population fitness 	*
  	 *OR                                                                        	*
  	 *let optimal distance be defined as defined as 1/cities in tour.           	*
  	 * Fitness = tour distance/optimal distance                                 	*
  	 ********************************************************************************/
  		
  	//Perform Crossover
  	
  	//Perform Mutation
  	
  	//Check Termination Conditions
   	 
}
