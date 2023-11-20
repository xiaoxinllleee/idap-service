package org.cmms.modules.ywgl.ywl.ywltqytj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.ywl.ywltqytj.entity.Ywltqytj;

import java.util.Date;

/**
 * @Description: 业务量提取与统计
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYwltqytjService extends IService<Ywltqytj> {
    void pYwltqytj(@Param("tjyf") String tjyf, @Param("zzbz") String zzbz);

    String getMaxFpid();

    String getAssessParamValue(@Param("paramcode") String paramcode);

    String conversionConfigInfo(@Param("beginOfMonth") Date beginOfMonth);
}
