package workshop2.service;

public interface ServiceMessage {
    String MESSAGE_CONNECT = "***Соединение с сервером установлено***";
    String MESSAGE_NO_CONNECT = "***Сервер недоступен***";
    String MESSAGE_DISCONNECT = "***Соединение с сервером завершено***";

    String MESSAGE_CONNECTION = "Подключен пользователь...";
    String MESSAGE_NO_CONNECTION = "Отключен пользователь...";
    String MESSAGE_ON = "Сервер запущен";
    String MESSAGE_ALREADY_RUN = "Сервер уже запущен";
    String MESSAGE_OFF = "Сервер остановлен";
    String MESSAGE_ALREADY_OFF = "Сервер уже остановлен";
    String MESSAGE_NOT_FILE = "Отсутствует лог-файл сервера!!!";
    String MESSAGE_NEW_FILE = "Создан новый лог-файл сервера!!!";
}
