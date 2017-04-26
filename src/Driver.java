import java.io.File;

public class Driver {

	public static void main(String args[]){
		
		 /********************************************************************************
	     *This Class is used as the main entry point for the application.           	*
	     ********************************************************************************/
		
		//Reads in from file and inits population
		//Change Parameters before submitting ---------------
		City cities[] = Utils.getCities(new File("res/dj38.txt"));
		int maxGeneration =  100;
		int populationSize = 100;
		double rateMut=0.4;
		double rateXO = 0.7;
		int XOAdjust[] = new int[2];
			XOAdjust[0]=maxGeneration-((int) (maxGeneration*rateXO));
			XOAdjust[1]=maxGeneration-((int) (maxGeneration*rateXO));
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
			/*if(generation>XOAdjust[0]){
				if(rateXO>0.2){
					rateXO-=.1;
				}
				geneAl.UpdateXORate(rateXO);
				XOAdjust[0]+=XOAdjust[1];
				System.out.println("Generation:"+ generation +" ratexo:"+rateXO);
				System.out.println(geneAl.getElite() + "\nSwitch Count:" +switchCnt +"\nElite Updates"+updateElite );
			}*/
			
			if (eliteDistance > geneAl.getElite().getTourDistance()){
				eliteDistance = geneAl.getElite().getTourDistance();
				eliteCnt=0;
				updateElite++;
				System.out.println(geneAl.getElite());
			}else{
				eliteCnt++;
			}
			
			if(eliteCnt>maxGeneration*.01){
				XOSwitch=!XOSwitch;
				switchCnt++;
				eliteCnt=0;
				geneAl.SeedElite();
				
			}
				
			// adjust crossover rate as algorithm progresses	
			
			
			generation++;
		}//end generation while
		
		System.out.println(geneAl.getElite() + "\nSwitch Count:" +switchCnt +"\nElite Updates"+updateElite );
		geneAl.showElite();
	}
	
}
