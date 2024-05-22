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
@Table(name = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cardNumber;
    int status;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    ConsumerEntity consumer;

    @OneToOne(mappedBy = "cardsId")
    @ToString.Exclude
    UserEntity user;

    @PrePersist
    public void assignDefaultValue(){
        status = 0; //if status = 0 consumer needs to activate, and it should be 1 to be activated
    }

}
