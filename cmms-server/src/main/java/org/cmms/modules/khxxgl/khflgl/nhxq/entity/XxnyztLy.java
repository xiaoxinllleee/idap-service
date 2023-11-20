package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 新型农业主体浏阳
 * @Author: jeecg-boot
 * @Date:   2023-06-30
 * @Version: V1.0
 */
@Data
@TableName("XXNYZT_LY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXNYZT_LY对象", description="新型农业主体浏阳")
public class XxnyztLy {
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String creator;
	/**创建时间*/
	//@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "修改人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String updatot;
	/**修改时间*/
	//@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**网格编号*/
	//@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**经营主体名称*/
	@Excel(name = "经营主体名称", width = 15)
    @ApiModelProperty(value = "经营主体名称")
	private String ztmc;
	/**主体类别 家庭农场，专业合作社，农业产业化企业，农村集体经济组织，种养大户，其他*/
	@Excel(name = "主体类别", width = 15,dicCode = "ly_xxnyzt_ztlb")
    @ApiModelProperty(value = "主体类别 家庭农场，专业合作社，农业产业化企业，农村集体经济组织，种养大户，其他")
	@Dict(dicCode = "ly_xxnyzt_ztlb")
	private String ztlb;
	/**经营年限*/
	@Excel(name = "经营年限", width = 15)
    @ApiModelProperty(value = "经营年限")
	private String jynx;
	/**社会信用代码*/
	@Excel(name = "社会信用代码", width = 15)
    @ApiModelProperty(value = "社会信用代码")
	private String shxydm;
	/**负责人名称*/
	@Excel(name = "负责人名称", width = 15)
    @ApiModelProperty(value = "负责人名称")
	private String khmc;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String zjhm;
	/**婚姻状况*/
	@Excel(name = "婚否", width = 15)
    @ApiModelProperty(value = "婚否")
	@Dict(dicCode = "sfbz")
	private String hyzk;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**认定情况 国家级，省级，市级，县级，无*/
	@Excel(name = "认定情况", width = 15,dicCode = "ly_xxnyzt_rdqk")
    @ApiModelProperty(value = "认定情况 国家级，省级，市级，县级，无")
	@Dict(dicCode = "ly_xxnyzt_rdqk")
	private String rdqk;
	/**经营状态 存续，停业，注销，其他*/
	@Excel(name = "经营状态", width = 15,dicCode = "ly_xxnyzt_jyzt")
    @ApiModelProperty(value = "经营状态 存续，停业，注销，其他")
	@Dict(dicCode = "ly_xxnyzt_jyzt")
	private String jxzt;
	/**是否首贷户*/
	@Excel(name = "是否首贷户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否首贷户")
	@Dict(dicCode = "sfbz")
	private String sfsdh;
	/**经营地址*/
	@Excel(name = "经营地址", width = 15)
    @ApiModelProperty(value = "经营地址")
	private String jydz;
	/**经营用地根源*/
	@Excel(name = "经营用地根源", width = 15,dicCode = "ly_xxnyzt_jyydgy")
    @ApiModelProperty(value = "经营用地根源")
	@Dict(dicCode = "ly_xxnyzt_jyydgy")
	private String jyydgy;
	/**流转土地面积（亩）*/
	@Excel(name = "流转土地面积（亩）", width = 15)
    @ApiModelProperty(value = "流转土地面积（亩）")
	private BigDecimal lztdmj;
	/**流转土地成本（元/亩）*/
	@Excel(name = "流转土地成本（元/亩）", width = 15)
    @ApiModelProperty(value = "流转土地成本（元/亩）")
	private BigDecimal lztdcb;
	/**流转土地限期起*/
	@Excel(name = "流转土地限期起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "流转土地限期起")
	private Date lztdqxq;
	/**流转土地限期止*/
	@Excel(name = "流转土地限期止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "流转土地限期止")
	private Date lztdqxz;
	/**经营行业：粮食栽种，畜牧水产养殖，菜果茶栽种，农资丶农机丶农机等社会化服务，药材丶花卉丶林木栽种，家庭休闲丶观光农业，农产品加工，贸易流通，其他*/
	@Excel(name = "经营行业", width = 15,dicCode = "ly_xxnyzt_jyhy")
    @ApiModelProperty(value = "经营行业：粮食栽种，畜牧水产养殖，菜果茶栽种，农资丶农机丶农机等社会化服务，药材丶花卉丶林木栽种，家庭休闲丶观光农业，农产品加工，贸易流通，其他")
	@Dict(dicCode = "ly_xxnyzt_jyhy")
	private String jyhy;
	/**其他经营品种及产值*/
	@Excel(name = "其他经营品种及产值", width = 15)
    @ApiModelProperty(value = "其他经营品种及产值")
	private BigDecimal qtjypzjcz;
	/**年经营收入*/
	@Excel(name = "年经营收入", width = 15)
    @ApiModelProperty(value = "年经营收入")
	private BigDecimal njysr;
	/**利润总额*/
	@Excel(name = "利润总额", width = 15)
    @ApiModelProperty(value = "利润总额")
	private BigDecimal lrze;
	/**现金及存款*/
	@Excel(name = "现金及存款", width = 15)
    @ApiModelProperty(value = "现金及存款")
	private BigDecimal xjjck;
	/**应收账款*/
	@Excel(name = "应收账款", width = 15)
    @ApiModelProperty(value = "应收账款")
	private BigDecimal yszk;
	/**其他固定资产*/
	@Excel(name = "其他固定资产", width = 15)
    @ApiModelProperty(value = "其他固定资产")
	private BigDecimal qtgdzc;
	/**其他流动资产*/
	@Excel(name = "其他流动资产", width = 15)
    @ApiModelProperty(value = "其他流动资产")
	private BigDecimal qtldzc;
	/**资产合计*/
	@Excel(name = "资产合计", width = 15)
    @ApiModelProperty(value = "资产合计")
	private BigDecimal zchj;
	/**本行借款*/
	@Excel(name = "本行借款", width = 15)
    @ApiModelProperty(value = "本行借款")
	private BigDecimal bhjk;
	/**他行借款*/
	@Excel(name = "他行借款", width = 15)
    @ApiModelProperty(value = "他行借款")
	private BigDecimal thjk;
	/**其他借款*/
	@Excel(name = "其他借款", width = 15)
    @ApiModelProperty(value = "其他借款")
	private BigDecimal qtjk;
	/**应付账款*/
	@Excel(name = "应付账款", width = 15)
    @ApiModelProperty(value = "应付账款")
	private BigDecimal yfzk;
	/**其他负责*/
	@Excel(name = "其他负责", width = 15)
    @ApiModelProperty(value = "其他负责")
	private BigDecimal qtfz;
	/**为他人担保*/
	@Excel(name = "为他人担保", width = 15)
    @ApiModelProperty(value = "为他人担保")
	private BigDecimal wtrdb;
	/**有无违法行为*/
	@Excel(name = "有无违法行为", width = 15)
    @ApiModelProperty(value = "有无违法行为")
	@Dict(dicCode = "ywbz")
	private String ywwfxw;
	/**行业评价 好，较好，一般，差*/
	@Excel(name = "行业评价", width = 15,dicCode = "ly_xxnyzt_hypj")
    @ApiModelProperty(value = "行业评价 好，较好，一般，差")
	@Dict(dicCode = "ly_xxnyzt_hypj")
	private String hypj;
	/**近2年人行征信逾期次数*/
	@Excel(name = "近2年人行征信逾期次数", width = 15)
    @ApiModelProperty(value = "近2年人行征信逾期次数")
	private BigDecimal rhyqcs;
	/**内部征信逾期次数*/
	@Excel(name = "内部征信逾期次数", width = 15)
    @ApiModelProperty(value = "内部征信逾期次数")
	private BigDecimal nbzxyqcs;
	/**是否小额农贷*/
	@Excel(name = "是否小额农贷", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否小额农贷")
	@Dict(dicCode = "sfbz")
	private String sfxend;
	/**评定等级*/
	@Excel(name = "评定等级", width = 15)
    @ApiModelProperty(value = "评定等级")
	private String pddj;
	/**授信金额*/
	@Excel(name = "小额农贷已授信", width = 15)
    @ApiModelProperty(value = "授信金额")
	private BigDecimal sxje;
	/**初评等级*/
	//@Excel(name = "初评等级", width = 15)
    @ApiModelProperty(value = "初评等级")
	private String cpdj;
	private String fpdj;
	/**初评金额*/
	//@Excel(name = "初评金额", width = 15)
    @ApiModelProperty(value = "初评金额")
	private BigDecimal cpje;
	@Excel(name = "主体已授信额度", width = 15)
	private BigDecimal fpje;
	//
	@Excel(name = "负债合计", width = 15)
	@ApiModelProperty(value = "负债合计")
	private BigDecimal fzhj;
	@Excel(name = "客户融资需求", width = 15)
	private BigDecimal khrzxq;
	@Excel(name = "客户经理授信意向", width = 15)
	private BigDecimal khjlsxyx;
	@Dict(dicCode = "dbfs")
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs")
	private String dbfs;
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	@Dict(dicCode = "ly_xxnyzt_pdlx")
	@Excel(name = "评定类型", width = 15,dicCode = "ly_xxnyzt_pdlx")
	private String pdlx;
	@Excel(name = "待定理由", width = 15,dicCode = "ly_xxnyzt_ddly")
	@Dict(dicCode = "ly_xxnyzt_ddly")
	private String ddly;
	@Excel(name = "其他待定理由")
	private String qtly;
	@TableField(exist = false)
	private String yjyzzhy;
	//@Excel(name = "主体贷款金额", width = 15)
	private BigDecimal dkje;
	@Excel(name = "负责人借款金额", width = 15)
	private BigDecimal fzrdkje;
	@Excel(name = "主体存款金额", width = 15)
	private BigDecimal ckje;
	@Excel(name = "负责人存款金额", width = 15)
	private BigDecimal fzrckje;
	@Excel(name = "审批额度", width = 15)
	private BigDecimal sped;
	@Excel(name = "净资产", width = 15)
	private BigDecimal jsr;
	@Dict(dicCode = "sfbz")
	@Excel(name = "是否黑名单", width = 15,dicCode = "sfbz")
	private String sfhmd;
	@Excel(name = "备注1（备注下面有内容的属于名单内）", width = 15)
	private String bz1;
	@Excel(name = "备注2（是否浏阳市农业社会化服务主体）", width = 15)
	private String bz2;
	@Excel(name = "备注3（是否参加贴息）", width = 15)
	private String bz3;
	private String khqm;
	private String khjlqm;
	private String pfrqm;
	private Date sxbscsj;
}
