package workshop3.calculator;

public class Calculator {

    public <T extends Number> Number sum(T param1, T param2) {
        return checkRemainder(param1.doubleValue() + param2.doubleValue());
    }

    public <T extends Number> Number multiply(T param1, T param2) {
        return checkRemainder(param1.doubleValue() * param2.doubleValue());
    }

    public <T extends Number> Number divide(T param1, T param2) {
        if (param2.equals(0)) {
            throw new RuntimeException("Недопустимо деление на 0");
        }
        return checkRemainder(param1.doubleValue() / param2.doubleValue());
    }

    public <T extends Number> Number subtract(T param1, T param2) {
        return checkRemainder(param1.doubleValue() - param2.doubleValue());
    }

    // проверка остатка для отсеивания дробной части
    private Number checkRemainder(Double result) {
        if ((result * 10) % 2 == 0) {
            return Integer.parseInt(result.toString().replace(".0", ""));
        }
        return result;
    }
}