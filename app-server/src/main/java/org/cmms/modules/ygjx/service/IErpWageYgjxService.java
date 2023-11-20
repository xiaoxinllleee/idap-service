package org.cmms.modules.ygjx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.gr.grjxsj.entity.ErpWageYgjbgzglYx;
import org.cmms.modules.ygjx.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 员工绩效合计
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
public interface IErpWageYgjxService extends IService<ErpWageYgjx> {
    //获取人均  写这里是因为获取人均的实体类在bms
    public String getRj();

    public JgDataVO jgIndex(String zzbz);

    public JgDataVO getZhrjZjj(String zzbz,String gzrq);
    //获取机构张家界最大工资日期
    public String getMaxJgDay();
    public List<ErpWageYgjx> getGzPmByZzbz(String zzbz);
    public List<ErpWageYgjx> getGzPmByZzbzJH(String zzbz);

    public Integer getCkByJgdm(String jgdm,String yyMM);
    public Integer getHqCkByJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public Integer getDkByJgdm(String jgdm,String yyMM);
    public Map<String,BigDecimal> getDkKHByJgdm(String jgdm, String yyMM);
    public Integer getBnblByJgdm(String jgdm,String yyMM);

    public ErpWageYgjbgzglYx getImpWage(String date, String yggh);

    public void updateYfgz(String yggh);

    public IPage<ImpWageVO> getImpWageList(Page page,String yggh);

    public String getMaxDate(String yggh);

    public String getZjjDate();
    //贷款主表最大日期-1
    public String getZjjDkDate();

    //修改后的查询 张家界
    public Integer getZjjDK(String tableName,String jgdm);
    public Integer getZjjCK(String tableName ,String jgdm);
    public Integer getZjjDxye(String tableName ,String jgdm );
    public Integer getZjjZczl(String tableName );//不良贷款余额
    public Integer getZjjZczlDkye(String tableName);//贷款余额
    public IPage<PrTbYgjbgzmxb> getJbgzHzDate(Page page, String nf, String zzbz, String tjyf);//岗位行长全年数据
    public IPage<PrTbYgjbgzmxb> getJbgzDate(Page page, String nf, String yggh, String tjyf);//岗位、津补贴、补扣全年数据
    public IPage<ErpBfgz> getBfgzDate(Page page, String nf, String yggh, String tjyf);//补发工资全年数据
    public IPage<ErpBkgz> getBkgzDate(Page page, String nf, String yggh, String tjyf);//补扣工资全年数据


    //修改后的查询
    public Integer getTjbsCK(String jgdm,String yyyyMMdd);
    public Integer getTjbsDK(String jgdm,String yyyyMMdd);

    //低息存款
    public Integer getTjbsDxck(String jgdm,String yyyyMMdd);

    public String getMaxJbgz(String yggh);

    //获取一个月时间最大的基本工资
    public String getMaxJbgzAndDate(String yggh,String yc,String ym);

    //获取江华的工资
    public BigDecimal getJhJxgz(String yggh,String date);

    public List<String> zbids(String yggh,String date);
    public Map<String,BigDecimal> ckrpAndCkje(String table,String acctNo);
}
