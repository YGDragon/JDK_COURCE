package workshop4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static workshop4.Employee.convertToEmployee;

public class EmployeeDirectory {
    protected List<Employee> directory = new ArrayList<>();
    private final static String PATH = "src\\main\\java\\workshop4\\repository.txt";
    private final static String MESSAGE_NAME = "Введите имя сотрудника: ";
    private final static String MESSAGE_PERSONNEL_NUMBER = "Введите табельный номер сотрудника: ";
    private final static String MESSAGE_PHONE_NUMBER = "Введите номер телефона сотрудника: ";
    private final static String MESSAGE_EXPERIENCE = "Введите стаж сотрудника: ";
    private final static String MESSAGE_EXPERIENCE_FIND = "Поиск сотрудника по стажу: ";
    private final static String MESSAGE_NAME_FIND = "Поиск имени сотрудника по номеру телефона: ";
    private final static String MESSAGE_PERSONNEL_NUMBER_FIND = "Поиск сотрудника по табельному номеру: ";

    // поиск сотрудника по стажу
    public void findExperience() {
        readFromRepository();
        List<Employee> employeeList = new ArrayList<>();
        Scanner enter = new Scanner(System.in);
        printMessage(MESSAGE_EXPERIENCE_FIND);
        String experience = enter.nextLine();
        for (Employee emp : directory
        ) {
            if (emp.getEmployeeData().get(Employee.EXPERIENCE).equals(experience)) {
                employeeList.add(emp);
            }
        }
        displayResult(employeeList);
    }

    // поиск имени сотрудника по номеру телефона
    public void findNameOfPhone() {
        readFromRepository();
        List<String> employeeList = new ArrayList<>();
        Scanner enter = new Scanner(System.in);
        printMessage(MESSAGE_NAME_FIND);
        String phone = enter.nextLine();
        for (Employee emp : directory
        ) {
            if (emp.getEmployeeData().get(Employee.PHONE_NUMBER).equals(phone)) {
                employeeList.add(emp.getEmployeeData().get(Employee.NAME));
            }
        }
        displayResult(employeeList);
    }

    // поиск сотрудника по табельному номеру
    public void findPersonnelNumber() {
        readFromRepository();
        Scanner enter = new Scanner(System.in);
        printMessage(MESSAGE_PERSONNEL_NUMBER_FIND);
        String number = enter.nextLine();
        for (Employee emp : directory
        ) {
            if (emp.getEmployeeData().get(Employee.PERSONNEL_NUMBER).equals(number)) {
                displayResult(emp);
            }
        }
    }

    public void enterNewEmployee() {
        Employee employee = new Employee();
        Scanner enter = new Scanner(System.in);
        printMessage(MESSAGE_PERSONNEL_NUMBER);
        employee.getEmployeeData().replace("personnelNumber", enter.nextLine());
        printMessage(MESSAGE_NAME);
        employee.getEmployeeData().replace("name", enter.nextLine());
        printMessage(MESSAGE_PHONE_NUMBER);
        employee.getEmployeeData().replace("phoneNumber", enter.nextLine());
        printMessage(MESSAGE_EXPERIENCE);
        employee.getEmployeeData().replace("experience", enter.nextLine());
        directory.add(employee);
        writeToRepository(employee);
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

    private <T> void displayResult(T employee) {
        System.out.println(employee);
    }

    public void displayAll() {
        for (Employee emp : directory) {
            displayResult(emp);
        }
    }

    //запись сотрудника в репозиторий справочника
    private void writeToRepository(Employee employee) {
        try (FileWriter fw = new FileWriter(PATH, true)) {
            fw.write(employee + "\n");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    //чтение данных из репозитория справочника
    public void readFromRepository() {
        directory.clear();
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(PATH)) {
            int i = fr.read();
            while (i != -1) {
                sb.append(((char) i));
                i = fr.read();
            }
            directory = convertToEmployee(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            new File(PATH);
        }
    }
}