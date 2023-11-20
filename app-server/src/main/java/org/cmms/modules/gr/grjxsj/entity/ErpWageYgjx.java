package org.cmms.modules.gr.grjxsj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("erp_wage_ygjx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ErpWageYgjx {

    @Id
    private String zzbz;            //组织标识
    @Id
    private Date gzrq;            //工资日期
    private BigDecimal ckgz = BigDecimal.ZERO;        //存款工资
    private BigDecimal dkgz = BigDecimal.ZERO;        //贷款工资
    private BigDecimal dzyhgz = BigDecimal.ZERO;        //电子银行工资
    private BigDecimal ywlgz = BigDecimal.ZERO;        //业务量工资
    private BigDecimal gljxgz = BigDecimal.ZERO;        //管理绩效工资
    private BigDecimal dqbcgz = BigDecimal.ZERO;        //地区补差工资
    private BigDecimal gzhj = BigDecimal.ZERO;        //工资合计
    @Id
    private Integer gwbz;                //岗位标志
    @Id
    private String yggh;            //员工工号
    private Integer zhgzpm;                //支行工资排名
    private Integer qhgzpm;                //全行工资排名
    @Id
    private String khwd;            //考核维度
    private Integer lrbz;                //录入标志
    private String lrr;                //录入人
    private Date lrsj;            //录入时间
    private BigDecimal qt = BigDecimal.ZERO;            //其他
    private BigDecimal jymbgz = BigDecimal.ZERO;        //经营目标工资
    private BigDecimal yqdfgz = BigDecimal.ZERO;        //延期兑付工资
    private BigDecimal JBGZ = BigDecimal.ZERO;//基本工资
    private BigDecimal bwgz = BigDecimal.ZERO;//表外工资
    private BigDecimal zxkhgz = BigDecimal.ZERO;//专项考核工资
    private BigDecimal nzkhgz = BigDecimal.ZERO;//年终考核工资

}
