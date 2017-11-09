package com.wanari.customlogin.example.config.security.constant;

public final class Roles {

    public static final String DETONATOR_ROLE = "DETONATOR";

    public static final String DETONATOR = "hasAuthority('" + DETONATOR_ROLE + "')";

    public static final String USER_ROLE = "USER";

    public static final String USER = "hasAuthority('" + USER_ROLE + "')";

    private Roles() {
    }
}
