package com.example.demo.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_FULL_ACCESS("admin:full"),
    USER_READ("user:read")
    ;

    @Getter
    private final String permission;
}
