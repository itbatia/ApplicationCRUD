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


**Общий стек технологий проекта:** _Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito_    

***Пример из консоли:***    

|Начало|Продолжение|
|:----:|:----:|
|![](screenshots/scrin_from_consol-1.jpg) |![](screenshots/scrin_from_consol-2.jpg)|

### Инструкции для запуска приложения:

| № | Этапы выполнения |
|:----:|:----|
| 1 | [Скопируйте код на свой ПК](https://github.com/itbatia/ApplicationCRUD/archive/refs/heads/master.zip)|
| 2 | Извлеките содержимое архива. В указанном месте появится папка ApplicationCRUD-master. |
| 3 | Зайдите в папку ApplicationCRUD-master и в адресной строке пропишите: cmd |
|   | ![](screenshots/scr2.jpg)|
| 4 | Откроется командная строка, в которой необходимо прописать команду: mvn package. Результатом её выполнения будет примерно следующее:|
|   | ![](screenshots/scr3.jpg)|
| 5 | Затем пропишите команду: mvn exec:java -Dexec.mainClass="com.itbatia.crud.Main" |
|   | ![](screenshots/scr4.jpg)|
|   | Программа запущена и готова к использованию! |