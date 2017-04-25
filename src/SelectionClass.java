import java.util.Random;
import java.util.Set;

public class SelectionClass {

public static Tour[] selectParents(Tour population[], int amount){
		
		Tour parents[] = new Tour[amount];
		
		double totalFitness = 0;		
		double thresholds[] = new double[population.length];
		
		for(int i = 0; i < population.length; ++i){
			totalFitness += population[i].getFitness();
		}
		
		thresholds[0] = population[0].getFitness();
		
		for(int i = 1; i < population.length; ++i){
			thresholds[i] = population[i].getFitness() + thresholds[i - 1];
		}
		
		
		Random r = new Random();
		
		double increment = totalFitness / amount;
		double spin = Math.abs(r.nextGaussian()) * totalFitness;
		
		for(int i = 0; i < parents.length; ++i){	
			
			spin += increment;
			
			for(int j = 0; j < thresholds.length; ++j){
				if(spin <= thresholds[j] || spin > thresholds[thresholds.length - 1]){
					parents[i] = population[j];
					j = thresholds.length; //Breaks the inner loop
				}
			}
			
			if(spin > totalFitness){
				spin -= totalFitness;
			}
			
		}
		
		return parents;
	}
	
}
