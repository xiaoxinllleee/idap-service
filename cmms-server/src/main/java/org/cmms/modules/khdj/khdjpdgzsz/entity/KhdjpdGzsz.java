package org.cmms.modules.khdj.khdjpdgzsz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户等级规则设置
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_KHDJPDGZSZ")
public class KhdjpdGzsz implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键id*/
    @TableId(type = IdType.ASSIGN_ID)
    @Excel(name = "ID", width = 15)
    private String id;
    /**规则维度*/
    @Excel(name = "规则维度", width = 15, dicCode = "rqwd")
    @Dict(dicCode = "rqwd")
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
}
