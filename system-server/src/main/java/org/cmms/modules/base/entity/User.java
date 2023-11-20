package org.cmms.modules.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.cmms.base.XbootBaseEntity;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.utils.NameUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="sys_user")
@TableName("sys_user")
@ApiModel(value = "用户")
public class User extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "登录名")
    @Column(unique = true, nullable = false)
    @Pattern(regexp = NameUtil.regUsername, message = "登录账号不能包含特殊字符且长度不能>16")
    private String username;


    @ApiModelProperty(value = "用户名/昵称/姓名")
    @NotNull(message = "不能为空")
    @Size(max = 20, message = "昵称长度不能超过20")
    private String realname;


    @ApiModelProperty(value = "密码")
    @NotNull(message = "不能为空")
    private String password;

    /**
     * md5密码盐
     */
    @ApiModelProperty(value = "md5密码盐")
    private String salt;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 部门code
     */
    @ApiModelProperty(value = "部门code")
    private String orgCode;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    @ApiModelProperty(value = "状态")
    private Integer status;



    /**
     * 工号，唯一键
     */
    @ApiModelProperty(value = "工号")
    private String workNo;

    /**
     * 职务，关联职务表
     */
    @ApiModelProperty(value = "职务")
    private String post;

    /**
     * 座机号
     */
    @ApiModelProperty(value = "座机号")
    private String telephone;


    /**
     * 同步工作流引擎1同步0不同步
     */
    @ApiModelProperty(value = "activitiSync")
    private String activitiSync;

    /**
     * 身份（0 普通成员 1 上级）
     */
    @ApiModelProperty(value = "身份")
    private Integer identity;

    /**
     * 负责部门
     */
    @ApiModelProperty(value = "负责部门")
    private String departIds;

}
