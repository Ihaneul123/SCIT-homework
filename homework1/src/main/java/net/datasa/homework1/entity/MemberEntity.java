package net.datasa.homework1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @Column(name = "id", nullable = false, length = 30)
    String id;

    @Column(name = "pw", nullable = false)
    String pw;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "phone", nullable = false)
    String phone;
}
