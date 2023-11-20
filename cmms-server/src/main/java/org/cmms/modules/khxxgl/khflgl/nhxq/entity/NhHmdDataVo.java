package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author 龚辉
 * @date 2023/8/22 10:31 周二
 */
@Data
public class NhHmdDataVo {

    /**户号编码 */
    @Excel(name = "户号编码", width = 15)
    private String hhbm;

    /**客户名称 */
    @Excel(name = "客户名称", width = 15)
    private String khmc;

    /**证件号码 */
    @Excel(name = "证件号码", width = 15)
    private String zjhm;

    /**系统评定说明 */
    @Excel(name = "系统评定说明", width = 15)
    private String xtpdsm;

    /**表内不良余额 */
    @Excel(name = "表内不良余额", width = 15)
    private java.math.BigDecimal bldkye;

    /**表外不良余额 */
    @Excel(name = "表外不良余额", width = 15)
    private java.math.BigDecimal bwbldkye;
}
