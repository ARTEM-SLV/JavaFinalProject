package app.input;

import app.utility.FileIO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Random;

public abstract class AbstractRandomGenerator<T> implements RandomGenerator<T> {

    protected final Random random = new Random();
    private final String filename;
    private final Class<T> type;

    protected AbstractRandomGenerator(String filename, Class<T> type) {
        this.filename = filename;
        this.type = type;
    }


    @Override
    public T generateRandom() {
        Serializable[] items = FileIO.read(filename);
        if (items == null || items.length == 0) {
            throw new IllegalStateException("No items found in file: " + filename);
        }
        return (T) items[random.nextInt(items.length)];
    }


    @Override
    public T[] generateRandom(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }

        Serializable[] items = FileIO.read(filename);
        if (items == null || items.length == 0) {
            throw new IllegalStateException("No items found in file: " + filename);
        }

        T[] result = (T[]) Array.newInstance(type, count);
        for (int i = 0; i < count; i++) {
            result[i] = (T) items[random.nextInt(items.length)];
        }
        return result;
    }
}
