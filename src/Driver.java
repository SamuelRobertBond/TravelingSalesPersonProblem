import java.io.File;
import java.nio.file.Paths;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("C:/Users/cincott1/git/TravelingSalesPersonProblem/res/dj38.txt"));
		int maxGeneration =  10;
		int populationSize = 1000;
		double rateMut=0.6;
		double rateXO = 0.5;
		int generation=0;
		Tour solution;
		int eliteCnt=0;
		int updateElite=0;
		int switchCnt=0;
		boolean XOSwitch=true;
		if (args.length==1){
			cities = Utils.getCities(new File(args[0]));
		}
		if(args.length>1){
			for(int i=0; i<args.length;i+=2){
				switch(args[i]){
				case "-f":cities = Utils.getCities(new File(args[i+1]));
					break;
				case "-g":maxGeneration = Integer.parseInt(args[i+1]);
					break;
				case "-p":populationSize = Integer.parseInt(args[i+1]);
					break;
				case "-m":rateMut = Double.parseDouble(args[i+1]);
				break;
				case "-c":rateXO = Double.parseDouble(args[i+1]);
				break;
				default:
				break;
					
				}
				
			}
		}
		
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
