package edu.westga.cs6311.climate.model;

/**
 * Represents temperature and precipitation measurement taken at a time-of-day
 * 
 * @author CS6311
 * @version Fall 2022
 *
 */
public class HourlyMeasurement {
	public static final int FAHRENHEIT_MAX = 100;
	public static final int FAHRENHEIT_MIN = -150;
	private int hourOfDay;
	private int tempInF;
	private int inchesOfPrecipitation;
	private int windSpeed;
	
	/**
	 * Creates a new Measurement with the given values.
	 * 
	 * @precondition hourOfDay >= 0 && hourOfDay <= 23 && tempInF >= -150 && tempInF <= 100 && inchesOfPrecipitation >= 0 
	 * 
	 * @postcondition getHourOfDay()==hourOfDay && getTempInF()==tempInF && getInchesOfPrecipitation()==inchesOfPrecipitation
	 * 
	 * @param hourOfDay the hour of the day, military-style (0 for 12am, 12 for 12pm, 23 for 11pm, etc)
	 * @param tempInF the temperature in Fahrenheit
	 * @param currentPrecipitation the inches of rain or snow that have fallen since the last measurement
	 * @param windSpeed the speed of the wind at given hour 
	 */
	public HourlyMeasurement(int hourOfDay, int tempInF, int currentPrecipitation, int windSpeed) {
		
		if (hourOfDay < 0 || hourOfDay > 23) {
			throw new IllegalArgumentException("hourOfDay must be between 0 and 23 inclusive");
		}
		
		if (tempInF < HourlyMeasurement.FAHRENHEIT_MIN || tempInF > HourlyMeasurement.FAHRENHEIT_MAX) {
			throw new IllegalArgumentException("tempInF must be between -150 and 100 inclusive");
		}
		
		if (currentPrecipitation < 0) {
			throw new IllegalArgumentException("currentPrecipitation must be at least 0");
		}
		
		if (windSpeed < 0) {
			throw new IllegalArgumentException("windSpeed must be at least 0");
		}
		
		this.hourOfDay = hourOfDay;
		this.tempInF = tempInF;
		this.inchesOfPrecipitation = currentPrecipitation;
		this.windSpeed = windSpeed;
	}
	
	/**
	 * Gets the hour at which this measurement was made.
	 * 
	 * @return the hour at which this measurement was made.
	 */
	public int getHourOfDay() {
		return this.hourOfDay;
	}

	/**
	 * Gets the temperature in Fahrenheit
	 * 
	 * @return the temperature in Fahrenheit
	 */
	public int getTempInF() {
		return this.tempInF;
	}

	/**
	 * Gets the amount of rainfall and/or snowfall, in inches, since the last measurement.
	 * 
	 * @return the amount of rainfall and/or snowfall, in inches, since the last measurement.
	 */
	public int getInchesOfPrecipitation() {
		return this.inchesOfPrecipitation;
	}
	
	/**
	 * Gets the speed of the wind 
	 * 
	 * @return the speed of the wind
	 */
	public int getWindSpeed() {
		return this.windSpeed;
	}

	@Override
	public String toString() {
		return "HourlyMeasurement [hourOfDay=" + this.hourOfDay + ", tempInF=" + this.tempInF + ", currentPrecipitation=" + this.inchesOfPrecipitation + ", windSpeed=" + this.windSpeed + "]";
	}

}
