package com.inredec.ATutor_Backend.payload;

public class UserAuth {
    private Boolean authorized;

    public UserAuth(Boolean authorized) {
        this.authorized = authorized;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }
}
