package workshop3.compare_arrays;

public class MainCompare {
    public static void main(String[] args) {
        String[] sArray1 = {"1", "2", "3"};
        String[] sArray2 = {"1", "2"};
        Integer[] iArray1 = {1, 2};
        boolean result1 = compareArrays(sArray1, sArray1);
        System.out.printf("Сравнение одинаковых массивов String: %b\n", result1);
        boolean result2 = compareArrays(sArray1, sArray2);
        System.out.printf("Сравнение неодинаковых массивов String: %b\n", result2);
        boolean result3 = compareArrays(sArray1, iArray1);
        System.out.printf("Сравнение массивов String и Integer: %b\n", result3);
    }

    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        return array1.length == array2.length && array1.getClass().equals(array2.getClass());
    }
}
