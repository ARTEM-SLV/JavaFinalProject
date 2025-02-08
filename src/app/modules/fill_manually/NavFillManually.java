package app.modules.fill_manually;

import app.modules.fill_manually.navigation.Navigation;
import java.util.Map;

public class NavFillManually {

    private final Navigation<Integer, String> nav = new Navigation.Builder<Integer, String>()
            .firstMessage("Введите длину массива: ")
            .optionsMessage("""
            Какой тип данных будем использовать?
            1 - Автомобиль
            2 - Книга
            3 - Корнеплод
            """)
            .options(Map.of(
            1, "Паттерн Автомобиль: Мощность | Модель | Год производства",
            2, "Паттерн Книга: Автор | Название | Количество страниц",
            3, "Паттерн Корнеплод: Тип | Вес | Цвет"
            ))
            .build();

    public Navigation<Integer, String> getNav() {
        return this.nav;
    }
}
