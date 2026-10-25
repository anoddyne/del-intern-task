package ru.anoddyne.del_intern_task.dto;

import jakarta.validation.constraints.NotBlank;
import ru.anoddyne.del_intern_task.model.enums.Gender;

public record ClientRequestDto (
    @NotBlank(message = "Заполните поле ФИО.")
    String fullName,

    Gender gender
) {}
