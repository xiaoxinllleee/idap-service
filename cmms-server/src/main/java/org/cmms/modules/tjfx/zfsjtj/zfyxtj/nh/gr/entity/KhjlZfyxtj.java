package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 客户经理走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zfyxtj_khjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zfyxtj_khjl对象", description="客户经理走访营销统计")
public class KhjlZfyxtj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计维度*/
    @ApiModelProperty(value = "统计维度")
	private String tjwd;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15, dicCode="GWBZ", dictTable="HR_BAS_POST", dicText="GWMC")
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode="GWBZ", dictTable="HR_BAS_POST", dicText="GWMC")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15, dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**客户类型*/
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private Integer zfhs;
	/**白名单走访户数*/
	@Excel(name = "白名单走访户数", width = 15)
	@ApiModelProperty(value = "白名单走访户数")
	private Integer bmdZfhs;
	/**授信未用信走访户数*/
	@Excel(name = "授信未用信走访户数", width = 15)
	@ApiModelProperty(value = "授信未用信走访户数")
	private Integer sxwyxZfhs;
	/**授信已用信走访户数*/
	@Excel(name = "授信已用信走访户数", width = 15)
	@ApiModelProperty(value = "授信已用信走访户数")
	private Integer sxyyxZfhs;
	/**不予授信走访户数*/
	@Excel(name = "不予授信走访户数", width = 15)
	@ApiModelProperty(value = "不予授信走访户数")
	private Integer bysxZfhs;
	/**走访排名*/
//	@Excel(name = "走访排名", width = 15)
	@ApiModelProperty(value = "走访排名")
	private Integer zfpm;
	/**走访户数比上期*/
//	@Excel(name = "走访户数比上期", width = 15)
	@ApiModelProperty(value = "走访户数比上期")
	private Integer zfhsBsq;
	/**有效走访户数*/
	@Excel(name = "有效走访户数", width = 15)
	@ApiModelProperty(value = "有效走访户数")
	private Integer yxzfhs;
	/**白名单有效走访户数*/
	@Excel(name = "白名单有效走访户数", width = 15)
	@ApiModelProperty(value = "白名单有效走访户数")
	private Integer bmdYxzfhs;
	/**授信未用信有效走访户数*/
	@Excel(name = "授信未用信有效走访户数", width = 15)
	@ApiModelProperty(value = "授信未用信有效走访户数")
	private Integer sxwyxYxzfhs;
	/**授信已用信有效走访户数*/
	@Excel(name = "授信已用信有效走访户数", width = 15)
	@ApiModelProperty(value = "授信已用信有效走访户数")
	private Integer sxyyxYxzfhs;
	/**不予授信有效走访户数*/
	@Excel(name = "不予授信有效走访户数", width = 15)
	@ApiModelProperty(value = "不予授信有效走访户数")
	private Integer bysxYxzfhs;
	/**有效走访排名*/
	@Excel(name = "有效走访排名", width = 15)
    @ApiModelProperty(value = "有效走访排名")
	private Integer yxzfpm;
	/**有效走访户数比上期*/
	@Excel(name = "有效走访户数比上期", width = 15)
    @ApiModelProperty(value = "有效走访户数比上期")
	private Integer yxzfhsBsq;
	/**授信户数*/
//	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Integer sxhs;
	/**授信金额*/
//	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**授信户数排名*/
//	@Excel(name = "授信户数排名", width = 15)
    @ApiModelProperty(value = "授信户数排名")
	private Integer sxhspm;
	/**授信金额排名*/
//	@Excel(name = "授信金额排名", width = 15)
    @ApiModelProperty(value = "授信金额排名")
	private Integer sxjepm;
	/**授信户数比上期*/
//	@Excel(name = "授信户数比上期", width = 15)
    @ApiModelProperty(value = "授信户数比上期")
	private Integer sxhsBsq;
	/**授信金额比上期*/
//	@Excel(name = "授信金额比上期", width = 15)
    @ApiModelProperty(value = "授信金额比上期")
	private java.math.BigDecimal sxjeBsq;
	/**用信户数*/
//	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Integer yxhs;
	/**用信金额*/
//	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**用信户数排名*/
//	@Excel(name = "用信户数排名", width = 15)
    @ApiModelProperty(value = "用信户数排名")
	private Integer yxhspm;
	/**用信金额排名*/
//	@Excel(name = "用信金额排名", width = 15)
    @ApiModelProperty(value = "用信金额排名")
	private Integer yxjepm;
	/**用信户数比上期*/
//	@Excel(name = "用信户数比上期", width = 15)
    @ApiModelProperty(value = "用信户数比上期")
	private Integer yxhsBsq;
	/**用信金额比上期*/
//	@Excel(name = "用信金额比上期", width = 15)
    @ApiModelProperty(value = "用信金额比上期")
	private java.math.BigDecimal yxjeBsq;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;



}
