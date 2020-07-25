package employee.schedule.main;

import employee.schedule.algorithm.SchedulingAlgorithm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		SchedulingAlgorithm schedule = new SchedulingAlgorithm();
				
		
		schedule.assignShift();
		
		schedule.displayEmployee();
		schedule.displayShifts();
	}

}
