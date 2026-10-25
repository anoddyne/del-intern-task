package ru.anoddyne.del_intern_task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.anoddyne.del_intern_task.dto.ClientRequestDto;
import ru.anoddyne.del_intern_task.dto.ClientResponseDto;
import ru.anoddyne.del_intern_task.model.ClientAccount;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "modifyDttm", ignore = true)
    ClientAccount toEntity(ClientRequestDto requestDto);

    ClientResponseDto toResponseDto(ClientAccount clientAccount);

    List<ClientResponseDto> toResponseDtoList(List<ClientAccount> clientAccountList);

    void updateEntity(ClientRequestDto dto, @MappingTarget ClientAccount entity);
}
