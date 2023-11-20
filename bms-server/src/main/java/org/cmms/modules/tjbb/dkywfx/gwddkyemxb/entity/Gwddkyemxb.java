package org.cmms.modules.tjbb.dkywfx.gwddkyemxb.entity;

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
 * @Description: 各网点贷款余额明细表
 * @Author: Penghr
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("tjbb_xdb_gwddkyemxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_xdb_gwddkyemxb对象", description="各网点贷款余额明细表")
public class Gwddkyemxb {

	/**数据截止日期*/
	@Excel(name = "数据截止日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据截止日期")
	private Date dataDate;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**本期贷款余额*/
	@Excel(name = "本期余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "本期贷款余额")
	private java.math.BigDecimal bqdkye;
	/**年初贷款余额*/
	@Excel(name = "年初余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "年初贷款余额")
	private java.math.BigDecimal ncdkye;
	/**上月贷款余额*/
	@Excel(name = "上月余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "上月贷款余额")
	private java.math.BigDecimal sydkye;
	/**贷款余额比年初（+，-）*/
	@Excel(name = "比年初（+，-）", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "贷款余额比年初（+，-）")
	private java.math.BigDecimal dkyeBnc;
	/**贷款余额比上月（+，-）*/
	@Excel(name = "比上月（+，-）", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "贷款余额比上月（+，-）")
	private java.math.BigDecimal dkyeBsy;
	/**全年任务*/
	@Excel(name = "全年任务", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "全年任务")
	private java.math.BigDecimal qnrw;
	/**差全年任务数（+，-）*/
	@Excel(name = "差全年任务数（+，-）", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "差全年任务数（+，-）")
	private java.math.BigDecimal cqnrws;
	/**完成全年任务比例*/
	@Excel(name = "完成全年任务比例", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "完成全年任务比例")
	private java.math.BigDecimal wcqnrwbl;
	/**排名*/
	@Excel(name = "排名", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "排名")
	private Integer pm;
	/**本期有贷款余额户数*/
	@Excel(name = "本期户数", width = 15, groupName = "有贷款余额户数")
    @ApiModelProperty(value = "本期有贷款余额户数")
	private Integer bqhs;
	/**年初有贷款余额户数*/
	@Excel(name = "年初户数", width = 15, groupName = "有贷款余额户数")
    @ApiModelProperty(value = "年初有贷款余额户数")
	private Integer nchs;
	/**上月有贷款余额户数*/
	@Excel(name = "上月户数", width = 15, groupName = "有贷款余额户数")
    @ApiModelProperty(value = "上月有贷款余额户数")
	private Integer syhs;
	/**有贷款余额户数比年初（+，-）*/
	@Excel(name = "比年初（+，-）", width = 15, groupName = "有贷款余额户数")
    @ApiModelProperty(value = "有贷款余额户数比年初（+，-）")
	private Integer bnchs;
	/**有贷款余额户数比上月（+，-）*/
	@Excel(name = "比上月（+，-）", width = 15, groupName = "有贷款余额户数")
    @ApiModelProperty(value = "有贷款余额户数比上月（+，-）")
	private Integer bsyhs;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
