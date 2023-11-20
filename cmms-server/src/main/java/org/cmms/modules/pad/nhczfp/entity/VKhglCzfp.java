package org.cmms.modules.pad.nhczfp.entity;

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
 * @Description: 村组复评
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_czfp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_czfp对象", description="村组复评")
public class VKhglCzfp {

	/**pyid*/
	@Excel(name = "pyid", width = 15)
	@ApiModelProperty(value = "pyid")
	private String pyid;
	/**fpid*/
	@Excel(name = "fpid", width = 15)
	@ApiModelProperty(value = "fpid")
	private String fpid;
	/**pyid*/
	@Excel(name = "nhid", width = 15)
	@ApiModelProperty(value = "nhid")
	private String nhid;
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**sjhm*/
	@Excel(name = "sjhm", width = 15)
    @ApiModelProperty(value = "sjhm")
	private String sjhm;
	/**jcmxcs*/
	@Excel(name = "jcmxcs", width = 15)
    @ApiModelProperty(value = "jcmxcs")
	private Integer jcmxcs;
	/**fped*/
	@Excel(name = "fped", width = 15)
    @ApiModelProperty(value = "fped")
	private Integer fped;
	/**hzxm*/
	@Excel(name = "hzxm", width = 15)
    @ApiModelProperty(value = "hzxm")
	private String hzxm;
	/**hzzjhm*/
	@Excel(name = "hzzjhm", width = 15)
    @ApiModelProperty(value = "hzzjhm")
	private String hzzjhm;
	/**pylx*/
	@Excel(name = "pylx", width = 15)
    @ApiModelProperty(value = "pylx")
	private String pylx;
	/**pyyzjhm*/
	@Excel(name = "pyyzjhm", width = 15)
	@ApiModelProperty(value = "pyyzjhm")
	private String pyyzjhm;
	/**pysj*/
	@Excel(name = "pysj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "pysj")
	private Date pysj;
	/**sdje*/
	@Excel(name = "sdje", width = 15)
	@ApiModelProperty(value = "sdje")
	private Integer sdje;
	/**支行审定备注*/
	@Excel(name = "zhsdbz", width = 15)
	@ApiModelProperty(value = "zhsdbz")
	private String zhsdbz;
}
