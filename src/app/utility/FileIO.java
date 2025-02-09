package app.utility;
import java.io.*;

public class FileIO {

    public static Serializable[] read(String filename) {

        int objectCount = countObjectsInFile(filename); // Количество объектов в файле

        Serializable[] array = new Serializable[objectCount]; // Массив сериализуемых объектов величиной равной количеству объектов в файле
        int index = 0; // индекс для записи объектов

        // Чтение объектов из файла и запись их в массив
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                //Если строка не пустая, добавляем ее к буферу StringBuilder
                if (!line.trim().isEmpty()) {
                    if (line.trim().contains(":")) {
                        stringBuilder.append(line.substring(line.indexOf(":") + 1).trim()).append("\n");
                    } else {
                        stringBuilder.append(line).append("\n");
                    }
                } else {
                    /*
                    Если строка пустая, значит это конец одного объекта в файле.
                    Десериализуем строку в объект.
                    Записываем объект в массив.
                     */
                    array[index++] = ObjectSerializer.deserialize(stringBuilder.toString().trim().split("\n"));
                    stringBuilder.setLength(0); // Очищаем буфер строки под следующий объект
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения объектов из файла: " + e.getMessage());
            return null;
        }

        return array;
    }

    //Считает количество объектов в файле по количеству пустых строк
    private static int countObjectsInFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int objectCount = 0;
            boolean prevLineEmpty = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() && !prevLineEmpty) {
                    objectCount++;
                }
                prevLineEmpty = line.trim().isEmpty();
            }

            return objectCount;

        } catch (IOException e) {
            System.err.println("Ошибка подсчёта количества объектов в файле: " + e.getMessage());
        }

        return 0;
    }

    // Запись в файл
    public static void write(String filename, Serializable[] objects) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (Serializable obj : objects) {
                String strObj = obj.toString().replace(",", "\n");
                writer.write(String.format("%s%n", strObj));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи объектов в файл: " + e.getMessage());
        }
    }
}