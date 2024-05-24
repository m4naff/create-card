package com.example.msuser.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int status;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    @JsonIgnore
    ConsumerEntity consumerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    UserEntity userId;

    @OneToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    CardEntity cardId;

    @PrePersist
    public void signDefaultValue(){
        status = 0; //if it is zero order has created but not card if it is 1 it means that card has been created
    }
}
