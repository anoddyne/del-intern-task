package ru.anoddyne.del_intern_task.exception;


public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Клиент с ID " + id + " не найден.");
    }
}
