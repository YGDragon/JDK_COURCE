package workshop3.calculator;

public class MainCalculator {
    public static void main(String[] args) {
        // калькулятор
        Calculator calculator = new Calculator();
        System.out.println("Суммирование");
        System.out.println(calculator.sum(100, 10));
        System.out.println(calculator.sum(100.55, 10));
        System.out.println("Вычитание");
        System.out.println(calculator.subtract(50, 25));
        System.out.println(calculator.subtract(50, 25.25));
        System.out.println("Умножение");
        System.out.println(calculator.multiply(9, 9));
        System.out.println(calculator.multiply(9.5, 9));
        System.out.println("Деление");
        System.out.println(calculator.divide(25, 5));
        System.out.println(calculator.divide(25, 5.8));
        System.out.println(calculator.divide(25, 0));
    }
}
