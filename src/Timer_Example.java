//Simple way to have a timer for the program

public class Timer_Example {

    public static void main(String args[]) {
    	long startTime = System.currentTimeMillis();
        
    	/*PROGRAM AFTER THIS*/
    	System.out.format("Timer ticking %n");
    	
    	/*PRINTS OUT TIME TAKEN*/
        long endTime   = System.currentTimeMillis();
    	long totalTime = endTime - startTime;
    	System.out.println(totalTime + " milliseconds");
    }
}
