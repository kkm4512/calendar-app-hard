package org.terror.calendarapphard.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public static class Authority {
        public static String ADMIN = "ROLE_ADMIN";
        public static String USER = "ROLE_USER";
    }
}
