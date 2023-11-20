package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: ETC营销信息管理历史信息
 * @Author: jeecg-boot
 * @Date:   2021-12-23
 * @Version: V1.0
 */
@Data
@TableName("erp_bas_etcyxxxlsb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_bas_etcyxxxlsb对象", description="ETC营销信息管理历史信息")
public class EtcyxxxglLsxx {
    
	/**网点机构码*/
	@Excel(name = "网点机构码", width = 15)
    @ApiModelProperty(value = "网点机构码")
	private String yywdjgm;
	/**办理渠道*/
	@Excel(name = "办理渠道", width = 15)
    @ApiModelProperty(value = "办理渠道")
	private String blqd;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**客户身份证号*/
	@Excel(name = "客户身份证号", width = 15)
    @ApiModelProperty(value = "客户身份证号")
	private String khsfzh;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**车牌颜色*/
	@Excel(name = "车牌颜色", width = 15)
    @ApiModelProperty(value = "车牌颜色")
	private String cpys;
	/**管理营销人工号*/
	@Excel(name = "管理营销人工号", width = 15)
    @ApiModelProperty(value = "管理营销人工号")
	private String glzrrgh;
	/**管理时间止*/
	@Excel(name = "管理时间止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "管理时间止")
	private Date glsjz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
