import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		int maxGeneration = 10000;
		int populationSize = 100;
		double rateMut=0.1;
		double rateXO = 0.5;
		int generation=0;
		Tour solution;
		int eliteCnt=0;
		int updateElite=0;
		int switchCnt=0;
		boolean XOSwitch=true;
		
		//create genetic algorithm object
		GeneticAlgorithm geneAl = new GeneticAlgorithm(populationSize,rateMut,rateXO, Utils.getCities(new File("res/dj38.txt")));
		
		//Initalize Population
		int population[][] = geneAl.InitPop(cities.length);
		double eliteDistance = geneAl.getElite().getTourDistance();
		System.out.println(eliteDistance);
		
		//check terminiation conditions
		while(generation<maxGeneration){
			//evaluate population
			geneAl.selectParents();
			//perform crosover
			geneAl.CrossOver(XOSwitch);
			//geneAl.OX(cities.length);
			//geneAl.PMX(cities.length);
			//Preforms Mutation
			geneAl.mutate();
			
			if (eliteDistance > geneAl.getElite().getTourDistance()){
				eliteDistance = geneAl.getElite().getTourDistance();
				eliteCnt=0;
				updateElite++;
			}else{
				eliteCnt++;
			}
			
			if(eliteCnt==10){
				XOSwitch=!XOSwitch;
				switchCnt++;
				eliteCnt=0;
				//if(switchCnt%100==0 && switchCnt>100)
				//geneAl.SeedElite();
			}
			
			generation++;
		}//end generation while
		System.out.println(geneAl.getElite() + "\nSwitch Count:" +switchCnt +"\nElite Updates"+updateElite );
		geneAl.showElite();
	}
	
}
