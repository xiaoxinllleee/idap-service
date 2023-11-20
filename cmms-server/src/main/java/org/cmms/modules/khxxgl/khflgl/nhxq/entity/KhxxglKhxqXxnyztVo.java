package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2022-12-06
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_XXNYZT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_XXNYZT", description="新型农业主体")
public class KhxxglKhxqXxnyztVo {

	/**网格编号*/
	@Excel(name = "网格编号", width = 15, dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
	private String wgbh;
	/**主体分类*/
	@Excel(name = "主体分类", width = 15,dicCode = "xxnyzt-ztfl")
	private String ztfl;
	/**新型主体名称*/
	@Excel(name = "新型主体名称", width = 15)
	private String xxztmc;
	/**新型主体证件号码*/
	@Excel(name = "新型主体证件号码", width = 15)
	private String xxztzjhm;
	/**经营者*/
	@Excel(name = "经营者", width = 15)
	private String jyz;
	/**经营者证件号码*/
	@Excel(name = "经营者证件号码", width = 15)
	private String jyzzjhm;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
	private Integer ysxed;
	/**是否已授信*/
	@Excel(name = "是否存量客户", width = 15,dicCode = "sfbz")
	private String sfysx;
	/**评定分类*/
	@Excel(name = "村组评定", width = 15,dicCode = "xxnyzt-cxpj")
	private String pdfl;
	/**是否有信贷需求*/
	@Excel(name = "是否有信贷需求", width = 15,dicCode = "sfbz")
	private String sfyxdxq;
	/**陪访人*/
	@Excel(name = "评议员", width = 15)
	private String pfr;
	/**是否正常经营*/
	@Excel(name = "是否正常经营", width = 15,dicCode = "sfbz")
	private String sfzcjy;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
	private String zcdz;
	/**管户责任人*/
	@Excel(name = "管户人", width = 15,dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "管户人")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String ghzrr;
	/**经营项目*/
	@Excel(name = "经营项目", width = 15)
	@ApiModelProperty(value = "经营项目")
	private String jyxm;
	/**经营规模*/
	@Excel(name = "经营规模", width = 15)
	@ApiModelProperty(value = "经营规模")
	private String jygm;
	/**年产值*/
	@Excel(name = "年产值", width = 15)
	@ApiModelProperty(value = "年产值")
	private Integer nsr;
	/**系统测算授信额度 */
	@Excel(name = "系统测算授信额度", width = 15)
	@ApiModelProperty(value = "系统测算授信额度")
	private java.math.BigDecimal xtcssxed;
	/**白名单授信额度 */
	@Excel(name = "白名单授信额度", width = 15)
	@ApiModelProperty(value = "白名单授信额度")
	private java.math.BigDecimal bmdsxed;
	/**有效签约对象 */
	@Excel(name = "有效签约对象", width = 15)
	@ApiModelProperty(value = "有效签约对象")
	private String yxqydx;
	/**有效签约对象证件号码 */
	@Excel(name = "有效签约对象证件号码", width = 15)
	@ApiModelProperty(value = "有效签约对象证件号码")
	private String yxqydxZjhm;
	/**有效签约额度 */
	@Excel(name = "有效签约额度", width = 15)
	@ApiModelProperty(value = "有效签约额度")
	private java.math.BigDecimal yxqyed;
	/**贷款余额 */
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**当年新签约额度 */
	@Excel(name = "当年新签约额度", width = 15)
	@ApiModelProperty(value = "当年新签约额度")
	private java.math.BigDecimal dnxqyed;
	/**当年贷款余额净增 */
	@Excel(name = "当年贷款余额净增", width = 15)
	@ApiModelProperty(value = "当年贷款余额净增")
	private java.math.BigDecimal dndkyejz;
}
