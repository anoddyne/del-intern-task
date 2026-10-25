package ru.anoddyne.del_intern_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anoddyne.del_intern_task.model.ClientAccount;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
}
