
public class Tour{
    /********************************************************************************
     *This Class is used for storing a Tour, which is a collection Cities.      	*
     *Has a property for the total distance of the tour, getTourDistance.       	*
     ********************************************************************************/
    City tour[];
    double tourDistance = 0;
    
    public Tour(City cities[], int tourPath[]){
    	
    	this.tour = new City[cities.length];
    	
    	for (int Index = 0; Index < this.tour.length; Index++) {
    		this.tour[Index] = cities[tourPath[Index]];
    	}
    	
    	tourDistance = getTourDistance();
    	System.out.println("Tour Distance: " + tourDistance);
    }
    
    public double getTourDistance() {
        	if (this.tourDistance > 0) {
              	return this.tourDistance;
        	}

        	// calulate total distance of tour
       	 
        	for (int cityIndex = 0; cityIndex + 1 < this.tour.length; cityIndex++) {
              	this.tourDistance += this.tour[cityIndex].howFarFrom(this.tour[cityIndex + 1]);
        	}
   		 // add distance from last city back to first city
        	this.tourDistance += this.tour[this.tour.length - 1].howFarFrom(this.tour[0]);
     	 
        	return this.tourDistance;
    }
}