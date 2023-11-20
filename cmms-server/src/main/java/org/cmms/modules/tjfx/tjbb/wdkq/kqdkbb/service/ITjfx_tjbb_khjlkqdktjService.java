package org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.service;

import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.entity.Tjfx_tjbb_khjlkqdktj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
public interface ITjfx_tjbb_khjlkqdktjService extends IService<Tjfx_tjbb_khjlkqdktj> {
    public void extract(String tjyf);

}
