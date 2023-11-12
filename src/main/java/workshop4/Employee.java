package workshop4;

import java.util.*;

public class Employee {
    final static String NAME = "name";
    final static String PERSONNEL_NUMBER = "personnelNumber";
    final static String PHONE_NUMBER = "phoneNumber";
    final static String EXPERIENCE = "experience";

    private Map<String, String> employeeData;

    public Employee() {
        this.employeeData = new HashMap<>();
        employeeData.put(PERSONNEL_NUMBER, null);
        employeeData.put(NAME, null);
        employeeData.put(PHONE_NUMBER, null);
        employeeData.put(EXPERIENCE, null);
    }

    public Map<String, String> getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(Map<String, String> employeeData) {
        this.employeeData = employeeData;
    }

    @Override
    public String toString() {
        return "таб.номер:" + employeeData.get(PERSONNEL_NUMBER) + "|" +
                "имя:" + employeeData.get(NAME) + "|" +
                "ном.телефона:" + employeeData.get(PHONE_NUMBER) + "|" +
                "стаж:" + employeeData.get(EXPERIENCE) + "|";
    }

    public static List<Employee> convertToEmployee(String data) {
        List<Employee> listEmployee = new ArrayList<>();
        String[] array = data.split("\\|");
        String[] array1 = new String[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            array1[i] = array[i].split(":")[1];
        }
        for (int i = 0; i < array1.length; ) {
            Employee employee = new Employee();
            employee.getEmployeeData().replace(PERSONNEL_NUMBER, array1[i++]);
            employee.getEmployeeData().replace(NAME, array1[i++]);
            employee.getEmployeeData().replace(PHONE_NUMBER, array1[i++]);
            employee.getEmployeeData().replace(EXPERIENCE, array1[i++]);
            listEmployee.add(employee);
        }
        return listEmployee;
    }
}