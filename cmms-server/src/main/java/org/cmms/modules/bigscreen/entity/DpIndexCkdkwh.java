package org.cmms.modules.bigscreen.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
 * @Description: 首页存款贷款我行风采
 * @Author: jeecg-boot
 * @Date:   2023-11-07
 * @Version: V1.0
 */
@Data
@TableName("DP_INDEX_CKDKWH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DP_INDEX_CKDKWH对象", description="首页存款贷款我行风采")
public class DpIndexCkdkwh {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private BigDecimal dkje;
	/**贷款金额较上月*/
	@Excel(name = "贷款金额较上月", width = 15)
    @ApiModelProperty(value = "贷款金额较上月")
	private BigDecimal dkjejsy;
	/**贷款金额较年初*/
	@Excel(name = "贷款金额较年初", width = 15)
    @ApiModelProperty(value = "贷款金额较年初")
	private BigDecimal dkjejnc;
	/**正常*/
	@Excel(name = "正常", width = 15)
    @ApiModelProperty(value = "正常")
	private BigDecimal dkjezc;
	/**关注*/
	@Excel(name = "关注", width = 15)
    @ApiModelProperty(value = "关注")
	private BigDecimal dkjegz;
	/**可以*/
	@Excel(name = "可以", width = 15)
    @ApiModelProperty(value = "可以")
	private BigDecimal dkjeky;
	/**次级*/
	@Excel(name = "次级", width = 15)
    @ApiModelProperty(value = "次级")
	private BigDecimal dkjecj;
	/**损失*/
	@Excel(name = "损失", width = 15)
    @ApiModelProperty(value = "损失")
	private BigDecimal dkjess;
	/**贷款客户*/
	@Excel(name = "贷款客户", width = 15)
    @ApiModelProperty(value = "贷款客户")
	private BigDecimal dkkh;
	/**贷款客户较上月*/
	@Excel(name = "贷款客户较上月", width = 15)
    @ApiModelProperty(value = "贷款客户较上月")
	private BigDecimal dkkhjsy;
	/**贷款客户较年初*/
	@Excel(name = "贷款客户较年初", width = 15)
    @ApiModelProperty(value = "贷款客户较年初")
	private BigDecimal dkkhjnc;
	/**60后*/
	@Excel(name = "60后", width = 15)
    @ApiModelProperty(value = "60后")
	private BigDecimal dkkh60;
	/**70后*/
	@Excel(name = "70后", width = 15)
    @ApiModelProperty(value = "70后")
	private BigDecimal dkkh70;
	/**80后*/
	@Excel(name = "80后", width = 15)
    @ApiModelProperty(value = "80后")
	private BigDecimal dkkh80;
	/**90后*/
	@Excel(name = "90后", width = 15)
    @ApiModelProperty(value = "90后")
	private BigDecimal dkkh90;
	/**00后*/
	@Excel(name = "00后", width = 15)
    @ApiModelProperty(value = "00后")
	private BigDecimal dkkh00;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
	private BigDecimal dkkhqt;
	/**对公*/
	@Excel(name = "对公", width = 15)
    @ApiModelProperty(value = "对公")
	private BigDecimal dkkhdg;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private BigDecimal ckye;
	/**存款余额较年初*/
	@Excel(name = "存款余额较年初", width = 15)
    @ApiModelProperty(value = "存款余额较年初")
	private BigDecimal ckyejnc;
	/**低息比*/
	@Excel(name = "低息比", width = 15)
    @ApiModelProperty(value = "低息比")
	private BigDecimal dxb;
	/**低息比较年初*/
	@Excel(name = "低息比较年初", width = 15)
    @ApiModelProperty(value = "低息比较年初")
	private BigDecimal dxbjnc;
	/**存款比*/
	@Excel(name = "存款比", width = 15)
    @ApiModelProperty(value = "存款比")
	private BigDecimal cdk;
	/**存贷比较年初*/
	@Excel(name = "存贷比较年初", width = 15)
    @ApiModelProperty(value = "存贷比较年初")
	private BigDecimal cdkjnc;
	/**付息率*/
	@Excel(name = "付息率", width = 15)
    @ApiModelProperty(value = "付息率")
	private BigDecimal fxl;
	/**付息率较年初*/
	@Excel(name = "付息率较年初", width = 15)
    @ApiModelProperty(value = "付息率较年初")
	private BigDecimal fxljnc;
	/**存款活期*/
	@Excel(name = "存款活期", width = 15)
    @ApiModelProperty(value = "存款活期")
	private BigDecimal ckhq;
	/**存款一年*/
	@Excel(name = "存款一年", width = 15)
    @ApiModelProperty(value = "存款一年")
	private BigDecimal ckyn;
	/**存款两年*/
	@Excel(name = "存款两年", width = 15)
    @ApiModelProperty(value = "存款两年")
	private BigDecimal ckln;
	/**存款三年*/
	@Excel(name = "存款三年", width = 15)
    @ApiModelProperty(value = "存款三年")
	private BigDecimal cksn;
	/**存款五年*/
	@Excel(name = "存款五年", width = 15)
    @ApiModelProperty(value = "存款五年")
	private BigDecimal ckwn;
	/**存款其他*/
	@Excel(name = "存款其他", width = 15)
    @ApiModelProperty(value = "存款其他")
	private BigDecimal ckqt;
	/**支行数量*/
	@Excel(name = "支行数量", width = 15)
    @ApiModelProperty(value = "支行数量")
	private BigDecimal zhsl;
	/**分理处数量*/
	@Excel(name = "分理处数量", width = 15)
    @ApiModelProperty(value = "分理处数量")
	private BigDecimal flcsl;
	/**部室数量*/
	@Excel(name = "部室数量", width = 15)
    @ApiModelProperty(value = "部室数量")
	private BigDecimal bssl;
	/**在职员工数量*/
	@Excel(name = "在职员工数量", width = 15)
    @ApiModelProperty(value = "在职员工数量")
	private BigDecimal zzygsl;
	/**员工男数量*/
	@Excel(name = "员工男数量", width = 15)
    @ApiModelProperty(value = "员工男数量")
	private BigDecimal ygnan;
	/**员工女数量*/
	@Excel(name = "员工女数量", width = 15)
    @ApiModelProperty(value = "员工女数量")
	private BigDecimal ygnv;
	/**70后*/
	@Excel(name = "70后", width = 15)
    @ApiModelProperty(value = "70后")
	private BigDecimal yg70;
	/**80后*/
	@Excel(name = "80后", width = 15)
    @ApiModelProperty(value = "80后")
	private BigDecimal yg80;
	/**90后*/
	@Excel(name = "90后", width = 15)
    @ApiModelProperty(value = "90后")
	private BigDecimal yg90;
	/**00后*/
	@Excel(name = "00后", width = 15)
    @ApiModelProperty(value = "00后")
	private BigDecimal yg00;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
	private BigDecimal ygqt;
	@TableField(exist = false)
	private String tjrq;
	@TableField(exist = false)
	private char[] dkjeArr;
	@TableField(exist = false)
	private char[] dkkhArr;
}
