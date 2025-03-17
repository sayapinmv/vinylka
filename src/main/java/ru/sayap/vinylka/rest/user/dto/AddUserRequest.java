package ru.sayap.vinylka.rest.user.dto;

import ru.sayap.vinylka.persistence.user.UserEntity;

public record AddUserRequest(
        String fullName,
        UserEntity.UserRole role,
        String email,
        String phoneNumber,
        String password,
        UserEntity.UserSex sex,
        UserEntity.UserStatus status
) {}
