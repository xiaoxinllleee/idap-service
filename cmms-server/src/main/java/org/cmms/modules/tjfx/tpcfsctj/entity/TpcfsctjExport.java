package org.cmms.modules.tjfx.tpcfsctj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 图片重复上传统计导出
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
@Data
public class TpcfsctjExport {
    
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String hzxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 20)
    @ApiModelProperty(value = "证件号码")
	private String hzzjhm;
	/**上传人*/
	@Excel(name = "上传人", width = 15, dicCode="username", dictTable="sys_bas_user", dicText="realname")
	@ApiModelProperty(value = "上传人")
	@Dict(dicCode="username", dictTable="sys_bas_user", dicText="realname")
	private String scr;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
	private Date scsj;
}
