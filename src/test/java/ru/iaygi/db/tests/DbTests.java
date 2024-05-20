package ru.iaygi.db.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.*;
import ru.iaygi.db.data.Sql;
import ru.iaygi.db.objects.DbMethods;
import ru.iaygi.db.service.DbConnect;
import ru.iaygi.dto.UserDTO;

import java.sql.ResultSet;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static ru.iaygi.db.data.Sql.GET_CUSTOMERS;

@Severity(CRITICAL)
@Tag("db_test")
@Tag("smoke")
@Epic("Users")
@Feature("Работа с пользователями через БД")
public class DbTests extends DbConnect {

    private ResultSet resultSet;
    private DbMethods dbMethods = new DbMethods();
    private List<UserDTO> list;

    @BeforeEach
    void prepare() {

    }

    @AfterEach
    void cleanup() {
        connectClose();
    }


    @Test
    @DisplayName("Получение списка покупателей из БД")
    @Description("Проверить корректное получение списка покупателей из БД")
    void getAllUsers() {
        int id = 1;
        String name = "Иван Петров";
        String email = "petrov@mail.ru";

        step("Сделать SQL запрос на получение покупателей", () -> {
            resultSet = getRequest(GET_CUSTOMERS);
        });

        step("Получить коллекцию покупателей", () -> {
            list = dbMethods.getCustomers(resultSet);
        });

        step("Проверить покупателя №1", () -> {
            assertThat(list).extracting("id", "name", "email")
                    .contains(tuple(id, name, email));
        });
    }
}
