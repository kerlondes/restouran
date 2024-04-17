package com.example.restoanchik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import com.example.restoanchik.data.database.GetTableDatabase;
import com.example.restoanchik.data.model.RestaurantModel;
import com.example.restoanchik.data.repository.DatabaseRepository;

public class MainActivity extends AppCompatActivity {
    private DatabaseRepository databaseRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем или получаем уже созданный singleton-экземпляр базы данных
        GetTableDatabase database = GetTableDatabase.getInstance(this);

        // Инициализируем экземпляр DatabaseRepository с реальным экземпляром БД
        databaseRepository = new DatabaseRepository(database);

        // Если вам нужно очистить все данные перед добавлением новых,
        // вызовем метод deleteAll() для очистки базы данных
        databaseRepository.deleteAllAsync();

        // Теперь можно добавить mock данные в БД
        addMockRestaurantsToDB();
    }
    private void addMockRestaurantsToDB() {
        databaseRepository.insertRestaurantsAsync(
                new RestaurantModel(
                        1,
                        "Four",
                        "four",
                        "Мира, 45б Four Elements Perm, Пермь 614022 Россия",
                        "12:00–23:59",
                        "Итальянская, Морепродукты, Средиземноморская, Европейская, Русская, Центральноевропейская, Международная"
                ),
                new RestaurantModel(
                        2,
                        "Ле Марш",
                        "lemarch",
                        "Газета Звезда ул., д. 27 (3 этаж) , Пермь 614000 Россия",
                        "00:00–12:00",
                        "Морепродукты, Европейская, Восточноевропейская, Французская, Средиземноморская"
                ),
                new RestaurantModel(
                        3,
                        "Наири",
                        "nairi",
                        "Советская, 67, Пермь 614000 Россия",
                        "09:00–00:00",
                        "Бельгийская, Русская, Восточноевропейская, Европейская"
                ),
                new RestaurantModel(
                        4,
                        "La Bottega.VS",
                        "labottega",
                        " ул. Советская, 62, Пермь 614000 Россия",
                        "00:00–23:00",
                        "Европейская, Итальянская, Средиземноморская"
                ),
                new RestaurantModel(
                        5,
                        "Old Moose",
                        "old_moose",
                        "Комсомольский Проспект, 14, Пермь 614000 Россия",
                        "16:00–01:00",
                        "Бар, Паб, Пивные рестораны"
                )
        );
    }
}