package ru.anoddyne.del_intern_task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.anoddyne.del_intern_task.model.enums.ClientStatus;
import ru.anoddyne.del_intern_task.model.enums.Gender;

import java.time.Instant;

@Entity
@Table(name = "client_account")
@Getter
@Setter
public class ClientAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "create_dttm", nullable = false)
    @CreationTimestamp
    private Instant createDttm;

    @Column(name = "modify_dttm", nullable = false)
    @UpdateTimestamp
    private Instant modifyDttm;
}
