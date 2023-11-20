package org.cmms.modules.yxdygl.ejyxdygl.entity;

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
 * @Description: 二级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-23
 * @Version: V1.0
 */
@Data
@TableName("v_yxdygl_ejyxdygl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_yxdygl_ejyxdygl对象", description="二级营销单元管理")
public class VEjyxdygl {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**一级营销单元编号*/
//	@Excel(name = "一级营销单元编号", width = 15)
    @ApiModelProperty(value = "一级营销单元编号")
	private String yjyxdybh;
	/**一级营销单元名称*/
	@Excel(name = "一级营销单元", width = 15)
    @ApiModelProperty(value = "一级营销单元名称")
	private String yjyxdymc;
	/**单元编号*/
	@Excel(name = "单元编号", width = 15)
    @ApiModelProperty(value = "单元编号")
	private String dybh;
	/**单元名称*/
	@Excel(name = "单元名称", width = 15)
    @ApiModelProperty(value = "单元名称")
	private String dymc;
	/**单元性质*/
	@Excel(name = "单元性质", width = 15, dicCode = "ejyxdyxz")
    @ApiModelProperty(value = "单元性质")
	@Dict(dicCode = "ejyxdyxz")
	private String dyxz;
	/**主客户经理工号*/
//	@Excel(name = "主客户经理工号", width = 15)
    @ApiModelProperty(value = "主客户经理工号")
	private String yggh;
	/**主客户经理姓名*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理姓名")
	private String ygxm;
	/**机构代码*/
//	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String sszh;
	/**机构名称*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String zzjc;
	/**责任部室*/
//	@Excel(name = "责任部室", width = 15)
    @ApiModelProperty(value = "责任部室")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zrbs;
	/**责任部室名称*/
	@Excel(name = "责任部室", width = 15)
    @ApiModelProperty(value = "责任部室名称")
	private String zrbsmc;
	/**责任领导*/
//	@Excel(name = "责任领导", width = 15)
    @ApiModelProperty(value = "责任领导")
	@Dict(dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	private String zrld;
	/**责任领导姓名*/
	@Excel(name = "责任领导", width = 15)
    @ApiModelProperty(value = "责任领导姓名")
	private String zrldxm;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**下级单元数量*/
	@Excel(name = "下级单元数量", width = 15)
    @ApiModelProperty(value = "下级单元数量")
	private Integer xjdysl;
	/**附件数量*/
	@Excel(name = "附件数量", width = 15)
    @ApiModelProperty(value = "附件数量")
	private Integer fjsl;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
//	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
//	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**网格面积*/
	@Excel(name = "网格面积", width = 15)
	@ApiModelProperty(value = "网格面积")
	private String wgmj;
	/**网格情况*/
	@Excel(name = "网格情况", width = 15)
	@ApiModelProperty(value = "网格情况")
	private String wgqk;
	/**网格痛点分析*/
	@Excel(name = "网格痛点分析", width = 15)
	@ApiModelProperty(value = "网格痛点分析")
	private String wgtdfx;
	/**村委书记*/
	@Excel(name = "村委书记", width = 15)
	@ApiModelProperty(value = "村委书记")
	private String cwsj;
	/**村委主任*/
	@Excel(name = "村委主任", width = 15)
	@ApiModelProperty(value = "村委主任")
	private String cwzr;
	/**村委办公室主任*/
	@Excel(name = "村委办公室主任", width = 15)
	@ApiModelProperty(value = "村委办公室主任")
	private String cwbgszr;
	/**网格位置*/
	@Excel(name = "网格位置", width = 15)
	@ApiModelProperty(value = "网格位置")
	private String wgwz;
}
