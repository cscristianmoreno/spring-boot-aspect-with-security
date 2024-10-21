package com.security.spring_app.reuse;

import com.security.spring_app.entity.Roles;
import com.security.spring_app.enums.RolesTypes;

public abstract class RolesTest {
    
    public static Roles getRole() {
        Roles roles = new Roles();
        roles.setId(1);
        roles.setName(RolesTypes.USER);
        return roles;
    }
}
