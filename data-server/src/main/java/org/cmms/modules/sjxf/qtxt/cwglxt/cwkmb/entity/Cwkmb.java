package org.cmms.modules.sjxf.qtxt.cwglxt.cwkmb.entity;

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
 * @Description: 财务科目表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_com_item")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_com_item对象", description="财务科目表")
public class Cwkmb {
    
	/**帐套类型*/
	@Excel(name = "帐套类型", width = 15)
    @ApiModelProperty(value = "帐套类型")
	private String accType;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String accNo;
	/**科目控制字*/
	@Excel(name = "科目控制字", width = 15)
    @ApiModelProperty(value = "科目控制字")
	private String accHrt;
	/**应用代码*/
	@Excel(name = "应用代码", width = 15)
    @ApiModelProperty(value = "应用代码")
	private String appCod;
	/**汇总科目控制字*/
	@Excel(name = "汇总科目控制字", width = 15)
    @ApiModelProperty(value = "汇总科目控制字")
	private String upAccHrt;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
    @ApiModelProperty(value = "科目名称")
	private String accName;
	/**子科目标志*/
	@Excel(name = "子科目标志", width = 15)
    @ApiModelProperty(value = "子科目标志")
	private String subAccYn;
	/**科目级别*/
	@Excel(name = "科目级别", width = 15)
    @ApiModelProperty(value = "科目级别")
	private String accLvl;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String dcInd;
	/**轧差标志*/
	@Excel(name = "轧差标志", width = 15)
    @ApiModelProperty(value = "轧差标志")
	private String rollInd;
	/**科目类型*/
	@Excel(name = "科目类型", width = 15)
    @ApiModelProperty(value = "科目类型")
	private String accKnd;
	/**本外币标志*/
	@Excel(name = "本外币标志", width = 15)
    @ApiModelProperty(value = "本外币标志")
	private String foreInd;
	/**平衡标志*/
	@Excel(name = "平衡标志", width = 15)
    @ApiModelProperty(value = "平衡标志")
	private String equaInd;
	/**发生额方向*/
	@Excel(name = "发生额方向", width = 15)
    @ApiModelProperty(value = "发生额方向")
	private String amtDcInd;
	/**透支标志*/
	@Excel(name = "透支标志", width = 15)
    @ApiModelProperty(value = "透支标志")
	private String odInd;
	/**内部科目标志*/
	@Excel(name = "内部科目标志", width = 15)
    @ApiModelProperty(value = "内部科目标志")
	private String inInd;
	/**是否允许撤销*/
	@Excel(name = "是否允许撤销", width = 15)
    @ApiModelProperty(value = "是否允许撤销")
	private String scope;
	/**资产负债类型*/
	@Excel(name = "资产负债类型", width = 15)
    @ApiModelProperty(value = "资产负债类型")
	private String accInd;
	/**使用系统*/
	@Excel(name = "使用系统", width = 15)
    @ApiModelProperty(value = "使用系统")
	private String uSys;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String brf;
	/**预算属性*/
	@Excel(name = "预算属性", width = 15)
    @ApiModelProperty(value = "预算属性")
	private String ysInd;
	/**科目种类*/
	@Excel(name = "科目种类", width = 15)
    @ApiModelProperty(value = "科目种类")
	private String kmInd;
	/**传票打印属性*/
	@Excel(name = "传票打印属性", width = 15)
    @ApiModelProperty(value = "传票打印属性")
	private String printFlag;
	/**种类编号*/
	@Excel(name = "种类编号", width = 15)
    @ApiModelProperty(value = "种类编号")
	private String classCode;
	/**对应种类帐套代码*/
	@Excel(name = "对应种类帐套代码", width = 15)
    @ApiModelProperty(value = "对应种类帐套代码")
	private String iteAccCode;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String regDate;
	/**科目状态*/
	@Excel(name = "科目状态", width = 15)
    @ApiModelProperty(value = "科目状态")
	private String itemSts;
	/**帐种类型*/
	@Excel(name = "帐种类型", width = 15)
    @ApiModelProperty(value = "帐种类型")
	private String acntKind;
	/**汇总机构扎差标志*/
	@Excel(name = "汇总机构扎差标志", width = 15)
    @ApiModelProperty(value = "汇总机构扎差标志")
	private String brRollInd;
	/**内部科目名称*/
	@Excel(name = "内部科目名称", width = 15)
    @ApiModelProperty(value = "内部科目名称")
	private String innerAccName;
	/**sDate*/
	@Excel(name = "sDate", width = 15)
    @ApiModelProperty(value = "sDate")
	private String sDate;
	/**eDate*/
	@Excel(name = "eDate", width = 15)
    @ApiModelProperty(value = "eDate")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
}
