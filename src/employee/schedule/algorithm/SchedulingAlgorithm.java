package employee.schedule.algorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import employee.schedule.model.Employee;
import employee.schedule.model.Shift;
import employee.schedule.util.Utility;

public class SchedulingAlgorithm {

	Utility utility = new Utility();
	
	List<Employee> employees = new ArrayList<Employee>();
	List<Shift> shifts = new ArrayList<Shift>();

	HashMap<Employee, Double> employeeMap = new HashMap<Employee, Double>();

	public SchedulingAlgorithm() {

		employees.add(new Employee("David", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Islam", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Zahirul", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Ayesha", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Sarah", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Tom", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Patric", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Jewel", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Jahid", utility.getRandomDesiredEarning()));
		employees.add(new Employee("Selwan", utility.getRandomDesiredEarning()));
		
		sortEmployees();
		createShift();
	}

	/*
	 * Initially, creating 40 shifts without assigning employee
	 * 
	 * */
	public void createShift() {
		Shift shift;
		LocalDateTime start;
		LocalDateTime end;
		int i = 0;
		while (i < 40) {
			start = utility.getRandomLocalDateTime();
			end = start.plusHours(2);
			shift = new Shift(start, end, utility.getRandomHourlyEarning());
			shifts.add(shift);
			i++;
		}

		sortShifts();
	}

	
	/*Assign first 10 shifts to all employees.
	 * Then offer shifts to employee who has lowest desiredEarnings. That's the reason I have made the sorted employee list based on desiredEarning. 
	 * Let him/her to earn as much as close to his/her desiredEarning.
	 * Then offer shifts to employee who has next higher desiredEarnings and so on until all shifts are allocated. 
	 * When offering shifts to an employee, checking whether the shift duration is overlapped with already occupied shift by same employee. 
	 * */
	public void assignShift() {		
		double employeeActualEarning = 0;
		double employeeDesiredEarning=0;
		
		assignAtLeastOneShift();
		
		for (Employee e : employees) {			
			if (employeeMap.isEmpty() == false && employeeMap.get(e) != null) {
				employeeActualEarning = employeeMap.get(e);
			} else {
				employeeActualEarning = 0;
			}
			
			employeeDesiredEarning=e.getDesiredEarnings();

			for (Shift s : shifts) {				
				if (employeeDesiredEarning < employeeActualEarning + s.getHourlyEarnings()) break;
				if (s.getAssignedEmployee() == null) {
					if (isShiftOverlapped(s.getStart(), s.getEnd(), e) == false) {
						s.setAssignedEmployee(e);						
						employeeActualEarning = employeeActualEarning + s.getHourlyEarnings();
					}
				}
			}
			employeeMap.put(e, employeeActualEarning);
		}
	}
	
	/* Allocate first 10 shifts for all 10 employees.
	 * So everyone get shifts at least one.
	 * */
	private void assignAtLeastOneShift() {
		int i=0;
		Shift shift;
		for(Employee e:employees) {
			shift= shifts.get(i);
			shift.setAssignedEmployee(e);
			employeeMap.put(e, shift.getHourlyEarnings());
			i++;
		}
	}

	

	/* **Checking shift overlapping** 
	 * Return true if any overlapping found
	 * */
	private boolean isShiftOverlapped(LocalDateTime start, LocalDateTime end, Employee e) {
		Employee emp;
		for (Shift s : shifts) {
			emp = s.getAssignedEmployee();
			if (emp != null) {
				if (emp.equals(e)) {
					if (s.getStart().isBefore(start) && s.getEnd().isAfter(start)) {
						return true;
					} else if (s.getStart().isAfter(start) && s.getStart().isBefore(end)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	

	public void displayEmployee() {
		Iterator iterator = employeeMap.entrySet().iterator();
		
		System.out.println("********Summary of Employee's Earning***********");
		System.out.println("");
		
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			Employee employee = (Employee) mapEntry.getKey();
			
			System.out.println("Employee Name: " + employee.getName() );		
			System.out.println("		Desired Earnings (Euro)	: " + employee.getDesiredEarnings());
			System.out.println("		Actual Earnings (Euro)	: " + mapEntry.getValue());
			
			System.out.println("");	
		}		
		System.out.println("");
		System.out.println("");
	}

	public void displayShifts() {
		int i = 1;
		String employeeName = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		System.out.println("*********Shift Allocation for Each Employee************");
		System.out.println("");
		
		for (Shift s : shifts) {
			if (s.getAssignedEmployee() == null) {
				employeeName = "";
			} else {
				employeeName = s.getAssignedEmployee().getName();
			}
			System.out.println("Shift " + i + ":");
			
			System.out.println("		Start: " + s.getStart().format(formatter));
			System.out.println("		End: " + s.getEnd().format(formatter));
			System.out.println("		Assigned Employee: " + employeeName);
						
			i++;
						
		}
					
	}
	
	/*Sorting the shifts based on start time of shift on ascending order
	 * */
	public void sortShifts() {

		Comparator<Shift> compareByStarttime = (Shift o1, Shift o2) -> o1.getStart().compareTo(o2.getStart());
		Collections.sort(shifts, compareByStarttime);
	}
	
	/*Sorting employees based on their desiredEarning on ascending order
	 * */
	public void sortEmployees() {
		Collections.sort(employees, new Comparator<Employee>() {		    
			@Override
			public int compare(Employee o1, Employee o2) {
				return Double.compare(o1.getDesiredEarnings(), o2.getDesiredEarnings());
			}
		});
	}
	
}
