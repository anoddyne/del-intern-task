package ru.anoddyne.del_intern_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anoddyne.del_intern_task.model.ClientAccount;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
}
