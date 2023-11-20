package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * @author 龚辉
 * @date 2023/7/6 10:42 周四
 */
@Data
@TableName("KHXXGL_KHXQ_XXNYZT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_XXNYZT", description="新型农业主体")
public class XxnyztCzpdVo {
    /**健康状态情况(1.较差；2.一般；3.良好)*/
    private String jkztqk;
    /**房屋价值情况*/
    private String fwjzqk;
    /**家庭纯收入情况*/
    private String jtcsrqk;
    /**信誉状况(1.较差；2.一般；3.良好)*/
    private String xyzk;
    /**家庭负债情况(1.无负债；2.少量负债；3.较高负债)*/
    private String jtfzqk;
    /**不予授信情形*/
    private String bysxqx;
    /**评议得分*/
    private String pydf;
    /**诚信度(1.很好；2.较好；3.一般；4.差)*/
    private String cxd;

    /** 银行负债 */
    private BigDecimal yhfz;
}
