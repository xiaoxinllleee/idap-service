package org.cmms.modules.sjxf.xdxt.dzygyrxx.entity;

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
 * @Description: 抵质押共有人信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_pledge_cust")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_pledge_cust对象", description="抵质押共有人信息")
public class Dzygyrxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String pdegeId;
	/**抵质物流水号*/
	@Excel(name = "抵质物流水号", width = 15)
    @ApiModelProperty(value = "抵质物流水号")
	private String guarantyDetailId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**抵押人住所*/
	@Excel(name = "抵押人住所", width = 15)
    @ApiModelProperty(value = "抵押人住所")
	private String describe1;
	/**抵押人联系方式*/
	@Excel(name = "抵押人联系方式", width = 15)
    @ApiModelProperty(value = "抵押人联系方式")
	private String describe2;
	/**共有方式*/
	@Excel(name = "共有方式", width = 15)
    @ApiModelProperty(value = "共有方式")
	private String describe4;
	/**其他描述/共有关系描述*/
	@Excel(name = "其他描述/共有关系描述", width = 15)
    @ApiModelProperty(value = "其他描述/共有关系描述")
	private String describe7;
	/**占有份额*/
	@Excel(name = "占有份额", width = 15)
    @ApiModelProperty(value = "占有份额")
	private String possessrate;
	/**新增日期*/
	@Excel(name = "新增日期", width = 15)
    @ApiModelProperty(value = "新增日期")
	private String updateDate;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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
