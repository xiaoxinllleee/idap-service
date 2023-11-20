package org.cmms.modules.khdj.khdjpdgzsz.vo;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.List;

/**
 * @Description: 客户等级规则设置
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
public class KhdjpdGzszPage {

    /**主键id*/
    private String id;
    /**规则编号*/
    @Excel(name = "规则维度", width = 15)
    private String gzwd;
    /**规则编号*/
    @Excel(name = "规则编号", width = 15)
    private String gzbh;
    /**规则名称*/
    @Excel(name = "规则名称", width = 15)
    private String gzmc;
    /**规则概述*/
    @Excel(name = "规则概述", width = 15)
    private String gzgs;
    /**客户等级编号*/
    @Excel(name = "客户等级", width = 15, dicCode="djbh", dictTable="KHDJ_KHDJSZ", dicText="djmc")
    @Dict(dicCode="djbh", dictTable="KHDJ_KHDJSZ", dicText="djmc")
    private String khdjbh;

    @ExcelCollection(name="客户明细")
    private List<KhdjpdGzszGzxx> Gzap_jhxf_khjl_gzxx_realList;

}
