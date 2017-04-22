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
  	public void PMX(int tourSize){
  		Random r = new Random(); //Replace with something more efficient
  		for(int i=0;i<populationSize/2;i+=2){
  			int XOIndex[] =new int[2]; 
  			//generate random indexes for crossover
  			XOIndex[0]=	r.nextInt((int)(tourSize*rateXO)); //generate random index for start
  			XOIndex[1]= r.nextInt((int)(tourSize*rateXO)); //generate random for end
  			XOIndex[1]+=XOIndex[0];//add start index to end index to ensure
  			//if end index is greater than tourSize
  			if (XOIndex[1]>tourSize){
  				XOIndex[0]-=(XOIndex[1]-tourSize);
  				XOIndex[1]-=(XOIndex[1]-tourSize);//set end index
  			}
  			//System.out.println("Parent1: " + population[i]+"\nParent2:"+population[i+1]);
  			// do crossover
  			for(int s = XOIndex[0];s<XOIndex[1];s++ ){
  				int p1Holder = population[i][s];
  				int p2Holder = population[i+1][s];
  				for(int find=0;find<tourSize;find++){
  					if(population[i][find] == p1Holder && find !=s){
  					population[i][s] = population[i][find];
  					population[i][find]=p1Holder;
  						  }
  					
  					if(population[i+1][find] == p2Holder && find !=s){
  	  					population[i+1][s] = population[i+1][find];
  	  					population[i+1][find]=p2Holder;
  	  					  }
  					}
  				}
  			}
  			
  		
  		//if odd amount of parents use previous parent
  		if(populationSize%2>0){
  			int XOIndex[] =new int[2]; 
  			//generate random indexes for crossover
  			XOIndex[0]=	r.nextInt((int)(tourSize*rateXO)); //generate random index for start
  			XOIndex[1]= r.nextInt((int)(tourSize*rateXO)); //generate random for end
  			XOIndex[1]+=XOIndex[0];//add start index to end index to ensure
  			//if end index is greater than tourSize
  			if (XOIndex[1]>tourSize){
  				XOIndex[0]-=(XOIndex[1]-tourSize);
  				XOIndex[1]-=(XOIndex[1]-tourSize);//set end index
  			}
  			//System.out.println("Parent1: " + population[i]+"\nParent2:"+population[i+1]);
  			// do crossover
  			for(int s = XOIndex[0];s<XOIndex[1];s++ ){
  				int p1Holder = population[populationSize-1][s];
  				int p2Holder = population[populationSize-2][s];
  				// do crossover 
  				for(int find=0;populationSize-1<tourSize;find++){
  					
  					if(population[populationSize-1][find] == p1Holder && find !=s){
  					population[populationSize-1][s] = population[populationSize-1][find];
  					population[populationSize-1][find]=p1Holder;
  						  }
  					
  					if(population[populationSize-2][find] == p2Holder && find !=s){
  	  					population[populationSize-2][s] = population[populationSize-2][find];
  	  					population[populationSize-2][find]=p2Holder;
  	  					  }
  					}
  				}// end do crossover
  			}// end if odd xo
  			
  	}// end PMX
  	
  	//Perform Mutation
  	
  	//Check Termination Conditions
   	 
}
