package org.cmms.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Data
public class SysUserExp {

    @Excel(name = "工号/账号", width = 15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String username;

    @Excel(name = "员工姓名", width = 15)
    @ExcelVerify(notNull = true)
    private String realname;

    @Excel(name="角色",width = 15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String roles;

    @Excel(name="权限组织(简称即可)",width = 15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String orgCode;

    @Excel(name = "电话(非必填)", width = 15)
    private String phone;

    @Excel(name = "密码(不填写默认123456)", width = 15)
    private String password;



}
