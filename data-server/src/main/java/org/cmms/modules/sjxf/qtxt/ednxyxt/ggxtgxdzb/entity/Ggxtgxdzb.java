package org.cmms.modules.sjxf.qtxt.ednxyxt.ggxtgxdzb.entity;

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
 * @Description: 农信银行号对应行内系统机构关系对照表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_bank_brno")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_bank_brno对象", description="农信银行号对应行内系统机构关系对照表")
public class Ggxtgxdzb {
    
	/**农信银系统行号*/
	@Excel(name = "农信银系统行号", width = 15)
    @ApiModelProperty(value = "农信银系统行号")
	private String npsBrno;
	/**本行机构号*/
	@Excel(name = "本行机构号", width = 15)
    @ApiModelProperty(value = "本行机构号")
	private String brno;
	/**清算中心行号*/
	@Excel(name = "清算中心行号", width = 15)
    @ApiModelProperty(value = "清算中心行号")
	private String sttlNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String brName;
	/**农信银系统行名*/
	@Excel(name = "农信银系统行名", width = 15)
    @ApiModelProperty(value = "农信银系统行名")
	private String npsName;
	/**清算中心名称*/
	@Excel(name = "清算中心名称", width = 15)
    @ApiModelProperty(value = "清算中心名称")
	private String sttlName;
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
