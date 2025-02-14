package app.utility.random;


public interface RandomGenerator<T> {
    T generateRandom();
    T[] generateRandom(int count);
}
