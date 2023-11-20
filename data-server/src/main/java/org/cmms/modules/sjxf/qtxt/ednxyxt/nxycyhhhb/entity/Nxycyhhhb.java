package org.cmms.modules.sjxf.qtxt.ednxyxt.nxycyhhhb.entity;

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
 * @Description: 农信银成员行行号表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_brno")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_brno对象", description="农信银成员行行号表")
public class Nxycyhhhb {
    
	/**行号*/
	@Excel(name = "行号", width = 15)
    @ApiModelProperty(value = "行号")
	private String npsBrno;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**属性*/
	@Excel(name = "属性", width = 15)
    @ApiModelProperty(value = "属性")
	private String att;
	/**清算行号*/
	@Excel(name = "清算行号", width = 15)
    @ApiModelProperty(value = "清算行号")
	private String sttlNpsBrno;
	/**行名*/
	@Excel(name = "行名", width = 15)
    @ApiModelProperty(value = "行名")
	private String npsName;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String addr;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
	private String post;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String telnum;
	/**电子邮箱*/
	@Excel(name = "电子邮箱", width = 15)
    @ApiModelProperty(value = "电子邮箱")
	private String email;
	/**原农信银行号*/
	@Excel(name = "原农信银行号", width = 15)
    @ApiModelProperty(value = "原农信银行号")
	private String orNpsBrno;
	/**原农信银业务权限*/
	@Excel(name = "原农信银业务权限", width = 15)
    @ApiModelProperty(value = "原农信银业务权限")
	private String orNpsRight;
	/**生效日期*/
	@Excel(name = "生效日期", width = 15)
    @ApiModelProperty(value = "生效日期")
	private String validDate;
	/**失效日期*/
	@Excel(name = "失效日期", width = 15)
    @ApiModelProperty(value = "失效日期")
	private String invalidDate;
	/**最近更新操作*/
	@Excel(name = "最近更新操作", width = 15)
    @ApiModelProperty(value = "最近更新操作")
	private String modifyType;
	/**对应人行行号*/
	@Excel(name = "对应人行行号", width = 15)
    @ApiModelProperty(value = "对应人行行号")
	private String pbocBrno;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**行别*/
	@Excel(name = "行别", width = 15)
    @ApiModelProperty(value = "行别")
	private String bankcty;
	/**地区代码*/
	@Excel(name = "地区代码", width = 15)
    @ApiModelProperty(value = "地区代码")
	private String areacode;
	/**数据起始日期*/
	@Excel(name = "数据起始日期", width = 15)
    @ApiModelProperty(value = "数据起始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
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
