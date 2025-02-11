package app.validation;

public final class Validator {

    private Validator(){};

    public static String validateString(String string)
    {
        return !string.matches("\\s*") ? string : null;
    }

    public static Double validateDouble(String string){
        return (string.matches(("\\d+(\\.\\d+)?")) && !string.matches("\\s*"))
                ? Double.parseDouble(string) : null;
    }

    public static Integer validateInteger(String string){
        return (string.matches(("\\d+")) && !string.matches("\\s*"))
                ? Integer.parseInt(string) : null;
    }
}
