package org.cmms.modules.system.entity;

import lombok.Data;

/**
 * @Date 2022/4/26
 * @Created by eran
 */
@Data
public class UpdatePassWordVO {
    private String username;
    private String oldPasswordEncrypt;
    private String passwordEncrypt;
    private String confirmPasswordEncrypt;
}
