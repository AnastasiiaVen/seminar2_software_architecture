package ru.geekbrains.seminar2;

public class Homework {


    // Первый вариант исполнения (для себя):

//    static Employee generateEmployee(){
//
//        //Collection<Employee> employees = new ArrayList<>();
//
//        String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим",
//                "Панкратий", "Рубен", "Герман" };
//        String[] surnames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков",
//                "Копылов", "Горбунов", "Лыткин", "Соколов" };
//
//        for (int i = 0; i < names.length; i++) {
//            if (i < 5) {
//                Double salary = 70000d;
//                Employee employee = new Worker(names[i], surnames[i], salary);
//                return employee;
//                //System.out.println(employee);
//                //employees.add(employee);
//            } else {
//                Double salary = 1000d;
//                Employee employee = new Freelancer(names[i], surnames[i], salary);
//                return employee;
//                //System.out.println(employee);
//                //employees.add(employee);
//            }
//
//        }
//        //System.out.println(employees);
//        return null;
//    }

    //Второй вариант исполнения:
    enum EmployeeType{
        Worker,
        Freelancer
    }

    abstract class EmployeeCreator {


        public Employee generateEmployee(EmployeeType employeeType) {

            Employee employee = createEmployee(employeeType);
            String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим",
                    "Панкратий", "Рубен", "Герман" };
            String[] surnames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков",
                    "Копылов", "Горбунов", "Лыткин", "Соколов" };
            for (int i = 0; i < names.length; i++) {
                employee.name = names[i];
                employee.surname = surnames[i];
                employee.salary = employee.calculateSalary();
            }
            return employee;
        }

        protected abstract Employee createEmployee(EmployeeType employeeType);

    }


    class ConcreteEmployeeCreator extends EmployeeCreator{

        @Override
        protected Employee createEmployee(EmployeeType employeeType) {
            return switch (employeeType){
                case Worker -> new Worker();
                case Freelancer -> new Freelancer();
            };
        }
    }


    public static void main(String[] args) {

        // Worker worker1 = new Worker("Анатолий", "Шестанов", 70000);

        // Первый вариант исполнения:
        //generateEmployee();


        //TODO: Домашняя работа
        // 1. Доработать метод generateEmployee(), вернуть сотрудника определенного типа.
        // 2***. Метод generateEmployee() должен быть без входных параметров, тип сотрудника,
        // фио и ставка генерируются автоматически.

        ConcreteEmployeeCreator concreteEmployeeCreator = new ConcreteEmployeeCreator();

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++){

            employees[i] = concreteEmployeeCreator.generateEmployee(EmployeeType.Worker);
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

}

/**
 * Работник (базовый класс)
 */
abstract class Employee {

    protected String name;
    protected String surname;
    /**
     * Ставка заработной платы
     */
    protected double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public Employee() {
    }

    /**
     * Расчет среднемесячной заработной платы
     * @return
     */
    public abstract double calculateSalary();

}

class Freelancer extends Employee{

    public Freelancer(String name, String surname, double salary) {
        super(name, surname, salary);
    }

    public Freelancer() {

    }

    @Override
    public double calculateSalary() {
        return 15 * 6 * salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер; Среднемесячная заработная плата: %.2f (руб.); Почасовая ставка: %.2f (руб.)",
                surname, name, calculateSalary(), salary);
    }
}

class Worker extends Employee{

    public Worker(String name, String surname, double salary) {
        super(name, surname, salary);
    }

    public Worker() {

    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.)",
                surname, name, salary);
    }
}