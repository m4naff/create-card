package com.example.msuser.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.FetchType.LAZY;
import static java.lang.reflect.Modifier.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles_authorities")
public class RoleAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_id")
    RoleEntity role;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "authority_id")
    AuthorityEntity authority;
}