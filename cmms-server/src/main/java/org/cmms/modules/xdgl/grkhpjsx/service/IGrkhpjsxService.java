package org.cmms.modules.xdgl.grkhpjsx.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grkhpjsx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 个人客户评级授信
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
public interface IGrkhpjsxService extends IService<Grkhpjsx> {
    public String querySplcProcessId(String key);

    public void updateGrpjsxxx(String spid,String zzpddj, BigDecimal zzsxed, String yj, String hhbm);
    public void updateGrpjsxxxZjhm(String spid,String zzpddj, BigDecimal zzsxed, String yj, String zjhm);
    public void updateGrpjsxxxZjhm2(String zzpddj, BigDecimal zzsxed, String yj, String zjhm,int status,String cpzl,BigDecimal cpzlll);
    public void updateGrpjsxxxZjhmAndStatus( String zjhm,int status);

    public void updateGrpjsxed(String pddj,BigDecimal zzsxed, String yj, String hhbm);

    public void updateGrpjsxjled(String pddj,BigDecimal zzsxed, String yj, String spid);

    public List<Grkhpjsx> getByListZjhm(List<String> zjhms);

    public List<Grkhpjsx> jointbDebtor(String hhbm);

    public String getfxjlBySskhjl(String sskhjl);

}
