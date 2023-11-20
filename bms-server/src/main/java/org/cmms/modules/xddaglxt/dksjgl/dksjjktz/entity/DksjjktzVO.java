package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_BNDKSJJKTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_BNDKSJJKTZ对象", description="贷款数据监控台账")
public class DksjjktzVO {

    /**贷款账号*/
    @Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
    private String dkzh;
    /**不良形成原因*/
    @Excel(name = "不良形成原因", width = 15)
    @ApiModelProperty(value = "不良形成原因")
    @Dict(dicCode = "blxcyy")
    private String blxcyy;
    /**责任界定*/
    @Excel(name = "责任界定", width = 15)
    @ApiModelProperty(value = "责任界定")
    @Dict(dicCode = "zrjd")
    private String zrjd;
    /**清收处置措施*/
    @Excel(name = "清收处置措施", width = 15)
    @ApiModelProperty(value = "清收处置措施")
    @Dict(dicCode = "qsczcs")
    private String qsczcs;
    /**清收处置时限*/
    @Excel(name = "清收处置时限", width = 15)
    @ApiModelProperty(value = "清收处置时限")
    private String qsczsx;
    /**主要责任人*/
    @Excel(name = "主要责任人", width = 15)
    @ApiModelProperty(value = "主要责任人")
    private String zyzrr;
    /**次要责任人*/
    @Excel(name = "次要责任人", width = 15)
    @ApiModelProperty(value = "次要责任人")
    private String cyzrr;
    /**清收责任人*/
    @Excel(name = "清收责任人", width = 15)
    @ApiModelProperty(value = "清收责任人")
    private String qszrr;

}
