package org.example.msnotifications.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor(force = true)
@Builder
@Entity
@Table(name = "telegram_notifications")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long consumerId;
    String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
