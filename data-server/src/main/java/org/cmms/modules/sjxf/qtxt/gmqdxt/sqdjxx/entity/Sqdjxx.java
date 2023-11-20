package org.cmms.modules.sjxf.qtxt.gmqdxt.sqdjxx.entity;

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
 * @Description: 授权登记信息
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Tler_jysqlsb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tler_jysqlsb对象", description="授权登记信息")
public class Sqdjxx {
    
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String tlid;
	/**柜员姓名*/
	@Excel(name = "柜员姓名", width = 15)
    @ApiModelProperty(value = "柜员姓名")
	private String name;
	/**授权机构*/
	@Excel(name = "授权机构", width = 15)
    @ApiModelProperty(value = "授权机构")
	private String sqgy;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String taskno;
	/**授权状态 0是通过,1是拒绝*/
	@Excel(name = "授权状态", width = 15)
    @ApiModelProperty(value = "授权状态")
	@Dict(dicCode = "shouqzt")
	private String status;
	/**授权原因*/
	@Excel(name = "授权原因", width = 15)
    @ApiModelProperty(value = "授权原因")
	private String remark;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String tradecode;
	/**交易名称*/
	@Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
	private String tranname;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String trantime;
	/**授权类型 0是本地授权,1是远程授权*/
	@Excel(name = "授权类型", width = 15)
    @ApiModelProperty(value = "授权类型")
	@Dict(dicCode = "shouqlx")
	private String authtype;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
