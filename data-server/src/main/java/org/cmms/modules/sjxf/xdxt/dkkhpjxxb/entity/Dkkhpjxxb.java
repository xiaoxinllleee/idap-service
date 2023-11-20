package org.cmms.modules.sjxf.xdxt.dkkhpjxxb.entity;

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
 * @Description: 贷款客户评级信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ci_ent_eval_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ci_ent_eval_info对象", description="贷款客户评级信息表")
public class Dkkhpjxxb {
    
	/**调整说明*/
	@Excel(name = "调整说明", width = 15)
    @ApiModelProperty(value = "调整说明")
	private String adjustExplain;
	/**实授信用等级*/
	@Excel(name = "实授信用等级", width = 15)
    @ApiModelProperty(value = "实授信用等级")
	private String appEvalGrade;
	/**应授信用等级*/
	@Excel(name = "应授信用等级", width = 15)
    @ApiModelProperty(value = "应授信用等级")
	private String applyEvalGrade;
	/**客户认定类别*/
	@Excel(name = "客户认定类别", width = 15)
    @ApiModelProperty(value = "客户认定类别")
	private String custEvalType;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private Long custId;
	/**报表编号*/
	@Excel(name = "报表编号", width = 15)
    @ApiModelProperty(value = "报表编号")
	private Long entFncInfoId;
	/**评估日期*/
	@Excel(name = "评估日期", width = 15)
    @ApiModelProperty(value = "评估日期")
	private String evalDate;
	/**评估流水编号*/
	@Excel(name = "评估流水编号", width = 15)
    @ApiModelProperty(value = "评估流水编号")
	private Long evalFlowId;
	/**评估级别*/
	@Excel(name = "评估级别", width = 15)
    @ApiModelProperty(value = "评估级别")
	private String evalGrade;
	/**评估ID流水号*/
	@Excel(name = "评估ID流水号", width = 15)
    @ApiModelProperty(value = "评估ID流水号")
	private Long evalInfoId;
	/**评估总得分*/
	@Excel(name = "评估总得分", width = 15)
    @ApiModelProperty(value = "评估总得分")
	private java.math.BigDecimal evalScore;
	/**评估类别*/
	@Excel(name = "评估类别", width = 15)
    @ApiModelProperty(value = "评估类别")
	private String evalType;
	/**当前评级是否有效*/
	@Excel(name = "当前评级是否有效", width = 15)
    @ApiModelProperty(value = "当前评级是否有效")
	private String isEnabled;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注3*/
	@Excel(name = "备注3", width = 15)
    @ApiModelProperty(value = "备注3")
	private String remark3;
	/**评级使用报表*/
	@Excel(name = "评级使用报表", width = 15)
    @ApiModelProperty(value = "评级使用报表")
	private String reportDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private Long userId;
	/**部门编号*/
	@Excel(name = "部门编号", width = 15)
    @ApiModelProperty(value = "部门编号")
	private Long deptId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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
