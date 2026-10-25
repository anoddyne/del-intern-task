package ru.anoddyne.del_intern_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anoddyne.del_intern_task.dto.ClientRequestDto;
import ru.anoddyne.del_intern_task.dto.ClientResponseDto;
import ru.anoddyne.del_intern_task.exception.ClientNotFoundException;
import ru.anoddyne.del_intern_task.mapper.ClientMapper;
import ru.anoddyne.del_intern_task.model.ClientAccount;
import ru.anoddyne.del_intern_task.model.enums.ClientStatus;
import ru.anoddyne.del_intern_task.repository.ClientAccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientAccountRepository clientAccountRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientResponseDto create(ClientRequestDto requestDto) {
        ClientAccount account = clientMapper.toEntity(requestDto);
        account.setStatus(ClientStatus.ACTIVE);
        ClientAccount saved = clientAccountRepository.save(account);
        return clientMapper.toResponseDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        ClientAccount account = getClientOrThrow(id);
        clientAccountRepository.delete(account);
    }

    @Transactional
    public void update(Long id, ClientRequestDto updateDto) {
        ClientAccount account = getClientOrThrow(id);
        clientMapper.updateEntity(updateDto, account);
    }

    @Transactional(readOnly = true)
    public ClientResponseDto view (Long id) {
        ClientAccount account = getClientOrThrow(id);
        return clientMapper.toResponseDto(account);
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDto> showAll() {
        return clientMapper.toResponseDtoList(clientAccountRepository.findAll());
    }

    private ClientAccount getClientOrThrow(Long id) {
        return clientAccountRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }
}
