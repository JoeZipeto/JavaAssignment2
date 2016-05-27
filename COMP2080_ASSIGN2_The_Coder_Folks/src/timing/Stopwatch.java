//********************************************************************************************************
//*	Project: COMP2080_ASSIGN1_YaghoubiVije_Bahman
//*	Assignment: assignment#1
//*	Author: Bahman Yaghoubi Vije
//*	Student number: 100968843
//*	Date: February 16, 2016
//*	Description: This file prepares a stopWatch to test time consumption of different methods.
//********************************************************************************************************
package timing;
/**
 A class to measure time elapsed.
*/

public class Stopwatch
{
    private long startTime;
    private long stopTime;

    public static final double NANOS_PER_SEC = 1000000000.0;

	/**
	 start the stop watch.
	*/
	public void start()
	{	System.gc();
		startTime = System.nanoTime();
	}

	/**
	 stop the stop watch.
	*/
	public void stop()
	{	stopTime = System.nanoTime();	}

	/**
	elapsed time in secods.
	@return the time recorded on the stopwatch in seconds
	*/
	public double time()
	{	return (stopTime - startTime) / NANOS_PER_SEC;	}

	public String toString()
	{   return "elapsed time: " + time() + " seconds.";
	}

	/**
	elapsed time in nanoseconds.
	@return the time recorded on the stopwatch in nanoseconds
	*/
	public long timeInNanoseconds()
	{	return (stopTime - startTime);	}
}

