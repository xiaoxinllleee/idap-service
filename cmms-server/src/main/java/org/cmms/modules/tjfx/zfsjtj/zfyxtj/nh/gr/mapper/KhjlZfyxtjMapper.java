package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.entity.KhjlZfyxtj;

/**
 * @Description: 客户经理走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
public interface KhjlZfyxtjMapper extends BaseMapper<KhjlZfyxtj> {
    void init(String tjrq);
    void batchInit(String beginDate, String endDate);
    void syxyInit(String zdrkrq);
}
