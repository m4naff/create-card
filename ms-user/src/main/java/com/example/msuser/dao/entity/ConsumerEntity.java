package com.example.msuser.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "consumers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int status;

    @ManyToMany
    @JoinTable(name = "consumers_roles",
    joinColumns = @JoinColumn(name = "consumer_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles;

    @OneToMany(mappedBy = "consumer",cascade = CascadeType.ALL)
    List<CardEntity> cardsId;

    @PrePersist
    public void autoSignDefaultValues(){
        status = 1;
    }

}
