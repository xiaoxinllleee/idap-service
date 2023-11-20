package org.cmms.modules.tjfx.ipadsysj.entity;

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
 * @Description: 平板端首页数据
 * @Author: jeecg-boot
 * @Date:   2020-07-23
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_sysj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_sysj对象", description="平板端首页数据")
public class vTjfxSysj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**本月走访户数*/
	@Excel(name = "本月走访户数", width = 15)
    @ApiModelProperty(value = "本月走访户数")
	private Integer byzfhs;
	/**累计走访户数*/
	@Excel(name = "累计走访户数", width = 15)
    @ApiModelProperty(value = "累计走访户数")
	private Integer ljzfhs;
	/**本月预授信额度*/
	@Excel(name = "本月预授信额度", width = 15)
    @ApiModelProperty(value = "本月预授信额度")
	private Integer byysxed;
	/**累计预授信额度*/
	@Excel(name = "累计预授信额度", width = 15)
    @ApiModelProperty(value = "累计预授信额度")
	private Integer ljysxed;
	/**本月授信额度*/
	@Excel(name = "本月授信额度", width = 15)
    @ApiModelProperty(value = "本月授信额度")
	private Integer bysxed;
	/**本月用信金额*/
	@Excel(name = "本月用信金额", width = 15)
    @ApiModelProperty(value = "本月用信金额")
	private Integer byyxje;
	/**累计用信金额*/
	@Excel(name = "累计用信金额", width = 15)
    @ApiModelProperty(value = "累计用信金额")
	private Integer ljyxje;
	/**全行排名*/
	@Excel(name = "全行排名", width = 15)
    @ApiModelProperty(value = "全行排名")
	private Integer qhpm;
}
