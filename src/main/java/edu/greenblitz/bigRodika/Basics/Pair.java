package edu.greenblitz.bigRodika.Basics;

public class Pair<T, S> {
    private T first;
    private S second;

    public Pair(T t, S s) {
        this.first = t;
        this.second = s;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "t=" + first.toString() +
                ", s=" + second.toString() +
                '}';
    }
}
