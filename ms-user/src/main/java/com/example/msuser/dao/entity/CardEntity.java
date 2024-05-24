package com.example.msuser.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(mappedBy = "cardsId")
    @JsonIgnore
    ConsumerEntity consumer;

    @OneToOne(mappedBy = "cardsId")
    @ToString.Exclude
    @JsonIgnore
    UserEntity user;

    @OneToOne(mappedBy = "cardId")
    OrderEntity order;

    @PrePersist
    public void assignDefaultValue(){
        status = 0; //if status = 0 consumer needs to activate, and it should be 1 to be activated
    }

}
