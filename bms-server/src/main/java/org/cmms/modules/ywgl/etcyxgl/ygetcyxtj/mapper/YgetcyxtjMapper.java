package org.cmms.modules.ywgl.etcyxgl.ygetcyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.ygetcyxtj.entity.Ygetcyxtj;

/**
 * @Description: 员工etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface YgetcyxtjMapper extends BaseMapper<Ygetcyxtj> {
    void pYgetcyxtj(@Param("tjyf")String tjyf);
}
