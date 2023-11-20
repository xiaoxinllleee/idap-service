package org.cmms.modules.tjbb.kmcx.kmrptj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.kmcx.kmrptj.entity.Kmrptj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 科目日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
public interface KmrptjMapper extends BaseMapper<Kmrptj> {
    void pKmrptj(@Param("tjyf") String tjyf);
}
