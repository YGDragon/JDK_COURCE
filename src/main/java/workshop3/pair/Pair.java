package workshop3.pair;

public class Pair <T, S>  {
    T first;
    S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", first, second);
    }
}
