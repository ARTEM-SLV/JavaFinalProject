package app.input;


public interface RandomGenerator<T> {
    T generateRandom();
    T[] generateRandom(int count);
}
