# Product описание проекта

Веб-приложение представляет собой бэкенд для магазина продуктов.

Тестовый сервис доступен по адресу: https://inlaid-backbone-404620.oa.r.appspot.com:443

Swagger: https://inlaid-backbone-404620.oa.r.appspot.com/swagger-ui/index.html#/

-----------------------------------------------------------------------------------------------------------------------------
Клиентская часть для покупателя это мобильное приложение
https://docs.google.com/document/d/1V4UYfS_FoE7TKZPsxRnOKgmIUOVC6PAPfckyHo0jjow/edit

Методы доступны для авторизованного пользователя.
Тестовый профиль:
email: user@mail.ru   
password: user
- Добавить продукт в корзину в количестве 1 штуки.
- Убрать продукт из корзины в количестве 1 штуки.
- Получить все продукты в корзине пользователя с общей ценой.
- Добавление заказа. После добавления продуктов в заказ корзина пользователя очищается.
-----------------------------------------------------------------------------------------------------------------------------
Часть для админки продавца доступна для пользователя с ролью администратор. 
https://docs.google.com/document/d/1LKvCVMKkwgnhYm18X58gwe39EMCXJaVhlxNKXYNVSfM/edit

Методы доступны авторизованному пользователю с ролью "Сотрудник"(staff).
Тестовый профиль:
email: staff@mail.ru 
password: staff

Управления наличием (складом):

- Получение списка всех категорий.
- Получение категории по id.
- Добавление новой категории. Осуществляется проверка на существование категории с таким же наименованием.
- Изменение категории. Осуществляется проверка на существование категории с таким же наименованием.
- Удаление категории. Осуществляется проверка на наличие продуктов в категории.
- Получение всех продуктов в категории.
- Получение продукта по id.
- Добавление нового продукта. Осуществляется проверка на существование продукта с таким же наименованием.
- Изменение продукта, в т.ч. количества на складе. Осуществляется проверка на существование продукта с таким же наименованием.
- Удаление продукта.


Работа с заказами:
- Получение списка всех заказов.
- Получение заказов по id.
- Изменение количества продуктов в заказе.
- Добавление продукта в заказ.
- Удаление продукта из заказа.
- Изменение статуса заказа. при изменении статуса с "Собран" на "Доставлен" происходит уменьшение количества продукта на складе.

Пользователю с ролью "Admin" доступны все методы.
Тестовый профиль:
email: admin@mail.ru
password: admin
-----------------------------------------------------------------------------------------------------------------------------
Для доступа к документации Swagger запустить проект локально и перейти по адресу: http://localhost:8080/swagger-ui/index.html#/ 

Запустить скомпилированный проект из командной строки: java -jar C:\путь к папке\target\Product-0.0.1-SNAPSHOT.jar
