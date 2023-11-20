package org.cmms.modules.sjxf.qtxt.ods.xjjgdz.entity;

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
 * @Description: 新旧机构对照
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Organ_con")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Organ_con对象", description="新旧机构对照")
public class Xjjgdz {
    
	/**新机构号*/
	@Excel(name = "新机构号", width = 15)
    @ApiModelProperty(value = "新机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String newBrNo;
	/**旧机构号*/
	@Excel(name = "旧机构号", width = 15)
    @ApiModelProperty(value = "旧机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String oldBrNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String brName;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String tel;
	/**上级管理机构（老财务）*/
	@Excel(name = "上级管理机构（老财务）", width = 15)
    @ApiModelProperty(value = "上级管理机构（老财务）")
	private String sjgljg;
	/**上级清算关系（老核心）*/
	@Excel(name = "上级清算关系（老核心）", width = 15)
    @ApiModelProperty(value = "上级清算关系（老核心）")
	private String sjqsgx;
	/**法人联社编号（老财务）*/
	@Excel(name = "法人联社编号（老财务）", width = 15)
    @ApiModelProperty(value = "法人联社编号（老财务）")
	private String frlsbh;
	/**法人联社编号*/
	@Excel(name = "法人联社编号", width = 15)
    @ApiModelProperty(value = "法人联社编号")
	private String frlsbh1;
	/**支付系统行号*/
	@Excel(name = "支付系统行号", width = 15)
    @ApiModelProperty(value = "支付系统行号")
	private String zfxthh;
	/**国结(SWIFT)地址*/
	@Excel(name = "国结(SWIFT)地址", width = 15)
    @ApiModelProperty(value = "国结(SWIFT)地址")
	private String gjdz;
	/**银行机构代码（新核心）*/
	@Excel(name = "银行机构代码（新核心）", width = 15)
    @ApiModelProperty(value = "银行机构代码（新核心）")
	private String yhjgdm;
	/**金融机构许可证（新核心）*/
	@Excel(name = "金融机构许可证（新核心）", width = 15)
    @ApiModelProperty(value = "金融机构许可证（新核心）")
	private String jrjgxkz;
	/**是否启用银承（新核心）*/
	@Excel(name = "是否启用银承（新核心）", width = 15)
    @ApiModelProperty(value = "是否启用银承（新核心）")
	private String sfqyyc;
	/**联社级管理中心（新核心）*/
	@Excel(name = "联社级管理中心（新核心）", width = 15)
    @ApiModelProperty(value = "联社级管理中心（新核心）")
	private String lsjglzx;
	/**上级分行代码（新核心）*/
	@Excel(name = "上级分行代码（新核心）", width = 15)
    @ApiModelProperty(value = "上级分行代码（新核心）")
	private String sjfhdm;
	/**上级业务机构（新核心）*/
	@Excel(name = "上级业务机构（新核心）", width = 15)
    @ApiModelProperty(value = "上级业务机构（新核心）")
	private String sjywjg;
	/**上级清算机构（新核心）*/
	@Excel(name = "上级清算机构（新核心）", width = 15)
    @ApiModelProperty(value = "上级清算机构（新核心）")
	private String sjqsjg;
	/**上级现金机构（新核心）*/
	@Excel(name = "上级现金机构（新核心）", width = 15)
    @ApiModelProperty(value = "上级现金机构（新核心）")
	private String sjxjjg;
	/**上级重空机构（新核心）*/
	@Excel(name = "上级重空机构（新核心）", width = 15)
    @ApiModelProperty(value = "上级重空机构（新核心）")
	private String sjzkjg;
	/**银行卡城市代码*/
	@Excel(name = "银行卡城市代码", width = 15)
    @ApiModelProperty(value = "银行卡城市代码")
	private String yhkcsdm;
	/**城市代码*/
	@Excel(name = "城市代码", width = 15)
    @ApiModelProperty(value = "城市代码")
	private String csdm;
	/**机构使用状态*/
	@Excel(name = "机构使用状态", width = 15)
    @ApiModelProperty(value = "机构使用状态")
	private String jgsyzt;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String organId;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
