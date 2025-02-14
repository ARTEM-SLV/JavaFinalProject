package app.utility;

import java.io.*;

public class FileIO {

    /**
     * Чтение из файла
     *
     * @param filename - имя файла включая расширение
     * @return массив объектов реализующих Serializable
     */
    public static Serializable[] read(String filename) {

        replaceMultipleNewlines(filename);

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
            // Десериализовать последний объект если конец файла не пустая строка
            if (!stringBuilder.isEmpty()) {
                array[index] = ObjectSerializer.deserialize(stringBuilder.toString().trim().split("\n"));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения объектов из файла: " + e.getMessage());
            return null;
        }

        return array;
    }

    //Считает количество объектов в файле
    private static int countObjectsInFile(String filename) {

        int objectCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase("car") || line.trim().equalsIgnoreCase("book") || line.trim().equalsIgnoreCase("vegetable")) {
                    objectCount++;
                }
            }

            return objectCount;

        } catch (IOException e) {
            System.err.println("Ошибка подсчёта количества объектов в файле: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Запись в файл.
     *
     * @param filename        - имя файла включая расширение
     * @param AddOrUpdateFile - True: добавить к файлу. False: заменить файл полностью.
     * @param objects         объект реализующий интерфейс Serializable или массив таких объектов
     */
    public static void write(String filename, boolean AddOrUpdateFile, Serializable... objects) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, AddOrUpdateFile))) {
            writer.newLine();
            writer.newLine();

            for (Serializable obj : objects) {
                String strObj = obj.toString().replace(",", "\n").replace("\n ", "\n");
                writer.write(String.format("%s%n", strObj));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи объектов в файл: " + e.getMessage());
        }
    }

    //Замена множественных пустых строк на одну
    private static void replaceMultipleNewlines(String filename) {
        // Чтение из файла
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(sb.toString().trim().replaceAll("(\\r?\\n){2,}", "\n\n"));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidFilename(String filename) {
        return filename.matches("^[\\w,\\s-]+\\.[A-Za-z]{3}$");
    }
}