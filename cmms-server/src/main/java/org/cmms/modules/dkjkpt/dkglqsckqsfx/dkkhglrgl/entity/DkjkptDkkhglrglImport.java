package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity;

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

import java.util.Date;

/**
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@Data
@TableName("Dkjkpt_dkkhglrgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dkjkpt_dkkhglrgl对象", description="贷款客户关联人管理")

public class DkjkptDkkhglrglImport {
    
	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
	private String jkrzjhm;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
	private String jkrmc;

	/**关联人证件号码*/
	@Excel(name = "关联人证件号码", width = 15)
	private String glrzjhm;

	/**关联人证件类型（1：身份证 0：其他）*/
	@Excel(name = "关联人证件类型", width = 15)
	@Dict(dicCode = "dkjkpt_zjlx")
	private Integer glrzjlx;

	/**关联人姓名*/
	@Excel(name = "关联人姓名", width = 15)
	private String glrxm;
	/**关联关系（1：父母 2：配偶 3：兄弟姐妹 4：子女）*/
	@Excel(name = "关联关系", width = 15)
	@Dict(dicCode = "dkjkpt_glgx")
	private Integer glgx;
	/**关联人工作单位*/
	@Excel(name = "关联人工作单位", width = 15)
	private String glrgzdw;
	/**关联人联系电话*/
	@Excel(name = "关联人联系电话", width = 15)
	private String glrlxdh;

}
