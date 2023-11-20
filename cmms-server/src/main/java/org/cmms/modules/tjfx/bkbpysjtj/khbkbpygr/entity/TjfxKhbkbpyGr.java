package org.cmms.modules.tjfx.bkbpysjtj.khbkbpygr.entity;

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
 * @Description: 个人背靠背评议明细
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHBKBPY_GR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHBKBPY_GR对象", description="个人背靠背评议明细")
public class TjfxKhbkbpyGr {

	/**评级日期*/
	@Excel(name = "评级日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评级日期")
	private Date pjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	@ApiModelProperty(value = "行政村")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	private String xzc;
	/**组*/
	@ApiModelProperty(value = "组")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_SJYXDYGL", dicText = "DYMC")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**评议员证件号码*/
	@Excel(name = "评议员证件号码", width = 15)
    @ApiModelProperty(value = "评议员证件号码")
	private String pyyzjhm;
	/**评议类型*/
	@Excel(name = "评议类型", width = 15 ,dicCode = "bkbpy_pylx")
    @ApiModelProperty(value = "评议类型")
	@Dict(dicCode = "bkbpy_pylx")
	private String pylx;
	/**评议附件数量*/
	@Excel(name = "评议附件数量", width = 15)
    @ApiModelProperty(value = "评议附件数量")
	private Long pyfjsl;
	/**评议记录数量*/
	@Excel(name = "评议记录数量", width = 15)
    @ApiModelProperty(value = "评议记录数量")
	private Long pyjlsl;
	/**评议得分*/
	@Excel(name = "评议得分", width = 15)
    @ApiModelProperty(value = "评议得分")
	private Long pydf;
}
