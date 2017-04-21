

public class City {
	
	public float x;
	public float y;
	
	/**
	 * Data Structure used to store city coordinates
	 * @param x - X Coord of a city
	 * @param y - Y Coord of a city
	 */
	
	public City(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the distance between two cities
	 * @param there - The City to be travelled to
	 * @return
	 */
	public float howFarFrom(City there){
	   	return (float)Math.sqrt(Math.pow( (there.x- x),2) + Math.pow( (there.y - x), 2));
	}
}
