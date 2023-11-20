package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.entity.KhjlZfyxtj;

/**
 * @Description: 客户经理走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
public interface IKhjlZfyxtjService extends IService<KhjlZfyxtj> {
    void init(String tjrq);
    void batchInit(String beginDate, String endDate);
    void syxyInit(String zdrkrq);
}
