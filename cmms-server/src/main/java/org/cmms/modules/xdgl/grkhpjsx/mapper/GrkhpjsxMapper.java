package org.cmms.modules.xdgl.grkhpjsx.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grkhpjsx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人客户评级授信
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */

@Component
public interface GrkhpjsxMapper extends BaseMapper<Grkhpjsx> {
    public String querySplcProcessId(@Param("key") String key);

    public void updateGrpjsxxx(@Param("spid") String spid,@Param("zzpddj") String zzpddj, @Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("hhbm") String hhbm);

    public void updateGrpjsxed(@Param("pddj") String pddj,@Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("hhbm") String hhbm);

    public void updateGrpjsxjled(@Param("pddj") String pddj, @Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("spid") String spid);

    public List<Grkhpjsx> getByListZjhm(@Param("zjhmList")List<String> zjhmList);

    public List<Grkhpjsx> jointbDebtor(@Param("hhbm")String hhbm);

    public void updateGrpjsxxxByZjhm(@Param("spid") String spid,@Param("zzpddj") String zzpddj,
                                     @Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj,
                                     @Param("zjhm") String zjhm);
    public void updateGrpjsxxxByZjhm2(@Param("zzpddj") String zzpddj, @Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("zjhm") String zjhm
            , @Param("status") int status, @Param("cpzl")String cpzl,@Param("cpzlll")BigDecimal cpzlll);

    void updateGrpjsxxxZjhmAndStatus(@Param("zjhm") String zjhm
            , @Param("status") int status);

}
