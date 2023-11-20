package org.cmms.modules.tjfx.tpcfsctj.entity;

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
 * @Description: 单户图片重复上传详细信息
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_dhtpcfsctj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_dhtpcfsctj对象", description="单户图片重复上传详细信息")
public class VDhtpcfsctj {
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
	/**图片总数*/
	@Excel(name = "图片总数", width = 15)
	@ApiModelProperty(value = "图片总数")
	private Integer tpzs;
	/**重复上传张数*/
	@Excel(name = "重复上传张数", width = 15)
	@ApiModelProperty(value = "重复上传张数")
	private Integer cfsczs;
}
