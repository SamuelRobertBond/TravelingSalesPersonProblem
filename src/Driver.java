import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		City cities[] = null;
		
		//Bounds
		int maxGeneration = 1000;
		int populationSize = 100;
		
		//Rates of Change
		double rateMut = 0.6;
		double rateXO = 0.5;
		
		//Control Values
		int generation = 0;
		int eliteCnt = 0;
		boolean XOSwitch = true;
		
		if (args.length==1){
			cities = Utils.getCities(new File(args[0]));
		}else if(args.length == 0){
			//cities = Utils.getCities(new File("res/dj38.txt"));
		}
		
		if(args.length>1){
			for(int i=0; i < args.length; ++i){
				switch(args[i].toLowerCase()){
				
				case "-f":
					cities = Utils.getCities(new File(args[i+1]));
					break;
				
				case "-g":
					maxGeneration = Integer.parseInt(args[i+1]);
					break;
				
				case "-p":
					populationSize = Integer.parseInt(args[i+1]);
					break;
				
				case "-m":
					rateMut = Double.parseDouble(args[i+1]);
					break;
				
				case "-c":
					rateXO = Double.parseDouble(args[i+1]);
					break;
				
				default:
					break;
					
				}
				
			}
		}
		
		//create genetic algorithm object
		GeneticAlgorithm geneAl = new GeneticAlgorithm(populationSize,rateMut,rateXO, cities);
		
		//Initialize Population
		geneAl.InitPop(cities.length);
		double eliteDistance = geneAl.getElite().getTourDistance();
		
		//check termination conditions
		while(generation<maxGeneration){
			
			//evaluate population
			geneAl.selectParents();
			
			//perform crossover
			geneAl.CrossOver(XOSwitch);
			geneAl.mutate();
			
			if (eliteDistance > geneAl.getElite().getTourDistance()){
				eliteDistance = geneAl.getElite().getTourDistance();
				eliteCnt=0;
			}else{
				eliteCnt++;
			}
			
			//for changing the crossover algorithms
			if(eliteCnt>maxGeneration*.01){
				XOSwitch=!XOSwitch;
				eliteCnt = 0;
				geneAl.SeedElite();
				
			}
				
			// adjust crossover rate as algorithm progresses	
			
			
			generation++;
		}//end generation while
		
		System.out.println(geneAl.getElite());
	}
	
}
