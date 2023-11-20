package org.cmms.modules.tjfx.tpcfsctj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.tpcfsctj.entity.Tpcfsctj;

/**
 * @Description: 图片重复上传统计
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
public interface ITpcfsctjService extends IService<Tpcfsctj> {
    void init();
}
