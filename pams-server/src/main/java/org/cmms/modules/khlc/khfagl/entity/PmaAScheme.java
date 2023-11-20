package org.cmms.modules.khlc.khfagl.entity;

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
import lombok.Value;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 考核方案基础信息表
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Data
@TableName("PMA_A_SCHEME")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_A_SCHEME对象", description="考核方案基础信息表")
public class PmaAScheme {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;

	/**考核方案ID*/
	@Excel(name = "考核方案ID", width = 15)
    @ApiModelProperty(value = "考核方案ID")
	private String schemeId;

	/**考核方案名称*/
	@Excel(name = "考核方案名称", width = 15)
    @ApiModelProperty(value = "考核方案名称")
	private String schemeName;

	/**考核方案目录编号*/
	@Excel(name = "考核方案目录编号", width = 15)
    @ApiModelProperty(value = "考核方案目录编号")
	@Dict(dicCode = "menu_id", dictTable = "PMA_A_SCHEME_MENU", dicText = "menu_name")
	private String menuId;

	/**考核场景*/
	@Excel(name = "考核场景", width = 15)
    @ApiModelProperty(value = "考核场景")
	@Dict(dicCode = "CHECK_SCENE")
	private String checkScene;

	/**评价对象类型*/
	@Excel(name = "评价对象类型", width = 15)
    @ApiModelProperty(value = "评价对象类型")
	@Dict(dicCode = "OBJ")
	private String evlObjType;



	/**方案描述*/
	@Excel(name = "方案描述", width = 15)
	@ApiModelProperty(value = "方案描述")
	private String remark;


	/**方案所属机构*/
	@Excel(name = "方案所属机构", width = 15)
	@ApiModelProperty(value = "方案所属机构")
	private String orgId;


	/**特殊规则类型*/
	@Excel(name = "特殊规则类型", width = 15)
    @ApiModelProperty(value = "特殊规则类型")
	private String speRuleType;


	@Excel(name = "执行顺序", width = 15)
	@ApiModelProperty(value = "执行顺序")
	private Integer zxsx;

	/**是否启用*/
	@Excel(name = "是否启用", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "sfbz")
	private String sfqy;

	/**创建人*/
	@Excel(name = "创建人", width = 15, dicCode = "username", dictTable = "sys_user", dicText = "realname")
	@ApiModelProperty(value = "创建人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String createBy;

	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**更新人*/
	@Excel(name = "更新人", width = 15, dicCode = "username", dictTable = "sys_user", dicText = "realname")
	@ApiModelProperty(value = "更新人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String updateBy;

	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	
	/**数据删除标志*/
	@Excel(name = "数据删除标志", width = 15)
    @ApiModelProperty(value = "数据删除标志")
	private String statFlag;
}
