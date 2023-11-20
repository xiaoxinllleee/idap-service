package org.cmms.modules.tjbb.dkywfx.lxwcqktjb.entity;

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
 * @Description: 利息完成情况统计表（五）
 * @Author: Penghr
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("tjbb_xdb_lxwcqktjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_xdb_lxwcqktjb对象", description="利息完成情况统计表（五）")
public class Lxwcqktjb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**利息总收入*/
	@Excel(name = "利息总收入", width = 15)
    @ApiModelProperty(value = "利息总收入")
	private java.math.BigDecimal lxzsr;
	/**上年底：应收利息余额*/
	@Excel(name = "上年底应收利息余额", width = 15, groupName = "其中")
    @ApiModelProperty(value = "上年底：应收利息余额")
	private java.math.BigDecimal sndYslxye;
	/**本月底：应收利息余额*/
	@Excel(name = "本月底应收利息余额", width = 15, groupName = "其中")
    @ApiModelProperty(value = "本月底：应收利息余额")
	private java.math.BigDecimal bydYslxye;
	/**本年新增应收利息*/
	@Excel(name = "本年新增应收利息", width = 15, groupName = "其中")
    @ApiModelProperty(value = "本年新增应收利息")
	private java.math.BigDecimal bnxzyslx;
	/**比上月（+，-）*/
	@Excel(name = "比上月（+，-）", width = 15, groupName = "其中")
    @ApiModelProperty(value = "比上月（+，-）")
	private java.math.BigDecimal bsy;
	/**比上年同期（+，-）*/
	@Excel(name = "比上年同期（+，-）", width = 15, groupName = "其中")
    @ApiModelProperty(value = "比上年同期（+，-）")
	private java.math.BigDecimal bsntq;
	/**比上年同期增长率*/
	@Excel(name = "比上年同期增长率", width = 15, groupName = "其中")
    @ApiModelProperty(value = "比上年同期增长率")
	private java.math.BigDecimal bsntqzzl;
	/**全年收入*/
	@Excel(name = "全年收入", width = 15)
    @ApiModelProperty(value = "全年收入")
	private java.math.BigDecimal qnsr;
	/**完成比例*/
	@Excel(name = "完成比例", width = 15)
    @ApiModelProperty(value = "完成比例")
	private java.math.BigDecimal wcbl;
	/**任务完成率排名*/
	@Excel(name = "任务完成率排名", width = 15)
    @ApiModelProperty(value = "任务完成率排名")
	private Integer rwwclpm;
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
