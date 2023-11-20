package org.cmms.modules.sjxf.xdxt.ywjjkzb.entity;

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
 * @Description: 业务借据扩展表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_bus_due_addi_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_bus_due_addi_info对象", description="业务借据扩展表")
public class Ywjjkzb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**itemInfoId*/
	@Excel(name = "itemInfoId", width = 15)
    @ApiModelProperty(value = "itemInfoId")
	private String itemInfoId;
	/**itemInfoType*/
	@Excel(name = "itemInfoType", width = 15)
    @ApiModelProperty(value = "itemInfoType")
	private String itemInfoType;
	/**itemRelaId*/
	@Excel(name = "itemRelaId", width = 15)
    @ApiModelProperty(value = "itemRelaId")
	private String itemRelaId;
	/**itemRelaNo*/
	@Excel(name = "itemRelaNo", width = 15)
    @ApiModelProperty(value = "itemRelaNo")
	private String itemRelaNo;
	/**调整前五级分类形态*/
	@Excel(name = "调整前五级分类形态", width = 15)
    @ApiModelProperty(value = "调整前五级分类形态")
	private String item001;
	/**学生证号*/
	@Excel(name = "学生证号", width = 15)
    @ApiModelProperty(value = "学生证号")
	private String item002;
	/**助学贷款类型*/
	@Excel(name = "助学贷款类型", width = 15)
    @ApiModelProperty(value = "助学贷款类型")
	private String item003;
	/**是否小额贷款公司经营贷款1:是2:否*/
	@Excel(name = "是否小额贷款公司经营贷款1:是2:否", width = 15)
    @ApiModelProperty(value = "是否小额贷款公司经营贷款1:是2:否")
	private String item004;
	/**item005*/
	@Excel(name = "item005", width = 15)
    @ApiModelProperty(value = "item005")
	private String item005;
	/**学校行政区码[省]*/
	@Excel(name = "学校行政区码[省]", width = 15)
    @ApiModelProperty(value = "学校行政区码[省]")
	private String item006;
	/**学校行政区码[市]*/
	@Excel(name = "学校行政区码[市]", width = 15)
    @ApiModelProperty(value = "学校行政区码[市]")
	private String item007;
	/**学校行政区码[县]*/
	@Excel(name = "学校行政区码[县]", width = 15)
    @ApiModelProperty(value = "学校行政区码[县]")
	private String item008;
	/**贷款时家庭住址行政区码[县]*/
	@Excel(name = "贷款时家庭住址行政区码[县]", width = 15)
    @ApiModelProperty(value = "贷款时家庭住址行政区码[县]")
	private String item009;
	/**贷款时家庭住址行政区码[省]*/
	@Excel(name = "贷款时家庭住址行政区码[省]", width = 15)
    @ApiModelProperty(value = "贷款时家庭住址行政区码[省]")
	private String item010;
	/**学校名称*/
	@Excel(name = "学校名称", width = 15)
    @ApiModelProperty(value = "学校名称")
	private String item011;
	/**学校地址*/
	@Excel(name = "学校地址", width = 15)
    @ApiModelProperty(value = "学校地址")
	private String item012;
	/**学校地址[部分]*/
	@Excel(name = "学校地址[部分]", width = 15)
    @ApiModelProperty(value = "学校地址[部分]")
	private String item013;
	/**贷款时家庭住址*/
	@Excel(name = "贷款时家庭住址", width = 15)
    @ApiModelProperty(value = "贷款时家庭住址")
	private String item014;
	/**贷款时家庭住址[部分]*/
	@Excel(name = "贷款时家庭住址[部分]", width = 15)
    @ApiModelProperty(value = "贷款时家庭住址[部分]")
	private String item015;
	/**贷款时家庭住址行政区码[市]*/
	@Excel(name = "贷款时家庭住址行政区码[市]", width = 15)
    @ApiModelProperty(value = "贷款时家庭住址行政区码[市]")
	private String item016;
	/**水利建设贷款类型1*/
	@Excel(name = "水利建设贷款类型1", width = 15)
    @ApiModelProperty(value = "水利建设贷款类型1")
	private String item017;
	/**水利建设贷款类型2*/
	@Excel(name = "水利建设贷款类型2", width = 15)
    @ApiModelProperty(value = "水利建设贷款类型2")
	private String item018;
	/**融资平台贷款类型1*/
	@Excel(name = "融资平台贷款类型1", width = 15)
    @ApiModelProperty(value = "融资平台贷款类型1")
	private String item019;
	/**融资平台贷款类型2*/
	@Excel(name = "融资平台贷款类型2", width = 15)
    @ApiModelProperty(value = "融资平台贷款类型2")
	private String item020;
	/**融资平台贷款投向1*/
	@Excel(name = "融资平台贷款投向1", width = 15)
    @ApiModelProperty(value = "融资平台贷款投向1")
	private String item021;
	/**融资平台贷款投向2*/
	@Excel(name = "融资平台贷款投向2", width = 15)
    @ApiModelProperty(value = "融资平台贷款投向2")
	private String item022;
	/**融资平台贷款投向3*/
	@Excel(name = "融资平台贷款投向3", width = 15)
    @ApiModelProperty(value = "融资平台贷款投向3")
	private String item023;
	/**融资平台贷款偿债资金来源*/
	@Excel(name = "融资平台贷款偿债资金来源", width = 15)
    @ApiModelProperty(value = "融资平台贷款偿债资金来源")
	private String item024;
	/**融资平台按隶属关系分类*/
	@Excel(name = "融资平台按隶属关系分类", width = 15)
    @ApiModelProperty(value = "融资平台按隶属关系分类")
	private String item025;
	/**item026*/
	@Excel(name = "item026", width = 15)
    @ApiModelProperty(value = "item026")
	private String item026;
	/**item027*/
	@Excel(name = "item027", width = 15)
    @ApiModelProperty(value = "item027")
	private String item027;
	/**item028*/
	@Excel(name = "item028", width = 15)
    @ApiModelProperty(value = "item028")
	private String item028;
	/**item029*/
	@Excel(name = "item029", width = 15)
    @ApiModelProperty(value = "item029")
	private String item029;
	/**item030*/
	@Excel(name = "item030", width = 15)
    @ApiModelProperty(value = "item030")
	private String item030;
	/**是否铁路、公路基础设施建设贷款*/
	@Excel(name = "是否铁路、公路基础设施建设贷款", width = 15)
    @ApiModelProperty(value = "是否铁路、公路基础设施建设贷款")
	private String item031;
	/**item032*/
	@Excel(name = "item032", width = 15)
    @ApiModelProperty(value = "item032")
	private String item032;
	/**item033*/
	@Excel(name = "item033", width = 15)
    @ApiModelProperty(value = "item033")
	private String item033;
	/**item034*/
	@Excel(name = "item034", width = 15)
    @ApiModelProperty(value = "item034")
	private String item034;
	/**item035*/
	@Excel(name = "item035", width = 15)
    @ApiModelProperty(value = "item035")
	private String item035;
	/**item036*/
	@Excel(name = "item036", width = 15)
    @ApiModelProperty(value = "item036")
	private String item036;
	/**item037*/
	@Excel(name = "item037", width = 15)
    @ApiModelProperty(value = "item037")
	private String item037;
	/**item038*/
	@Excel(name = "item038", width = 15)
    @ApiModelProperty(value = "item038")
	private String item038;
	/**item039*/
	@Excel(name = "item039", width = 15)
    @ApiModelProperty(value = "item039")
	private String item039;
	/**item040*/
	@Excel(name = "item040", width = 15)
    @ApiModelProperty(value = "item040")
	private String item040;
	/**item100*/
	@Excel(name = "item100", width = 15)
    @ApiModelProperty(value = "item100")
	private String item100;
	/**item101*/
	@Excel(name = "item101", width = 15)
    @ApiModelProperty(value = "item101")
	private String item101;
	/**item102*/
	@Excel(name = "item102", width = 15)
    @ApiModelProperty(value = "item102")
	private String item102;
	/**item111*/
	@Excel(name = "item111", width = 15)
    @ApiModelProperty(value = "item111")
	private String item111;
	/**item112*/
	@Excel(name = "item112", width = 15)
    @ApiModelProperty(value = "item112")
	private String item112;
	/**item200*/
	@Excel(name = "item200", width = 15)
    @ApiModelProperty(value = "item200")
	private String item200;
	/**item201*/
	@Excel(name = "item201", width = 15)
    @ApiModelProperty(value = "item201")
	private String item201;
	/**item202*/
	@Excel(name = "item202", width = 15)
    @ApiModelProperty(value = "item202")
	private String item202;
	/**item203*/
	@Excel(name = "item203", width = 15)
    @ApiModelProperty(value = "item203")
	private String item203;
	/**item204*/
	@Excel(name = "item204", width = 15)
    @ApiModelProperty(value = "item204")
	private String item204;
	/**item205*/
	@Excel(name = "item205", width = 15)
    @ApiModelProperty(value = "item205")
	private String item205;
	/**item300*/
	@Excel(name = "item300", width = 15)
    @ApiModelProperty(value = "item300")
	private java.math.BigDecimal item300;
	/**item301*/
	@Excel(name = "item301", width = 15)
    @ApiModelProperty(value = "item301")
	private java.math.BigDecimal item301;
	/**item302*/
	@Excel(name = "item302", width = 15)
    @ApiModelProperty(value = "item302")
	private java.math.BigDecimal item302;
	/**item303*/
	@Excel(name = "item303", width = 15)
    @ApiModelProperty(value = "item303")
	private java.math.BigDecimal item303;
	/**item304*/
	@Excel(name = "item304", width = 15)
    @ApiModelProperty(value = "item304")
	private java.math.BigDecimal item304;
	/**item305*/
	@Excel(name = "item305", width = 15)
    @ApiModelProperty(value = "item305")
	private java.math.BigDecimal item305;
	/**item306*/
	@Excel(name = "item306", width = 15)
    @ApiModelProperty(value = "item306")
	private java.math.BigDecimal item306;
	/**item307*/
	@Excel(name = "item307", width = 15)
    @ApiModelProperty(value = "item307")
	private java.math.BigDecimal item307;
	/**item308*/
	@Excel(name = "item308", width = 15)
    @ApiModelProperty(value = "item308")
	private java.math.BigDecimal item308;
	/**item309*/
	@Excel(name = "item309", width = 15)
    @ApiModelProperty(value = "item309")
	private java.math.BigDecimal item309;
	/**item310*/
	@Excel(name = "item310", width = 15)
    @ApiModelProperty(value = "item310")
	private java.math.BigDecimal item310;
	/**item311*/
	@Excel(name = "item311", width = 15)
    @ApiModelProperty(value = "item311")
	private java.math.BigDecimal item311;
	/**item312*/
	@Excel(name = "item312", width = 15)
    @ApiModelProperty(value = "item312")
	private String item312;
	/**item313*/
	@Excel(name = "item313", width = 15)
    @ApiModelProperty(value = "item313")
	private String item313;
	/**item314*/
	@Excel(name = "item314", width = 15)
    @ApiModelProperty(value = "item314")
	private String item314;
	/**item315*/
	@Excel(name = "item315", width = 15)
    @ApiModelProperty(value = "item315")
	private String item315;
	/**item400*/
	@Excel(name = "item400", width = 15)
    @ApiModelProperty(value = "item400")
	private String item400;
	/**item401*/
	@Excel(name = "item401", width = 15)
    @ApiModelProperty(value = "item401")
	private String item401;
	/**item402*/
	@Excel(name = "item402", width = 15)
    @ApiModelProperty(value = "item402")
	private String item402;
	/**item403*/
	@Excel(name = "item403", width = 15)
    @ApiModelProperty(value = "item403")
	private String item403;
	/**约定还款日*/
	@Excel(name = "约定还款日", width = 15)
    @ApiModelProperty(value = "约定还款日")
	private String item404;
	/**item405*/
	@Excel(name = "item405", width = 15)
    @ApiModelProperty(value = "item405")
	private String item405;
	/**item500*/
	@Excel(name = "item500", width = 15)
    @ApiModelProperty(value = "item500")
	private java.math.BigDecimal item500;
	/**item501*/
	@Excel(name = "item501", width = 15)
    @ApiModelProperty(value = "item501")
	private java.math.BigDecimal item501;
	/**item502*/
	@Excel(name = "item502", width = 15)
    @ApiModelProperty(value = "item502")
	private java.math.BigDecimal item502;
	/**item503*/
	@Excel(name = "item503", width = 15)
    @ApiModelProperty(value = "item503")
	private java.math.BigDecimal item503;
	/**item504*/
	@Excel(name = "item504", width = 15)
    @ApiModelProperty(value = "item504")
	private java.math.BigDecimal item504;
	/**userId*/
	@Excel(name = "userId", width = 15)
    @ApiModelProperty(value = "userId")
	private String userId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**updateDate*/
	@Excel(name = "updateDate", width = 15)
    @ApiModelProperty(value = "updateDate")
	private String updateDate;
	/**借据号码*/
	@Excel(name = "借据号码", width = 15)
    @ApiModelProperty(value = "借据号码")
	private String voucherNo;
	/**起始日期*/
	@Excel(name = "起始日期", width = 15)
    @ApiModelProperty(value = "起始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
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
