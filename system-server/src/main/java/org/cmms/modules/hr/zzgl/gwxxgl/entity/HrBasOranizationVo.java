package org.cmms.modules.hr.zzgl.gwxxgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: www
 * @Author: jeecg-boot
 * @Date:   2021-10-23
 * @Version: V1.0
 */
@Data
@TableName("V_HR_BAS_ORGANIZATION")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_HR_BAS_ORGANIZATION对象", description="www")
public class HrBasOranizationVo {
    
	/**sjzzjc*/
	@Excel(name = "sjzzjc", width = 15)
    @ApiModelProperty(value = "sjzzjc")
	private String sjzzjc;
	/**sjywjgdm*/
	@Excel(name = "sjywjgdm", width = 15)
    @ApiModelProperty(value = "sjywjgdm")
	private String sjywjgdm;
	/**sjzzbzR*/
	@Excel(name = "sjzzbzR", width = 15)
    @ApiModelProperty(value = "sjzzbzR")
	private String sjzzbzR;
	/**zzbz*/
	@Excel(name = "zzbz", width = 15)
    @ApiModelProperty(value = "zzbz")
	private String zzbz;
	/**zzmc*/
	@Excel(name = "zzmc", width = 15)
    @ApiModelProperty(value = "zzmc")
	private String zzmc;
	/**zzlb*/
	@Excel(name = "zzlb", width = 15)
    @ApiModelProperty(value = "zzlb")
	private Integer zzlb;
	/**zzjb*/
	@Excel(name = "zzjb", width = 15)
    @ApiModelProperty(value = "zzjb")
	private Integer zzjb;
	/**qybz*/
	@Excel(name = "qybz", width = 15)
    @ApiModelProperty(value = "qybz")
	private Integer qybz;
	/**sjzzbz*/
	@Excel(name = "sjzzbz", width = 15)
    @ApiModelProperty(value = "sjzzbz")
	private String sjzzbz;
	/**zzjc*/
	@Excel(name = "zzjc", width = 15)
    @ApiModelProperty(value = "zzjc")
	private String zzjc;
	/**ywjgdm*/
	@Excel(name = "ywjgdm", width = 15)
    @ApiModelProperty(value = "ywjgdm")
	private String ywjgdm;
	/**bbqxjgdm*/
	@Excel(name = "bbqxjgdm", width = 15)
    @ApiModelProperty(value = "bbqxjgdm")
	private String bbqxjgdm;
	/**ywjglx*/
	@Excel(name = "ywjglx", width = 15)
    @ApiModelProperty(value = "ywjglx")
	private Integer ywjglx;
	/**ywjgxz*/
	@Excel(name = "ywjgxz", width = 15)
    @ApiModelProperty(value = "ywjgxz")
	private Integer ywjgxz;
	/**ywjgbz*/
	@Excel(name = "ywjgbz", width = 15)
    @ApiModelProperty(value = "ywjgbz")
	private Integer ywjgbz;
	/**pxxh*/
	@Excel(name = "pxxh", width = 15)
    @ApiModelProperty(value = "pxxh")
	private java.math.BigDecimal pxxh;
	/**szqy*/
	@Excel(name = "szqy", width = 15)
    @ApiModelProperty(value = "szqy")
	private Integer szqy;
}
