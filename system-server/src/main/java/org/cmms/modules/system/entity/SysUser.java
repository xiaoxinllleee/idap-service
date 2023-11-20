package org.cmms.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 登录账号
     */
    @Excel(name = "登录账号", width = 15)
    private String username;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", width = 15)
    private String realname;


    /**
     * 员工类型
     */
    @Excel(name = "员工类型", width = 15,dicCode = "yglx")
    @Dict(dicCode = "yglx")
    private String yglx;

    /**
     * 员工状态
     */
    @Excel(name = "员工状态", width = 15)
    @Dict(dicCode = "ygzt")
    private String ygzt;


    /**
     * 柜员号"
     */
    @Excel(name = "柜员号", width = 15)
    private String gyh;


    /**
     * 客户经理编号"
     */
    @Excel(name = "客户经理编号", width = 15)
    private String khjlbh;


    /**
     * 性别（1：男 2：女）
     */
    @Excel(name = "性别", width = 15,dicCode="sex")
    @Dict(dicCode = "sex")
    private Integer sex;

    /**
     * 电子邮件
     */
    @Excel(name = "电子邮件", width = 15)
    private String email;


    /**
     * 电话
     */
    @Excel(name = "电话", width = 15)
    private String phone;

    /**
     * 角色
     */
    @Excel(name="角色",width = 15,dictTable ="sys_role",dicText = "role_name",dicCode = "id")
    @Dict(dictTable ="sys_role",dicText = "role_name",dicCode = "id")
    private String roles;




    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * md5密码盐
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    /**
     * 头像
     */
    @Excel(name = "头像", width = 15)
    private String avatar;

    /**
     * 生日
     */
    @Excel(name = "生日", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;




    /**
     * 部门code
     */
    @Excel(name="负责部门",width = 15,dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    @Dict(dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    private String orgCode;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    @Excel(name = "状态", width = 15,dicCode="user_status")
    @Dict(dicCode = "user_status")
    private Integer status;

    /**
     * 删除状态（0，正常，1已删除）
     */
    @Excel(name = "删除状态", width = 15,dicCode="del_flag")
    @TableLogic
    private String delFlag;

    /**
     * 工号，唯一键
     */
    @Excel(name = "工号", width = 15)
    private String workNo;

    /**
     * 职务，关联职务表
     */
    @Excel(name = "职务", width = 15)
    private String post;


    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     *
     */

    /**
     * 更新密码的时间
     */
    private Date updatepasswordtime;
    /**
     * 同步工作流引擎1同步0不同步
     */
    private String activitiSync;

    /**
     * 身份（0 普通成员 1 上级）
     */
    @Excel(name="（1普通成员 2上级）",width = 15)
    private Integer identity;

    /**
     * 负责部门
     */
    @Excel(name="负责部门",width = 15,dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    @Dict(dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    private String departIds;

    /**
     * 密码释放时间
     */
    private String pwdFreeTm;

    /**
     * 密码第一次输错时间
     */
    private String pwdErrTm;

    /**
     * 密码输错次数
     */
    private Integer pwdErrNum;



    /**
     * PC启用标识（1，启用，1不启用）
     */
    private Integer pcFlag;



    /**
     * PC启用标识（1，启用，1不启用）
     */
    private Integer appFlag;

    @TableField(exist=false)
    private String  evlObjId;
}
