# ApplicationCRUD

**ApplicationCrud** - это консольное CRUD приложение, которое взаимодействует с базой данных.

Пользователь имеет возможность выполнять все CRUD операции (create, read, update, delete).

### Взаимодействие происходит со следующими сущностями:  
>*- Writer (id, name, List<Post<Post>> posts);*  
*- Post (id, content, List<<Tag>Tag> tags, PostStatus status);*   
*- Tag (id, name);*  
*- PostStatus (enum ACTIVE, DELETED).*

### Приложение соответствует требованиям: 
:white_check_mark: Шаблон __MVC__ (пакеты: model, repository, service, controller, view)    
:white_check_mark: Для миграции БД используется __Liquibase__    
:white_check_mark: Сервисный слой покрыт юнит тестами (__JUnit + Mockito__)   
:white_check_mark: Для импорта библиотек используется __Maven__

**Общий стек технологий проекта:** _Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito_.    

***Пример из консоли:***    

|Начало|Продолжение|
|:----:|:----:|
|![](screenshots/scrin_from_consol-1.jpg) |![](screenshots/scrin_from_consol-2.jpg)|

### Инструкции для запуска приложения:

| № | Этапы выполнения |
|:----:|:----|
| 1 | [Скопируйте код на свой ПК](https://github.com/itbatia/ApplicationCRUD/archive/refs/heads/master.zip)|
| 2 | Извлеките содержимое архива. В указанном месте появится папка ApplicationCRUD-master. |
| 3 | Для локального использования приложения необходимо внести логин, пароль и URL от своей БД MySQL.     Для этого измените файл pom.xml указав в теге <<properties>properties> свои <database.url>, <username<username>> и <password<password>>.     Соответствующие изменения внесите в файл src\main\java\com\itbatia\crud\utils\DatabaseConnection.java в поля DATABASE_URL, USER и PASSWORD.|
| 4 | Зайдите в папку ApplicationCRUD-master и в адресной строке пропишите: cmd |
|   | ![](screenshots/screen1.jpg)|
| 5 | Откроется командная строка, в которой необходимо прописать команду: mvn package. Результатом её выполнения будет примерно следующее:|
|   | ![](screenshots/screen2.jpg)|
| 6 | Запустите миграцию БД: mvn liquibase:update |
|   | ![](screenshots/screen3.jpg)||
| 7 | Затем пропишите команду: mvn exec:java -Dexec.mainClass="com.itbatia.crud.Main" |
|   | ![](screenshots/screen4.jpg)|
| :triangular_flag_on_post: | Программа запущена и готова к использованию! |

*Для последующих запусков выполняйте только пункты № 4 и 7.*