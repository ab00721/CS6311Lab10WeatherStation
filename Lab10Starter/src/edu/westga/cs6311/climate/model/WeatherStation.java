package edu.westga.cs6311.climate.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Summary of weather station for weather data recorded over a period of days from different locations 
 *
 * @author Anna Blood
 * @version 11/01/22
 *
 */
public class WeatherStation {
	
	public static int DANGEROUSLY_HOT_TEMPERATURE = 50;
	
	private DailySummary dailySummary;
	private ArrayList<DailySummary> dailySummaries;
	
	/**
	 * Creates a new weather station with the given dailySummary 
	 * 
	 * @precondition the dailySummary is not null or empty
	 * @postcondition getdailySummary() == dailySummary && getDailySummaries() == dailySummaries
	 * 
	 * @param dailySummary to set the name of the dailySummary for the weatherStation
	 */
	public WeatherStation(DailySummary dailySummary) {
		if (dailySummary == null || dailySummary.getHourlyMeasurements().isEmpty()) {
			throw new IllegalArgumentException("the daily summary can not be null or empty");
		}
		
		this.dailySummary = dailySummary;
		this.dailySummaries = new ArrayList<DailySummary>();
	}
	
	/**
	 * Adds a daily summary to the dailySummaries ArrayList.
	 * 
	 * @precondition dailySummary != null && getDate() does not match the date of an already-added summary
	 * @postcondition this.dailySummaries.size() = this.dailySummaries.size()@prev + 1
	 * 
	 * @param dailySummary the summary to add to the ArrayList  
	 */
	public void add(DailySummary dailySummary) {
		if (dailySummary == null) {
			throw new IllegalArgumentException("dailySummary can not be null");
		}
		
		for (DailySummary summary: this.dailySummaries) {
			if (summary.getDate() == dailySummary.getDate()) {
				throw new IllegalArgumentException("can not add different summary for same date.");
			}
		}

		this.dailySummaries.add(dailySummary);
	}
	
	/**
	 * Finds the hiTemp for each day in a ArrayList of dailySummaries and returns the date of the day 
	 * with the hottest hiTemp recorded by the weather station or null if no data is recorded 
	 * 
	 * @precondition none 
	 * @postcondition dailySummary.getDate() = date when HiTemp was the hottest compared to other days 
	 * 
	 * @return date of hottest day recorded by station 
	 */
	public LocalDate findHottestDay() {
		int max = 0;
		for (DailySummary summary: this.dailySummaries) {
			if (summary.findHiTempInF() > max) {
				max = summary.findHiTempInF();
			}
		}
		return this.dailySummary.getDate();
	}
	
	/**
	 * Finds the hiTemp for each day in an ArrayList of dailySummaries and compares it to DANGEROUSLY_HOT_TEMP constant.
	 * Count increases if hiTemp was above constant. 
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return number of days hi temp was above DANGERSOUSLY_HOT_TEMP constant (50F)
	 */
	public int countDaysOver50F() {
		int count = 0;
		for (DailySummary summary: this.dailySummaries) {
			if (summary.findHiTempInF() > DANGEROUSLY_HOT_TEMPERATURE) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates the average windspeed from daily summary 
	 * 
	 * @precondition dailySummary != null 
	 * @postcondition none
	 * 
	 * @param dailySummary sets the daily summary to use to calculate average windspeed 
	 * @return average windspeed from the given dailySummary 
	 */
	public double averageWindSpeed(DailySummary dailySummary) {
		if (dailySummary == null) {
			throw new IllegalArgumentException("dailySummary can not be null");
		}
		
		double sum = 0;
		int count = 0; 
		for (DailySummary summary: this.dailySummaries) {
			for (int hour = 0; hour < summary.getHourlyMeasurements().size(); hour++) {
				sum = summary.getMeasurementAt(hour).getWindSpeed();
				count++;
			}
		}
		return (sum / count);
	}
	
	/**
	 * Calculates the average windspeed from daily summary when temp is less than -25F
	 * 
	 * @precondition dailySummary != null and temp < -25F
	 * @postcondition none
	 * 
	 * @param dailySummary sets the daily summary to use to calculate average windspeed 
	 * @return average windspeed from the given dailySummary 
	 */
	public double averageWindSpeedWithLowTemp(DailySummary dailySummary) {
		if (dailySummary == null) {
			throw new IllegalArgumentException("dailySummary can not be null");
		}
		
		double sum = 0;
		int count = 0;
		for (DailySummary summary: this.dailySummaries) {
			for (int hour = 0; hour < summary.getHourlyMeasurements().size(); hour++) {
				if (summary.getMeasurementAt(hour).getTempInF() < -25) {
					sum = summary.getMeasurementAt(hour).getWindSpeed();
					count++;
				}
			}
		}
		return (sum / count);
	}
	
	/**
	 * Gets the average of all recorded temperatures for the day.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the average of all recorded temperatures for the day, or Double.MIN_VALUE if no measurements recorded.
	 */
	public double getAverageTemp() {
		
		if (this.dailySummary.getHourlyMeasurements().isEmpty()) {
			return Double.MIN_VALUE;
		}
		double sum = 0;
		for (HourlyMeasurement measurement: this.dailySummary.getHourlyMeasurements()) {
			int tempF = measurement.getTempInF();
			sum += tempF;
		}
		return sum / this.dailySummary.getHourlyMeasurements().size();
	}
	
	/**
	 * Gets the dailySummary of the station 
	 * 
	 * @return the dailySummary 
	 */
	
	public DailySummary getDailySummary() {
		return this.dailySummary;
	}
	
	/**
	 * Sets the dailySummary of the station
	 * 
	 * @param dailySummary to set daily summary from that station 
	 */
	public void setName(DailySummary dailySummary) {
		this.dailySummary = dailySummary;
	}
	
	/**
	 * Gets the daily summaries for this WeatherStation
	 * 
	 * @return the daily summaries 
	 */
	public ArrayList<DailySummary> getDailySummaries() {
		return this.dailySummaries;
	}
	
	/**
	 * Sets the daily summaries for this weatherStation
	 * 
	 * @param dailySummaries to set dailySummaries ArrayList 
	 * 
	 */
	public void setDailySummaries(ArrayList<DailySummary> dailySummaries) {
		this.dailySummaries = dailySummaries;
	}
	
}
