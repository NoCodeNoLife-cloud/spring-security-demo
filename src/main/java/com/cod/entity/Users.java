package com.cod.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * users
 * @author admin
 */
@Data
public class Users implements Serializable {
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * enabled
     */
    private Boolean enabled;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}