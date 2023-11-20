package org.cmms.modules.khgl.khzhfx.khzhmx.entity;

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
 * @Description: 网上银行信息明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Data
@TableName("Khxxgl_khzhfx_wsyh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Khxxgl_khzhfx_wsyh对象", description="网上银行信息明细")
public class WsyhKhzh {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private java.lang.String sszh;
	/**归属网格*/
	@Excel(name = "归属网格编号", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	@ApiModelProperty(value = "归属网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	private java.lang.String wgbh;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15, dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	@ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private java.lang.String sskhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private java.lang.String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private java.lang.String khh;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private java.lang.String sjhm;
	/**渠道状态*/
	@Excel(name = "渠道状态", width = 15)
    @ApiModelProperty(value = "渠道状态")
	private java.lang.String qdzt;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private java.lang.String khjg;
	/**开户柜员*/
	@Excel(name = "开户柜员", width = 15)
    @ApiModelProperty(value = "开户柜员")
	private java.lang.String khgy;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private java.lang.String khsj;
}
