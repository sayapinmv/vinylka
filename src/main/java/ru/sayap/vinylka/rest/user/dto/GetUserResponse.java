package ru.sayap.vinylka.rest.user.dto;

import ru.sayap.vinylka.persistence.user.UserEntity;

public record GetUserResponse(
        String fullName,
        UserEntity.UserRole role,
        String email,
        String phoneNumber,
        UserEntity.UserSex sex,
        UserEntity.UserStatus status
) {
}
