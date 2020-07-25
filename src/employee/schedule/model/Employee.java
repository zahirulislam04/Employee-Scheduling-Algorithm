package employee.schedule.model;

public class Employee{


	private String name;
	private double desiredEarnings;

	public Employee(String name, double desiredEarnings) {
		this.name = name;
		this.desiredEarnings = desiredEarnings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDesiredEarnings() {
		return desiredEarnings;
	}

	public void setDesiredEarnings(double desiredEarnings) {
		this.desiredEarnings = desiredEarnings;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", desiredEarnings=" + desiredEarnings + "]";
	}

	

}
