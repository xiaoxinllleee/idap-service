package org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.entity.Zhetcyxtj;

/**
 * @Description: 支行etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface ZhetcyxtjMapper extends BaseMapper<Zhetcyxtj> {
    void pZhetcyxtj(@Param("tjyf")String tjyf);
}
