package app.modules.fill_manually;

import app.modules.fill_manually.navigation.Navigation;
import java.util.Map;

public class NavFillManually {

    private final Navigation<Integer, String> nav = new Navigation.Builder<Integer, String>()
            .firstMessage("Введите длину массива: ")
            .optionsMessage("""
            Какой тип данных будем использовать?
            1 - Car
            2 - Book
            3 - Root vegetable
            """)
            .options(Map.of(
            1, "Паттерн Car: model power value",
            2, "Паттерн Book: name author pages",
            3, "Паттерн Vegetable: kek lol beb"
            ))
            .build();

    public Navigation<Integer, String> getNav() {
        return this.nav;
    }
}
