package org.cmms.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 用户信息
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
@Data
@TableName("SYS_BAS_USER")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SYS_BAS_USER对象", description="用户信息")
public class SysBasUser {

	/**md5密码盐*/
	@Excel(name = "md5密码盐", width = 15)
    @ApiModelProperty(value = "md5密码盐")
	private String salt;
	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
	private String avatar;
	/**生日*/
	@Excel(name = "生日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
	private Date birthday;
	/**性别(0-默认未知,1-男,2-女)*/
	@Excel(name = "性别(0-默认未知,1-男,2-女)", width = 15)
    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
	private Integer sex;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String orgCode;
	/**删除状态(0-正常,1-已删除)*/
	@Excel(name = "删除状态(0-正常,1-已删除)", width = 15)
    @ApiModelProperty(value = "删除状态(0-正常,1-已删除)")
	private Integer delFlag;
	/**座机号*/
	@Excel(name = "座机号", width = 15)
    @ApiModelProperty(value = "座机号")
	private String telephone;
	/**身份（1普通成员 2上级）*/
	@Excel(name = "身份（1普通成员 2上级）", width = 15)
    @ApiModelProperty(value = "身份（1普通成员 2上级）")
	private Integer identity;
	/**同步工作流引擎(1-同步,0-不同步)*/
	@Excel(name = "同步工作流引擎(1-同步,0-不同步)", width = 15)
    @ApiModelProperty(value = "同步工作流引擎(1-同步,0-不同步)")
	private Integer activitiSync;
	/**负责部门*/
	@Excel(name = "负责部门", width = 15)
    @ApiModelProperty(value = "负责部门")
	private String departIds;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
	private String post;
	/**密码释放时间*/
	@Excel(name = "密码释放时间", width = 15)
    @ApiModelProperty(value = "密码释放时间")
	private String pwdFreeTm;
	/**密码第一次输错时间*/
	@Excel(name = "密码第一次输错时间", width = 15)
    @ApiModelProperty(value = "密码第一次输错时间")
	private String pwdErrTm;
	/**密码输错次数*/
	@Excel(name = "密码输错次数", width = 15)
    @ApiModelProperty(value = "密码输错次数")
	private Integer pwdErrNum;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
    @ApiModelProperty(value = "用户ID")
	@TableId(type = IdType.ASSIGN_UUID)
	private String userid;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
	private String username;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
	private String password;
	/**真实姓名*/
	@Excel(name = "真实姓名", width = 15)
    @ApiModelProperty(value = "真实姓名")
	private String realname;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
	private String email;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String phone;
	/**状态1正常 2锁定 3删除 4停用 5作废*/
	@Excel(name = "状态1正常 2锁定 3删除 4停用 5作废", width = 15)
    @ApiModelProperty(value = "状态1正常 2锁定 3删除 4停用 5作废")
	private String status;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createuser;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createtime;
	/**岗位ID*/
	@Excel(name = "岗位ID", width = 15)
    @ApiModelProperty(value = "岗位ID")
	private Long postid;
	/**角色ID*/
	@Excel(name = "角色ID", width = 15)
    @ApiModelProperty(value = "角色ID")
	private Long roleid;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String tellid;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateuser;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updatetime;
	/**所属机构码*/
	@Excel(name = "所属机构码", width = 15)
    @ApiModelProperty(value = "所属机构码")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**岗位名称*/
	@Excel(name = "岗位名称", width = 15)
    @ApiModelProperty(value = "岗位名称")
	private String postname;
	/**角色名称*/
	@Excel(name = "角色名称", width = 15)
    @ApiModelProperty(value = "角色名称")
	private String rolename;
	/**权限机构代码，当有多个权限机构时，以逗号分隔*/
	@Excel(name = "权限机构代码，当有多个权限机构时，以逗号分隔", width = 15)
    @ApiModelProperty(value = "权限机构代码，当有多个权限机构时，以逗号分隔")
	private String qxjgdm;
	/**最后一次登录时间*/
	@Excel(name = "最后一次登录时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后一次登录时间")
	private Date lastlogintime;
	/**最后一次登录IP*/
	@Excel(name = "最后一次登录IP", width = 15)
    @ApiModelProperty(value = "最后一次登录IP")
	private String lastloginip;
	/**最后一次登录PORT*/
	@Excel(name = "最后一次登录PORT", width = 15)
    @ApiModelProperty(value = "最后一次登录PORT")
	private String lastloginport;
	/**登录状态（0未登录，1已登录）*/
	@Excel(name = "登录状态（0未登录，1已登录）", width = 15)
    @ApiModelProperty(value = "登录状态（0未登录，1已登录）")
	private Integer loginstatus;
	/**登录SESSION标识*/
	@Excel(name = "登录SESSION标识", width = 15)
    @ApiModelProperty(value = "登录SESSION标识")
	private String loginsessionid;
	/**退出登录时间*/
	@Excel(name = "退出登录时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "退出登录时间")
	private Date logouttime;
	/**通知信息*/
	@Excel(name = "通知信息", width = 15)
    @ApiModelProperty(value = "通知信息")
	private String notifyinfo;
	/**通知会话ID*/
	@Excel(name = "通知会话ID", width = 15)
    @ApiModelProperty(value = "通知会话ID")
	private String notifysessionid;
	/**状态变更时间*/
	@Excel(name = "状态变更时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "状态变更时间")
	private Date statusupdatetime;
	/**
	 * 更新密码的时间
	 */
	private Date updatepasswordtime;

	@TableField(exist = false)
	private Boolean fxdiszx;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@TableField(exist = false)
	private Date fxdzjzxsj;

}
