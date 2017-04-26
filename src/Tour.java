
public class Tour{
    /********************************************************************************
     *This Class is used for storing a Tour, which is a collection Cities.      	*
     *Has a property for the total distance of the tour, getTourDistance.       	*
     ********************************************************************************/
    private City tour[];
    private int tourInt[];
    private double tourDistance;
    private double fitness;
    
    /**
     * Contains all the information of the individual tour
     * This information includes the total distance of the tour, the fitness of the tour, 
     * the path of the tour, 
     * @param cities
     * @param tourPath - the 
     */

    public Tour(City cities[], int tourPath[]){
    	
    	this.tourInt = tourPath;
    	this.tour = new City[cities.length];
    	
    	tourDistance = 0;
    	
    	for (int Index = 0; Index < this.tour.length; Index++) {
    		this.tour[Index] = cities[tourPath[Index]];
    	}
    	
    	tourDistance = calculateTourDistance();
    	//System.out.println("Tour Distance: " + tourDistance);
    	
    	fitness = calculateFitness(tourDistance);
    }

    
   public int[] getTourInt(){
	   return tourInt.clone();
   }

    public String toString(){
    	
    	String s = "";
    	s += "Total Distance: " + tourDistance + "\n";
    	for(int i = 0; i < tour.length; ++i){
    		s += (tourInt[i] + 1)+" ";
  
    	}
    	
    	return s;
    }
    
    public double getTourDistance(){
    	return tourDistance;
    }
    
    public double getFitness(){
    	return fitness;
    }
    
    //Helper method to determine the euclidian distance
    private double calculateTourDistance() {
    	
    	if (this.tourDistance > 0) {
            return this.tourDistance;
       	}

        // calculate total distance of tour
       	 
        for (int cityIndex = 0; cityIndex + 1 < this.tour.length; cityIndex++) {
            this.tourDistance += this.tour[cityIndex].howFarFrom(this.tour[cityIndex + 1]);
        }
        
   		// add distance from last city back to first city
        this.tourDistance += this.tour[this.tour.length - 1].howFarFrom(this.tour[0]);
     	 
        return this.tourDistance;
    }
    
    //Helper method used to calculate the fitness value
    private double calculateFitness(double distance){
    	return 1/distance; 
    }
    
    
}