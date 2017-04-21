
//Imports go here

public class GeneticAlgorithm {
    /********************************************************************************
     *This Class is used to store the functions used in the Genetic Algorithm.  	*
     ********************************************************************************/
  	private int populationSize;
  	private double rateMutation;
  	private double rateXO;
 	 
  	public GeneticAlgorithm(int populationSize, double rateMutation, double rateXO) {
  		this.populationSize = populationSize;
  		this.rateMutation = rateMutation;
  		this.rateXO = rateXO;
	}
  	
  	//Initial Population
  	
  	//Evaluate Pupulation
  	
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
