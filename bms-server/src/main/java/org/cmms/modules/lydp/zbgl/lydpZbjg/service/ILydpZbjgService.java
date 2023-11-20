package org.cmms.modules.lydp.zbgl.lydpZbjg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.lydp.zbgl.lydpZbjg.entity.LydpZbjg;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @Description: 浏阳大屏指标结果表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@DS("ads")
public interface ILydpZbjgService extends IService<LydpZbjg> {
    Double dynamicSql(@Param("sqlStr") String sql);
    long dynamicDwSql(@Param("sqlStr") String sql);
    void dynamicCallSql(@Param("sqlStr") String sql);

    String executePcSql(List<LydpZbsjx> sqlList, Date sjrq);
    String executeDwzbSql(List<LydpZbsjx> sqlList, Date sjrq,String sfdsjpt);


}
