import java.util.Arrays;
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
  	private City cities[];
  	Tour pop[];
  	Tour elite;//[];
  	LinkedList<Tour> elitist = new LinkedList<Tour>();
 	 
  	public GeneticAlgorithm(int populationSize, double rateMutation, double rateXO, City cities[]) {
  		this.populationSize = populationSize;
  		this.rateMutation = rateMutation;
  		this.rateXO = rateXO;
  		this.cities = cities;
  		//this.elite = new Tour[populationSize/10];
	}
  	
  	//Initial Population
  	public int[][] InitPop(int tourSize){
  		pop = new Tour[populationSize];
  		population = new int[populationSize][tourSize];
  		
  		LinkedList<Integer> list = new LinkedList<Integer>();
  		
  		Random r = new Random(); //Replace with something more efficient
  		
  		//Manages Population index
  		for(int j = 0; j < populationSize; ++j){
  			
  			//System.out.println("Population: " + j + "\n---------------------");
  			
  			//Populates List
  			for(int i = 0; i < tourSize; ++i){
  	  			list.add(i);
  	  		}
  			
  			//Generates Parents
  			for(int i = 0; i < tourSize; ++i){
  	  			int index = r.nextInt(list.size());
  	  	  		population[j][i] = list.remove(index);
  	  	  	//	System.out.print(population[j][i] + " -> ");
  	  		}
  			Tour tour1 =new Tour(cities,population[j]); 
  			pop[j]= tour1;
  			addElite(tour1);
  			
  			//System.out.println("\n");
  		}
  		
 
  		
  		return population;
  	}
  	
  	//Evaluate Population
  	public void selectParents(){
		
		Tour parents[] = new Tour[populationSize];
		
		double totalFitness = 0;		
		double thresholds[] = new double[pop.length];
		
		for(int i = 0; i < pop.length; ++i){
			totalFitness += pop[i].getFitness();
		}
		
		thresholds[0] = pop[0].getFitness();
		
		for(int i = 1; i < pop.length; ++i){
			thresholds[i] = pop[i].getFitness() + thresholds[i - 1];
		}
		
		
		Random r = new Random();
		
		double increment = totalFitness / populationSize;
		double spin = Math.abs(r.nextGaussian()) * totalFitness;
		
		for(int i = 0; i < parents.length; ++i){	
			
			spin += increment;
			
			for(int j = 0; j < thresholds.length; ++j){
				if(spin <= thresholds[j] || spin > thresholds[thresholds.length - 1]){
					parents[i] = pop[j];
					j = thresholds.length; //Breaks the inner loop
				}
			}
			
			if(spin > totalFitness){
				spin -= totalFitness;
			}
			
		}
		
		pop = parents;
		
	}
  	
  	
  	
  	//Find Fitness
  	/********************************************************************************
  	 *Fitness will be determined by:                                            	*
  	 *If current distance > best previously found distance fitness = 0          	*
  	 *else (best previous distance - current distance)/total population fitness 	*
  	 *OR                                                                        	*
  	 *let optimal distance be defined as defined as 1/cities in tour.           	*
  	 * Fitness = tour distance/optimal distance                                 	*
  	 ********************************************************************************/
  	public void CrossOver(boolean algo){
  		
  		if(algo){
  			OX(cities.length);
  		}else{
  			PMX(cities.length);
  		}
  	}
  	
  	public void OX(int tourSize){
  			
  		int[] parent1;
  		int[] parent2;
  		Random r = new Random(); //Replace with something more efficient
  		
  		LinkedList<Integer> list = new LinkedList<Integer>();
  		
  		for (int i = 0; i<populationSize;i++){
  			list.add(i);
  		}
  		
  		for(int h =0; h < populationSize/2; h +=2){
  			
  			int rList = r.nextInt(list.size());
  			parent1 = pop[list.remove(rList)].getTourInt();
  			int rList2 =r.nextInt(list.size());
  			parent2 = pop[list.remove(rList2)].getTourInt();
  	  		
  			
  			//Replace with something more efficient
  	  		int XOIndex[] =new int[2]; //generate random indexes for crossover
  	  		
  			XOIndex[0]=	r.nextInt((int)(tourSize*rateXO)); //generate random index for start
  			XOIndex[1]= r.nextInt((int)(tourSize*rateXO)); //generate random for end
  			XOIndex[1]+=XOIndex[0];//add start index to end index to ensure
  			
  			//if end index is greater than tourSize
  			if (XOIndex[1] > tourSize){
  				XOIndex[0] -= (XOIndex[1]-tourSize);
  				XOIndex[1] -= (XOIndex[1]-tourSize);//set end index
  			}
  			
  	  		int temp1[] = new int[(parent1.length-(XOIndex[1]-XOIndex[0]))];
  	  		int t1i=0;
  	  		
  	  		int temp2[] = new int[parent1.length-(XOIndex[1]-XOIndex[0])];
  	  		int t2i=0;
  	  		
  	  		// find values not in XO segment
  	  		for(int i = 0;i<parent1.length;i++){
  	  			
  	  			boolean p1=false;
  	  			boolean p2=false;
  	  			
  	  			for(int j =XOIndex[0]; j<XOIndex[1];j++){
  	  				if(parent1[i]==parent2[j]){
  	  					p1=true;
  	  				}
  	  				if(parent2[i]==parent1[j]){
	  					p2=true;
	  				}
  	  			}//end for loop for looping through XO region
  	  			
  	  			if (!p1){
  	  				temp1[t1i]=parent1[i];
  	  				t1i++;
  	  			}
  	  			if (!p2){
	  				temp2[t2i]=parent2[i];
	  				t2i++;
	  			}
  	  			
  	  		}//end for loop for looping through parents
  	  		
  	  		//copy to parent objects
  	  			for(int i =0;i<parent1.length;i++){
  	  				if(i < XOIndex[0]){
  	  					parent1[i]=temp2[(temp2.length-XOIndex[0])+i];
  	  					parent2[i]=temp1[(temp2.length-XOIndex[0])+i];  
  	  				}
  	  				
  	  				if(i >= XOIndex[1]){
  	  					parent1[i]=temp2[i-XOIndex[1]];
  	  					parent2[i]=temp1[i-XOIndex[1]];
  	  				}
  	  			
  	  				Tour tour1= new Tour(cities,parent1);//.setTourInt(parent1);
  	  				pop[rList] = tour1; 
  	  				
  	  				Tour tour2= new Tour(cities,parent2);
  	  				pop[rList2] = tour2;
  	  			}
  	  		}// end population loop
  		}
  		
  	//Perform Crossover
  	public void PMX(int tourSize){
  		
  		int[] parent1;
  		int[] parent2;
  		Random r = new Random(); //Replace with something more efficient
  		
  		LinkedList<Integer> list = new LinkedList<Integer>();
  		
  		for (int i = 0; i<populationSize;i++){
  			list.add(i);
  		}
  		
  		for(int i=0;i<populationSize/2;i +=2){
  			
  			int rList = r.nextInt(list.size());
  			parent1 = pop[list.remove(rList)].getTourInt();
  			
  			int rList2 =r.nextInt(list.size());
  			parent2 = pop[list.remove(rList2)].getTourInt();

  			int XOIndex[] =new int[2]; 
  			
  			//generate random indexes for crossover
  			XOIndex[0]=	r.nextInt((int)(tourSize*rateXO)); //generate random index for start
  			XOIndex[1]= r.nextInt((int)(tourSize*rateXO)); //generate random for end
  			XOIndex[1] += XOIndex[0];//add start index to end index to ensure that is greater and not the same
  			
  			//if end index is greater than tourSize
  			if (XOIndex[1]>tourSize){
  				XOIndex[0]-=(XOIndex[1]-tourSize);
  				XOIndex[1]-=(XOIndex[1]-tourSize);//set end index
  			}

  			

  			// do crossover for even amount of parents
  			for(int s = XOIndex[0];s<XOIndex[1];s++ ){
  				int p1Holder = parent1[s];
  				int p2Holder = parent2[s];
  				for(int find=0;find<tourSize;find++){
  					if(parent1[find] == p2Holder && find !=s){
  						parent1[s] = parent1[find];
  						parent1[find]=p1Holder;
  						  }
  					
  					if(parent2[find] == p1Holder && find !=s){
  						parent2[s] = parent2[find];
  						parent2[find]=p2Holder;
  	  					  }
  					
  				
  					}
  				}// end even crossover
  			
  			Tour tour1= new Tour(cities,parent1);//.setTourInt(parent1);
  			pop[rList] = tour1; 
  			
			Tour tour2= new Tour(cities,parent2);
		  	pop[rList2] =tour2;
  		}
  			
  		
  		//if odd amount of parents use previous parent
  		if(populationSize%2>0){
  			
  			parent1 = pop[pop.length-2].getTourInt();
  			parent2 = pop[pop.length-1].getTourInt();
  			
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
  				int p1Holder = parent1[s];
  				int p2Holder = parent2[s];
  				// do crossover 
  				for(int find=0;find<tourSize;find++){
  					
  					if(parent1[find] == p1Holder && find !=s){
  					parent1[s] = parent1[find];
  					parent1[find]=p1Holder;
  						  }
  					
  					if(parent2[find] == p2Holder && find !=s){
  	  					parent2[s] = parent2[find];
  	  					parent2[find]=p2Holder;
  	  					  }
  					}
  				}// end do crossover
  			
  				Tour tour1= new Tour(cities, parent1);//.setTourInt(parent1);
  				pop[pop.length-2] = tour1; 

				Tour tour2= new Tour(cities, parent2);
	  			pop[pop.length-1] =tour2;
  			}// end if odd xo
  			
  	}// end PMX
  	
  	//Perform Mutation
