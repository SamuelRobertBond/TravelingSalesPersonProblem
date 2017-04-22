import java.math.BigDecimal;

public class City {
	
	public double x;
	public double y;
	
	/**
	 * Data Structure used to store city coordinates
	 * @param x - X Coord of a city
	 * @param y - Y Coord of a city
	 */
	
	public City(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the distance between two cities
	 * @param there - The City to be travelled to
	 * @return
	 */
	public double howFarFrom(City there){
	   	return (int)Math.round(Math.sqrt(Math.pow( (there.x- x),2) + Math.pow( (there.y - y), 2)));
	}
}
