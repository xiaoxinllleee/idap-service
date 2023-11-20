package org.cmms.modules.report.zbgl.zbjg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.report.zbgl.zbjg.entity.Zbjg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 指标结果
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@DS("ads")
public interface IZbjgService extends IService<Zbjg> {

    Double dynamicSql(@Param("sqlStr") String sql);
    long dynamicDwSql(@Param("sqlStr") String sql);
    void dynamicCallSql(@Param("sqlStr") String sql);
    String executePcSql(List<Zbsjxgl> sqlList, Date sjrq);
    String executeDwzbSql(List<Zbsjxgl> sqlList, Date sjrq,String sfdsjpt);
}
