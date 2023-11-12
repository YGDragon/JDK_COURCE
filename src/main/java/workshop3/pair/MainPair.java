package workshop3.pair;

public class MainPair {
    public static void main(String[] args) {
        Pair pair = new Pair<>("ключ", 200);
        System.out.println("первый элемент -> " + pair.getFirst());
        System.out.println("второй элемент -> " + pair.getSecond());
        System.out.println(pair);
    }
}
