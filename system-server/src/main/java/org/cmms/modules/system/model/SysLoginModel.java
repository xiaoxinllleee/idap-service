package org.cmms.modules.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 *
 * @Author scott
 * @since  2019-01-18
 */
@ApiModel(value="登录对象", description="登录对象")
@Data
public class SysLoginModel {
	@ApiModelProperty(value = "账号")
    private String username;
	@ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "加密后的账号")
    private String usernameEncrypt;
    @ApiModelProperty(value = "加密后的密码")
    private String passwordEncrypt;
	@ApiModelProperty(value = "验证码")
    private String captcha;
	@ApiModelProperty(value = "验证码key")
    private String checkKey;
    @ApiModelProperty(value = "设备类型")
    private String equipment;
}