import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		int maxGeneration =  10000;
		int populationSize = 1000;
		double rateMut=0.6;
		double rateXO = 0.5;
		int generation=0;
		Tour solution;
		int eliteCnt=0;
		int updateElite=0;
		int switchCnt=0;
		boolean XOSwitch=true;
		
		//create genetic algorithm object
		GeneticAlgorithm geneAl = new GeneticAlgorithm(populationSize,rateMut,rateXO, Utils.getCities(new File("res/dj38.txt")));
		
		//Initialize Population
		int population[][] = geneAl.InitPop(cities.length);
		double eliteDistance = geneAl.getElite().getTourDistance();
		//System.out.println(eliteDistance);
		
		//check termination conditions
		while(generation<maxGeneration){
			
			//evaluate population
			geneAl.selectParents();
			
			//perform crosover
			geneAl.CrossOver(XOSwitch);
			geneAl.mutate();
			
			if (eliteDistance > geneAl.getElite().getTourDistance()){
				eliteDistance = geneAl.getElite().getTourDistance();
				eliteCnt=0;
				updateElite++;
				//System.out.println(geneAl.getElite());
			}else{
				eliteCnt++;
			}
			//for changing the crossover algorithms
			if(eliteCnt>maxGeneration*.01){
				XOSwitch=!XOSwitch;
				switchCnt++;
				eliteCnt=0;
				geneAl.SeedElite();
				
			}
				
			// adjust crossover rate as algorithm progresses	
			
			
			generation++;
		}//end generation while
		
		System.out.println(geneAl.getElite());// + "\nSwitch Count:" +switchCnt +"\nElite Updates"+updateElite );
		//geneAl.showElite();
	}
	
}
