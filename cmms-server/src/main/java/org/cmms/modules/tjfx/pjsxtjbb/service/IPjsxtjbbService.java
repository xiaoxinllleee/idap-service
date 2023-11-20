package org.cmms.modules.tjfx.pjsxtjbb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cmms.modules.tjfx.pjsxtjbb.entity.Pjsxtjbb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;

import java.util.List;

/**
 * @Description: 评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
public interface IPjsxtjbbService extends IService<Pjsxtjbb> {
    void init(String tjyf);

    List<NhPjsxxxMx> pjsxtjbbExl(String sswg,String sjrq);
}
