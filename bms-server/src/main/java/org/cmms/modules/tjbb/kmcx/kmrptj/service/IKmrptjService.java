package org.cmms.modules.tjbb.kmcx.kmrptj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.kmcx.kmrptj.entity.Kmrptj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 科目日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IKmrptjService extends IService<Kmrptj> {
    void pKmrptj(String tjyf);
}
