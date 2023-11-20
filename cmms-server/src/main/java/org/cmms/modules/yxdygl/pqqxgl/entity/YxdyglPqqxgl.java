package org.cmms.modules.yxdygl.pqqxgl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date:   2021-11-17
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_PQQXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXDYGL_PQQXGL对象", description="片区权限管理")
public class YxdyglPqqxgl {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**所属支行*/
	//@Excel(name = "所属支行", width = 15 , dicCode = "zzbz",dictTable = "HR_BAS_ORGANIZATION" ,dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**所属单元编号*/
	@Excel(name = "网格编号", width = 15,dicCode = "id",dictTable = "V_YXDYGL_MAIN",dicText = "wgmc_show")
	@ApiModelProperty(value = "所属单元编号")
	@Dict(dicCode = "id",dictTable = "V_YXDYGL_MAIN",dicText = "wgmc_show")
	private String menuId;
	/**客户经理*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "HR_BAS_STAFF",dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh",dictTable = "HR_BAS_STAFF",dicText = "ygxm")
	private String khjl;
	/**是否主客户经理*/
	@Excel(name = "是否主客户经理", width = 15 ,dicCode = "sfbz")
    @ApiModelProperty(value = "是否主客户经理")
	@Dict(dicCode = "sfbz")
	private String sfzkhjl;
	/**区分标识(1:农户，2：商户)*/
	@Excel(name = "数据权限", width = 15,dicCode = "wgsjqx")
	@ApiModelProperty(value = "数据权限")
	@Dict(dicCode = "wgsjqx")
	private String sjqx;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	//@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	//@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	//@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	@TableField(exist=false)
	List<YxdyglPqqxgl> children;
}
