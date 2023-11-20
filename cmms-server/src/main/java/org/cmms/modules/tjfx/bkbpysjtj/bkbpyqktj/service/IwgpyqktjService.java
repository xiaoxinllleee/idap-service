package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.entity.Wgpyqktj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 评议情况统计(网格)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
public interface IwgpyqktjService extends IService<Wgpyqktj> {
    void initPyxx(String tjyf);

}
