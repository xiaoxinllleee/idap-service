package org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.entity.Qhetcyxtj;

/**
 * @Description: 全行etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface QhetcyxtjMapper extends BaseMapper<Qhetcyxtj> {
    void pQhetcyxtj(@Param("tjyf")String tjyf);
}
