# Lab6
Параллельное программирование
Небольшой пример работы на сокетах

### Сборка и запуск

Lab6 требует наличие [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) для запуска и [Maven](https://maven.apache.org/download.cgi) для сборки.

Сборка *.jar файла.

```sh
$ cd Chat
$ mvn package
```
В `target/` папке ты найдёшь *.jar файл.  
Для запуска 1-ого клиента выполни:  
```sh
java -jar Chat-1.0-SNAPSHOT.jar
```
Который по умолчанию создаст сокет-сервер на порту 9090, а сокет клиента на 127.0.0.1:8080
Для запуска 2-ого клиента выполни:  
```sh
java -jar Chat-1.0-SNAPSHOT.jar -P 8080 -p 9090
```
Что бы подключиться к 1-му клиенту.  
А так же доступен к изменению параметр адреса клиента `-h`
```sh
java -jar Chat-1.0-SNAPSHOT.jar -P 8080 -p 9090 -h 192.168.0.1
```
