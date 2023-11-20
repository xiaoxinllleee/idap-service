package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款监控平台诉讼资产_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_SSZC_XT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_SSZC_XT对象", description="贷款监控平台诉讼资产_湘潭")
public class DkjkptsszcxtImport {
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
	@ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
	@ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	private java.math.BigDecimal ye;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
	@ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**担保/抵押有效性*/
	@Excel(name = "担保/抵押有效性", width = 15)
    @ApiModelProperty(value = "担保/抵押有效性")
	private String dbdyyxx;
	/**转入表外日期*/
	@Excel(name = "转入表外日期", width = 15)
    @ApiModelProperty(value = "转入表外日期")
	private String zrbwrq;
	/**转入表外类型*/
	@Excel(name = "转入表外类型", width = 15)
    @ApiModelProperty(value = "转入表外类型")
	private String zrbwlx;
	/**是否有权益*/
	@Excel(name = "是否有权益", width = 15)
    @ApiModelProperty(value = "是否有权益")
	private String sfyqy;
	/**有无借据（合同）原件*/
	@Excel(name = "有无借据（合同）原件", width = 15)
    @ApiModelProperty(value = "有无借据（合同）原件")
	private String ywjj;
	/**借款人类型*/
	@Excel(name = "借款人类型", width = 15)
    @ApiModelProperty(value = "借款人类型")
	private String jkrlx;
	/**借款人现状*/
	@Excel(name = "借款人现状", width = 15)
    @ApiModelProperty(value = "借款人现状")
	private String jkrxz;
	/**是否起诉*/
	@Excel(name = "是否起诉", width = 15)
    @ApiModelProperty(value = "是否起诉")
	private String sfqs;
	/**是否有诉讼时效*/
	@Excel(name = "是否有诉讼时效", width = 15)
    @ApiModelProperty(value = "是否有诉讼时效")
	private String sfysssx;
	/**是否有执行时效*/
	@Excel(name = "是否有执行时效", width = 15)
    @ApiModelProperty(value = "是否有执行时效")
	private String sfyzxsx;
	/**诉讼阶段*/
	@Excel(name = "诉讼阶段", width = 15)
    @ApiModelProperty(value = "诉讼阶段")
	private String ssjd;
	/**抵押资产处置情况*/
	@Excel(name = "抵押资产处置情况", width = 15)
    @ApiModelProperty(value = "抵押资产处置情况")
	private String dyzcczqk;
	/**剩余未处置资产对应贷款本金*/
	@Excel(name = "剩余未处置资产对应贷款本金", width = 15)
    @ApiModelProperty(value = "剩余未处置资产对应贷款本金")
	private String sywczzcdydkbj;
	/**未处置抵押物性质*/
	@Excel(name = "未处置抵押物性质", width = 15)
    @ApiModelProperty(value = "未处置抵押物性质")
	private String wczdywxz;
	/**未处置抵押物位置、楼层*/
	@Excel(name = "未处置抵押物位置、楼层", width = 15)
    @ApiModelProperty(value = "未处置抵押物位置、楼层")
	private String wqcdywwz;
	/**未处置抵押物面积*/
	@Excel(name = "未处置抵押物面积", width = 15)
    @ApiModelProperty(value = "未处置抵押物面积")
	private String wczdywmj;

}
