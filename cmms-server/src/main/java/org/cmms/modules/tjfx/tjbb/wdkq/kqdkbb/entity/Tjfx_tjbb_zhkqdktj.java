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
 * @Date:   2020-04-20
 * @Version: V1.0
 */
@Data
@TableName("tjfx_tjbb_zhkqdktj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_tjbb_zhkqdktj对象", description="1")
public class Tjfx_tjbb_zhkqdktj {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
    @ApiModelProperty(value = "客户经理编号")
	private String khjlbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**打卡次数*/
	@Excel(name = "打卡次数", width = 15)
    @ApiModelProperty(value = "打卡次数")
	private java.math.BigDecimal dkcs;
	/**有效打卡次数*/
	@Excel(name = "有效打卡次数", width = 15)
    @ApiModelProperty(value = "有效打卡次数")
	private java.math.BigDecimal yxdkcs;
	/**有效打卡天数*/
	@Excel(name = "有效打卡天数", width = 15)
    @ApiModelProperty(value = "有效打卡天数")
	private java.math.BigDecimal yxdkts;
	/**当年有效打卡天数*/
	@Excel(name = "当年有效打卡天数", width = 15)
    @ApiModelProperty(value = "当年有效打卡天数")
	private java.math.BigDecimal dnyxdkts;
}
