package employee.schedule.model;

import java.time.LocalDateTime;

public class Shift {	
	private LocalDateTime start;
	private LocalDateTime end;
	private double hourlyEarnings;
	private Employee assignedEmployee;
	
	public Shift(LocalDateTime start, LocalDateTime end, double hourlyEarnings) {		
		this.start = start;
		this.end = end;
		this.hourlyEarnings = hourlyEarnings;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public double getHourlyEarnings() {
		return hourlyEarnings;
	}

	public void setHourlyEarnings(double hourlyEarnings) {
		this.hourlyEarnings = hourlyEarnings;
	}

	public Employee getAssignedEmployee() {
		return assignedEmployee;
	}

	public void setAssignedEmployee(Employee assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}
	
	
	@Override
	public String toString() {
		return "Shift [start=" + start + ", end=" + end + ", hourlyEarnings=" + hourlyEarnings + ", assignedEmployee="
				+ assignedEmployee + "]";
	}

	
	

}
