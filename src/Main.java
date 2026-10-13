import java.util.HashMap;
import java.util.Scanner;

//Class for Exception Handling
class InvalidArgumentException extends RuntimeException{
	public InvalidArgumentException(String message) {
		super(message);
	}
}

//Class for Creating Employee
class Employee{
	private int id;
	private String name;
	private String dept;
	private int salary;
	
	public Employee(int id,String name,String dept,int salary){
		this.id=id;
		this.name=name;
		this.dept=dept;
		this.salary=salary;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDept(){
		return this.dept;
	}
	
	public int getSalary(){
		return this.salary;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setDept(String dept){
		this.dept=dept;
	}
	
	public void setSalary(int salary){
		this.salary=salary;
	}
}

//Class to get the Employee Services
class EmployeeService{
	
	//For Storing the Employees
	HashMap<Integer,Employee> employees;
	
	public EmployeeService() {
		this.employees=new HashMap<Integer,Employee>();
	}
	
	//To add a new employee
	public void addEmployee(Employee emp) {
		
		if(employees.containsKey(Integer.valueOf(emp.getId()))) {
			throw new InvalidArgumentException("Employee with this id already exist.");
		}
		Integer id=emp.getId();
		employees.put(id,emp);
	}
	
	//To remove an employee
	public void removeEmployee(int id) {
		if(!employees.containsKey(Integer.valueOf(id))) {
			throw new InvalidArgumentException("Employee with provided id does not exist.");
		}
		employees.remove(Integer.valueOf(id));
	}
	
	//To update the details of an Employee
	public void updateEmployee(int id,Employee emp) {
		if(!employees.containsKey(Integer.valueOf(id))) {
			throw new InvalidArgumentException("No employee exist with the provided id.");
        }
			Employee empp=employees.get(Integer.valueOf(id));
			empp.setName(emp.getName());
			empp.setDept(emp.getDept());
			empp.setSalary(emp.getSalary());
		
		employees.remove(id);
		employees.put(Integer.valueOf(id),empp);
		
	}

	//To display the details of all the employees
    public void displayAllEmployees(){
        if(employees.size()==0){
            System.out.println("No employee present.");
            return;
        }
        for(Employee emp:employees.values()){
            System.out.println("Employee id: "+emp.getId());
            System.out.println("Employee name: "+emp.getName());
            System.out.println("Employee department: "+emp.getDept());
            System.out.println("Employee salary: "+emp.getSalary());
        }
    }
	
    //To filter the employees by department
	public Employee[] getByDept(String dept) {
		return employees.values().stream().filter(emp->emp.getDept().equalsIgnoreCase(dept)).toArray(Employee[]::new);
	}
	
	//To get the total employees salary
	public int getTotalSalary() {
		return employees.values().stream().mapToInt(Employee::getSalary).sum();
	}
}

public class Main {
	public static void main(String args[]) {
		
		EmployeeService empService=new EmployeeService();
		
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Console Based Employee Management System:"+"\n");
		System.out.print("Enter Your Choice:"+"\n");
		System.out.print("1->Add an Employee, 2->Remove an Employee with a given id, 3->Update an Employee, 4->Get all the employees by department, 5->Get the total salary of the employees, 6->Get all employees, 7->Exit"+"\n");
		
		System.out.print("Please Enter your choice: ");
		int ch;
		ch=sc.nextInt();
		
		//Looping in order to get user choice
		while(ch<=6 && ch>0) {
			if(ch==1) {
				System.out.print("Please enter the details of the employee:-"+"\n");
				System.out.print("Enter id->");
				int id=sc.nextInt();
				System.out.print("Enter name->");
				String name=sc.next();
				System.out.print("Enter department-> ");
				String dept=sc.next();
				System.out.print("Enter salary-> ");
				int salary=sc.nextInt();
				
				Employee emp=new Employee(id,name,dept,salary);
				empService.addEmployee(emp);
				System.out.print("Employee added successfully!!");
			}
			else if(ch==2) {
				System.out.print("Enter the id of the employee to be removed: ");
				int id=sc.nextInt();
				empService.removeEmployee(id);
				System.out.print("Employee removed successfully!!");
			}
			else if(ch==3) {
				System.out.print("Enter the id of the employee to be updated: ");
				int id=sc.nextInt();
				
				System.out.print("Please enter the details of the employee:-"+"\n");
				System.out.print("Enter name->");
				String name=sc.next();
				System.out.print("Enter department-> ");
				String dept=sc.next();
				System.out.print("Enter salary-> ");
				int salary=sc.nextInt();
				
				Employee emp=new Employee(0,name,dept,salary);
				empService.updateEmployee(id, emp);
				System.out.print("Employee details updated successfully!!");
			}
			else if(ch==4) {
				System.out.print("Enter the department name for which you want all the employees: ");
				String dept=sc.next();
				
				Employee[] emps=empService.getByDept(dept);
				
				for(int i=0;i<emps.length;i++){
            System.out.println("Employee id: "+emps[i].getId());
            System.out.println("Employee name: "+emps[i].getName());
            System.out.println("Employee department: "+emps[i].getDept());
            System.out.println("Employee salary: "+emps[i].getSalary());
        }
			}
			else if(ch==5) {
				int finAmount=empService.getTotalSalary();
				System.out.print("The total salary of the employees is "+finAmount);
			}
			else if(ch==6) {
				empService.displayAllEmployees();
			}
			System.out.print("\nPlease Enter your choice: ");
			ch=sc.nextInt();
		}
		
		System.out.println("Thank you!!");
	}
}
