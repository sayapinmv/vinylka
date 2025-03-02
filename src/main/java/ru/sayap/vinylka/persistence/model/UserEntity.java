package ru.sayap.vinylka.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

//enum Role {
//    ADMIN, AUTHORIZED, NOT_AUTHORIZED
//}

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

//    private static final String PHONE_REGEX = "^(?:\\+7|8)?\\s*\\(?\\d{3}\\)?\\s*\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$";
//    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    @Id
    @GeneratedValue
    UUID id;

    @Column(name = "full_name")
    String fullName;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_sex")
    Sex userSex;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    UserStatus status;

    @OneToMany(mappedBy = "userId")
    List<OrderEntity> orderEntity;

    @OneToMany(mappedBy = "userId")
    List<CartEntity> cartEntity;

    @OneToMany(mappedBy = "userId")
    List<OrderedVinylEntity> orderedVinylEntity;

    public enum Sex {
        MAN, WOMAN
    }

    public enum UserStatus {
        ONLINE, OFFLINE
    }
}

