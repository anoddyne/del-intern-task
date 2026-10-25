package ru.anoddyne.del_intern_task.dto;

import ru.anoddyne.del_intern_task.model.enums.ClientStatus;
import ru.anoddyne.del_intern_task.model.enums.Gender;

import java.time.Instant;

public record ClientResponseDto(
        Long id,
        String fullName,
        Gender gender,
        ClientStatus status,
        Instant createDttm,
        Instant modifyDttm
) { }