public void mutate( ){
		
		Random r = new Random();
		
		boolean swap = false;
		int swapIndex = 0;
		
		//Gets a number between 0 and 1
		//Only needs to generate 1 random number
		//This will serve as the basis for future mutation
		double random = Math.abs(r.nextGaussian());
		
		for(int p =0; p < populationSize;p++){
			
			int parent[] = pop[p].getTourInt();
			
			for(int i = 0; i < cities.length; ++i){
			 
				//Checks if the random number is less than the mutation rate
				if(random < rateMutation){
					//If a number has already been selected to be mutated, the two indicies are swapped
					if(swap){
						//If there are 2 indices that have been selected to be mutates, they are swapped
						int tmp = parent[i];
						parent[i] = parent[swapIndex];
						parent[swapIndex] = tmp;
						swap = false;
					}else{
						//First index is selected to be swapped
						swapIndex = i;
						swap = true;
					}
				}
			
				random += rateMutation; //Adds the rate to random
			
				if(random > 1){
					random -= 1;
				}
			}
			
			Tour tour1= new Tour(cities, parent);
		  	pop[p] = tour1;
		  	addElite(tour1);
		}//end for population Size
		
	}
  	
  	//Check Termination Conditions
  	public Tour getElite(){
  		return elitist.peekFirst();//[0];
  	}
  	
  	public void addElite(Tour iTour){
  		
  		iTour= new Tour(cities, iTour.getTourInt());
  		
  		boolean add = true;
  			
  		for(int i=0; i < elitist.size() ;i++){
  			if(Arrays.equals(elitist.get(i).getTourInt(), iTour.getTourInt())){
  				add = false;
  			}
  		}
  			
  		if(add){
  			elitist.add(iTour);
  			elitist.sort(Utils.sortByFitness);
  			
  			if(elitist.size() > 10){
  	  			elitist.removeLast();
  			}
  			
  		}
  		
  		
  	}//end add elite
  	
  	public void showElite(){
  		System.out.println(elitist);
  	}
  	
  	public void SeedElite(){
  		
  		Random r = new Random();
  		
  		for(int i=0; i < elitist.size(); i++){
  			pop[r.nextInt(populationSize)] = new Tour(cities, elitist.get(i).getTourInt());
  		}
  		
	}
  	
  	public void UpdateXORate(double rateXO){
  		this.rateXO = rateXO;
  	}
  	
}
