package org.cmms.modules.sjxf.qtxt.cwglxt.bmb.entity;

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
 * @Description: 部门表
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_com_department")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_com_department对象", description="部门表")
public class Bmb {
    
	/**内部部门号*/
	@Excel(name = "内部部门号", width = 15)
    @ApiModelProperty(value = "内部部门号")
	private String depNo;
	/**部门名称*/
	@Excel(name = "部门名称", width = 15)
    @ApiModelProperty(value = "部门名称")
	private String depName;
	/**所属管辖内部部门号*/
	@Excel(name = "所属管辖内部部门号", width = 15)
    @ApiModelProperty(value = "所属管辖内部部门号")
	private String upDepNo;
	/**所属法人联社*/
	@Excel(name = "所属法人联社", width = 15)
    @ApiModelProperty(value = "所属法人联社")
	private String upBrNo;
	/**是否是账务部门*/
	@Excel(name = "是否是账务部门", width = 15)
    @ApiModelProperty(value = "是否是账务部门")
	private String dcInd;
	/**默认内部记账机构*/
	@Excel(name = "默认内部记账机构", width = 15)
    @ApiModelProperty(value = "默认内部记账机构")
	private String dcBrNo;
	/**部门负责人姓名*/
	@Excel(name = "部门负责人姓名", width = 15)
    @ApiModelProperty(value = "部门负责人姓名")
	private String depManager;
	/**部门负责人电话*/
	@Excel(name = "部门负责人电话", width = 15)
    @ApiModelProperty(value = "部门负责人电话")
	private String depPhone;
	/**部门类型*/
	@Excel(name = "部门类型", width = 15)
    @ApiModelProperty(value = "部门类型")
	private String depType;
	/**责任单位*/
	@Excel(name = "责任单位", width = 15)
    @ApiModelProperty(value = "责任单位")
	private String costProfitInd;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String depSts;
	/**营业状态*/
	@Excel(name = "营业状态", width = 15)
    @ApiModelProperty(value = "营业状态")
	private String wrkSts;
	/**网点IP地址*/
	@Excel(name = "网点IP地址", width = 15)
    @ApiModelProperty(value = "网点IP地址")
	private String ipaddr;
	/**通存通兑标志*/
	@Excel(name = "通存通兑标志", width = 15)
    @ApiModelProperty(value = "通存通兑标志")
	private String trfInd;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String filler;
	/**部门级别*/
	@Excel(name = "部门级别", width = 15)
    @ApiModelProperty(value = "部门级别")
	private String depLvl;
	/**是否有下级部门*/
	@Excel(name = "是否有下级部门", width = 15)
    @ApiModelProperty(value = "是否有下级部门")
	private String subYn;
	/**部门邮箱*/
	@Excel(name = "部门邮箱", width = 15)
    @ApiModelProperty(value = "部门邮箱")
	private String depMail;
	/**部门职能类型*/
	@Excel(name = "部门职能类型", width = 15)
    @ApiModelProperty(value = "部门职能类型")
	private String funcType;
	/**法人类型*/
	@Excel(name = "法人类型", width = 15)
    @ApiModelProperty(value = "法人类型")
	private String corpType;
	/**是否是独立法人*/
	@Excel(name = "是否是独立法人", width = 15)
    @ApiModelProperty(value = "是否是独立法人")
	private String blCorp;
	/**部门号*/
	@Excel(name = "部门号", width = 15)
    @ApiModelProperty(value = "部门号")
	private String curDepNo;
	/**上级部门号*/
	@Excel(name = "上级部门号", width = 15)
    @ApiModelProperty(value = "上级部门号")
	private String upCurDepNo;
	/**标记市机关管理哪个市*/
	@Excel(name = "标记市机关管理哪个市", width = 15)
    @ApiModelProperty(value = "标记市机关管理哪个市")
	private String cityDepNo;
	/**所属外部法人联社号*/
	@Excel(name = "所属外部法人联社号", width = 15)
    @ApiModelProperty(value = "所属外部法人联社号")
	private String curUpBrNo;
	/**默认记账机构号*/
	@Excel(name = "默认记账机构号", width = 15)
    @ApiModelProperty(value = "默认记账机构号")
	private String curDcBrNo;
	/**默认树类型*/
	@Excel(name = "默认树类型", width = 15)
    @ApiModelProperty(value = "默认树类型")
	private String queryTree;
	/**是否汇总部门*/
	@Excel(name = "是否汇总部门", width = 15)
    @ApiModelProperty(value = "是否汇总部门")
	private String isGather;
	/**是否上划部门*/
	@Excel(name = "是否上划部门", width = 15)
    @ApiModelProperty(value = "是否上划部门")
	private String shFlag;
	/**部门库存现金限额上限*/
	@Excel(name = "部门库存现金限额上限", width = 15)
    @ApiModelProperty(value = "部门库存现金限额上限")
	private java.math.BigDecimal amtBlanceMax;
	/**部门库存现金限额下限*/
	@Excel(name = "部门库存现金限额下限", width = 15)
    @ApiModelProperty(value = "部门库存现金限额下限")
	private java.math.BigDecimal amtBlanceMin;
	/**部门序号*/
	@Excel(name = "部门序号", width = 15)
    @ApiModelProperty(value = "部门序号")
	private String depSeqn;
	/**对外支付渠道所属机构*/
	@Excel(name = "对外支付渠道所属机构", width = 15)
    @ApiModelProperty(value = "对外支付渠道所属机构")
	private String hxAcDepNo;
	/**核心并账机构号*/
	@Excel(name = "核心并账机构号", width = 15)
    @ApiModelProperty(value = "核心并账机构号")
	private String hxDcBrNo;
	/**部门简称*/
	@Excel(name = "部门简称", width = 15)
    @ApiModelProperty(value = "部门简称")
	private String shortName;
	/**币种与年终状态对应标志位*/
	@Excel(name = "币种与年终状态对应标志位", width = 15)
    @ApiModelProperty(value = "币种与年终状态对应标志位")
	private String curBit;
	/**上划交易区别标志*/
	@Excel(name = "上划交易区别标志", width = 15)
    @ApiModelProperty(value = "上划交易区别标志")
	private String shDif;
	/**上级管理机构*/
	@Excel(name = "上级管理机构", width = 15)
    @ApiModelProperty(value = "上级管理机构")
	private String mngUpBrNo;
	/**机构类型*/
	@Excel(name = "机构类型", width = 15)
    @ApiModelProperty(value = "机构类型")
	private String ywType;
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
