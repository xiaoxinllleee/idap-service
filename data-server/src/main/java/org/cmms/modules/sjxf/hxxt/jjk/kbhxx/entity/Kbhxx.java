package org.cmms.modules.sjxf.hxxt.jjk.kbhxx.entity;

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
 * @Description: 卡6位BIN号信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Data
@TableName("CBSC_BIN")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CBSC_BIN对象", description="卡6位BIN号信息表")
public class Kbhxx {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private Integer institution;
	/**卡BIN*/
	@Excel(name = "卡BIN", width = 15)
    @ApiModelProperty(value = "卡BIN")
	@TableField(value = "no")
	private Integer no;
	/**卡前缀*/
	@Excel(name = "卡前缀", width = 15)
    @ApiModelProperty(value = "卡前缀")
	private String prefix;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
	private String name;
	/**检验位算法*/
	@Excel(name = "检验位算法", width = 15)
    @ApiModelProperty(value = "检验位算法")
	private String checkDigit;
	/**卡类型*/
	@Excel(name = "卡类型", width = 15)
    @ApiModelProperty(value = "卡类型")
	@Dict(dicCode = "klx")
	private String isoType;
	/**密码格式和长度(HSM加密时使用)*/
	@Excel(name = "密码格式和长度", width = 15)
    @ApiModelProperty(value = "密码格式和长度")
	private String pinFormat;
	/**密码校验方式*/
	@Excel(name = "密码校验方式", width = 15)
    @ApiModelProperty(value = "密码校验方式")
	private String pinMethod;
	/**CVV校验方式*/
	@Excel(name = "CVV校验方式", width = 15)
    @ApiModelProperty(value = "CVV校验方式")
	private String cvvMethod;
	/**CVV类型*/
	@Excel(name = "CVV类型", width = 15)
    @ApiModelProperty(value = "CVV类型")
	private String cvvType;
	/**销卡之后多少个月之后从数据库清除*/
	@Excel(name = "销卡之后多少个月之后从数据库清除", width = 15)
    @ApiModelProperty(value = "销卡之后多少个月之后从数据库清除")
	private Integer purgePeriod;
	/**最近维护日期*/
    @ApiModelProperty(value = "最近维护日期")
	private Date lastMaintainDate;
	/**最近维护标志*/
	@Excel(name = "最近维护标志", width = 15)
    @ApiModelProperty(value = "最近维护标志")
	private String lastMaintainFlag;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
	@Excel(name = "加载日期", width = 15)
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**天入库表编号-对不同的表名唯一*/
    /*@ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
