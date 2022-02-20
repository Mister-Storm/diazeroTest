package com.diazero.incidentsapi.infra.security.user;

import java.util.Arrays;

public enum UserRoles {

    ADMIN(1, "ROLE_ADMIN"),
    NORMAL(2, "ROLE_NORMAL");

    private int code;
    private String role;

    UserRoles(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UserRoles toEnum(Integer code) {
        if(code==null) {
            return null;
        }
        return Arrays.stream(UserRoles.values())
                .filter(c -> code.equals(c.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Role %s does not exists", code)));
    }
}
