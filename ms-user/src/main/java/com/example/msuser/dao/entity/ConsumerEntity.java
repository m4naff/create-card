package com.example.msuser.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consumers_roles",
    joinColumns = @JoinColumn(name = "consumer_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles;

    @OneToOne
    @JoinColumn(name = "cards_id")
    @JsonIgnore //to stop infinite recursion
    CardEntity cardsId;

    @OneToOne(mappedBy = "consumerId")
    OrderEntity order;

    @PrePersist
    public void autoSignDefaultValues(){
        status = 1;
    }

}
