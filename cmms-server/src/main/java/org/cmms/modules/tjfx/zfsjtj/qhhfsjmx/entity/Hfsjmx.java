package org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.entity;

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
 * @Description: 回访数据明细
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_hfsjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_hfsjmx对象", description="回访数据明细")
public class Hfsjmx {
    
	/**回访日期*/
	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "回访日期")
	private Date hfrq;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**走访支行*/
	@Excel(name = "走访支行", width = 15)
    @ApiModelProperty(value = "走访支行")
	private String zfzhmc;
	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15)
    @ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	/**营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "所属营销单元")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String yxdy;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String khsszh;
	/**所属支行名称*/
	@Excel(name = "所属支行名称", width = 15)
    @ApiModelProperty(value = "所属支行名称")
	private String sszhmc;
	/**走访类型*/
	@Excel(name = "走访类型", width = 15)
    @ApiModelProperty(value = "走访类型")
	private String zflx;
	/**走访时间*/
	@Excel(name = "走访时间", width = 15)
    @ApiModelProperty(value = "走访时间")
	private String zfsj;
	/**总行陪访人*/
	@Excel(name = "总行陪访人", width = 15)
	@ApiModelProperty(value = "总行陪访人")
	private String zhpfr;
	/**ywid*/
	@Excel(name = "ywid", width = 15)
	@ApiModelProperty(value = "ywid")
	private String ywid;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
	@ApiModelProperty(value = "任务名称")
	private String rwmc;
}
