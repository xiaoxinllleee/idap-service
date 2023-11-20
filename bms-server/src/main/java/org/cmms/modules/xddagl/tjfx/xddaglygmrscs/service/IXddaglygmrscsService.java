package org.cmms.modules.xddagl.tjfx.xddaglygmrscs.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.tjfx.xddaglygmrscs.entity.Xddaglygmrscs;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@DS("eweb")
public interface IXddaglygmrscsService extends IService<Xddaglygmrscs> {
    void pYgmrscs(String tjrqBegin, String tjrqEnd, String username);
}
