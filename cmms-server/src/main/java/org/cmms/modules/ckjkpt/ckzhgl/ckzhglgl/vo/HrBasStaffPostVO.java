package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@TableName("view_hr_bas_staff_post")
public class HrBasStaffPostVO {

    //ID
    @ApiModelProperty(value = "ID")
    private String id;
    //组织标识
    @ApiModelProperty(value = "组织标识")
    private String zzbz;
    //机构代码
    @ApiModelProperty(value = "机构代码")
    private String jgdm;
    //组织名称
    @ApiModelProperty(value = "组织名称")
    private String zzmc;
    //员工工号
    @ApiModelProperty(value = "员工工号")
    private String yggh;
    //员工姓名
    @ApiModelProperty(value = "员工姓名")
    private String ygxm;
    //岗位标识
    @ApiModelProperty(value = "岗位标识")
    private String gwbz;
    //岗位名称
    @ApiModelProperty(value = "岗位名称")
    private String gwmc;
    //客户经理标识
    @ApiModelProperty(value = "客户经理标识")
    private String khjlbz;
    //柜员号
    @ApiModelProperty(value = "柜员号")
    private String gyh;
    //入岗类型
    @ApiModelProperty(value = "入岗类型(1 正式 2 代班 3 实习 4 虚拟 5 离岗)")
    private String rglx;
    //入岗日期
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入岗日期")
    private Date rgrq;
    //离岗日期
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "离岗日期")
    private Date lgrq;
    //是否参与考核
    @ApiModelProperty(value = "是否参与考核(1 是 2 否)")
    private Integer sfcykh;
    //是否临退休
    @ApiModelProperty(value = "是否临退休(1 是 2 否)")
    private Integer sfltx;
    //备注
    @ApiModelProperty(value = "备注")
    private String bz;
    //删除标识
    @ApiModelProperty(value = "删除标识(0 否 1 是)")
    private Integer scbz;
    //删除时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "删除时间")
    private Date scsj;
    //删除操作员
    @ApiModelProperty(value = "删除操作员")
    private String scczy;
}
