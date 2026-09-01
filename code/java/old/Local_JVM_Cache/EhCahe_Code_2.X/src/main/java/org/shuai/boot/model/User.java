package org.shuai.boot.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends EhCacheBaseObject{

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String email;

    private String phone;

    // toString
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
