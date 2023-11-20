package org.cmms.modules.khdj.khdjpdgzsz.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_KHDJPDGZSZ_GZXX")
public class KhdjpdGzszGzxx implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键id*/
    @Excel(name = "ID", width = 15)
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**客户类型(01.个人/02.企业)*/
    @Excel(name = "客户类型", width = 15, dicCode = "khdjpd_khlx")
    @Dict(dicCode = "khdjpd_khlx")
    private String khlx;
    /**父级ID*/
    @Excel(name = "父级ID", width = 15)
    private String key;
    /**规则ID*/
    @Excel(name = "规则ID", width = 15)
    private String gzid;
    /**规则编号*/
    @Excel(name = "规则编号", width = 15, dicCode = "sjxid", dictTable = "KHDJ_DJPDSJX", dicText = "sjxmc")
    @Dict(dicCode = "sjxid", dictTable = "KHDJ_DJPDSJX", dicText = "sjxmc")
    private String gzbh;
    /**规则符号*/
    @Excel(name = "规则符号", width = 15, dicCode = "ysfh")
    @Dict(dicCode = "ysfh")
    private String gzfh;
    /**规则值*/
    @Excel(name = "规则值", width = 15)
    private String gzz;
}
