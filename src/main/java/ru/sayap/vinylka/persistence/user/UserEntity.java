package ru.sayap.vinylka.persistence.user;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.order.OrderEntity;

import java.util.List;
import java.util.UUID;

//enum Role {
//    ADMIN, AUTHORIZED, NOT_AUTHORIZED
//}

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {

//    private static final String PHONE_REGEX = "^(?:\\+7|8)?\\s*\\(?\\d{3}\\)?\\s*\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$";
//    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PROTECTED)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    //@ElementCollection
    //@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole roles;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_sex")
    private UserSex userSex;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @OneToMany(mappedBy = "userId")
    @ToString.Exclude
    List<OrderEntity> orderEntity;
//
    @OneToOne(mappedBy = "userId")
    @ToString.Exclude
    CartEntity cartEntity;
//
//    @OneToMany(mappedBy = "userId")
//    @ToString.Exclude
//    List<OrderedVinylEntity> orderedVinylEntity;

    public enum UserSex {
        MAN, WOMAN
    }

    public enum UserRole {
        ADMIN, UNAUTHORIZED, USER
    }

    public enum UserStatus {
        ONLINE, OFFLINE
    }
}

