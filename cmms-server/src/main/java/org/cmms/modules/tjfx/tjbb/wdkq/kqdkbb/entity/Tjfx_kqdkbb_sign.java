package org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KQDKBB_SIGN")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KQDKBB_SIGN对象", description="1")
public class Tjfx_kqdkbb_sign {
    
	/**signId*/
	@Excel(name = "signId", width = 15)
    @ApiModelProperty(value = "signId")
	private String signId;
	/**所属机构代码*/
	@Excel(name = "所属机构代码", width = 15)
    @ApiModelProperty(value = "所属机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ssjgdm;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**用户Id*/
	@Excel(name = "用户Id", width = 15)
    @ApiModelProperty(value = "用户Id")
	private String userId;
	/**日期*/
	@Excel(name = "日期", width = 15)
    @ApiModelProperty(value = "日期")
	private String signDt;
	/**时间*/
	@Excel(name = "时间", width = 15)
    @ApiModelProperty(value = "时间")
	private String signTm;
	/**tmSmp*/
	@Excel(name = "tmSmp", width = 15)
    @ApiModelProperty(value = "tmSmp")
	private String tmSmp;
}
