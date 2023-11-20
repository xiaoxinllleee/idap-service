package org.cmms.modules.sjxf.qtxt.cwglxt.frjgfwxx.entity;

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
 * @Description: 法人机构范围信息
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_artif_betwn")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_artif_betwn对象", description="法人机构范围信息")
public class Frjgfwxx {
    
	/**法人联社标识*/
	@Excel(name = "法人联社标识", width = 15)
    @ApiModelProperty(value = "法人联社标识")
	private String upBrNo;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String brNo;
	/**法人联社名称*/
	@Excel(name = "法人联社名称", width = 15)
    @ApiModelProperty(value = "法人联社名称")
	private String name;
	/**开始机构号*/
	@Excel(name = "开始机构号", width = 15)
    @ApiModelProperty(value = "开始机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String beginNo;
	/**结束机构号*/
	@Excel(name = "结束机构号", width = 15)
    @ApiModelProperty(value = "结束机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String endNo;
	/**机构类型*/
	@Excel(name = "机构类型", width = 15)
    @ApiModelProperty(value = "机构类型")
	private String corpType;
	/**机构状态*/
	@Excel(name = "机构状态", width = 15)
    @ApiModelProperty(value = "机构状态")
	private String corpSts;
	/**维护日期*/
	@Excel(name = "维护日期", width = 15)
    @ApiModelProperty(value = "维护日期")
	private Integer weihuDate;
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
