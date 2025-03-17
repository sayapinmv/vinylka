package ru.sayap.vinylka.service.user.vo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserVo {

    @Setter(AccessLevel.PROTECTED)
    UUID id;

    String fullName;

    @Enumerated(EnumType.STRING)
    UserEntity.UserRole roles;

    String phoneNumber;

    String email;

    String password;

    @Enumerated(EnumType.STRING)
    UserEntity.UserSex sex;

    @Enumerated(EnumType.STRING)
    UserEntity.UserStatus status;

}
