package org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.entity.Yxzfgzsz;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;

import java.util.List;
import java.util.Map;

/**
 * @Description: 农户有效走访规则设置
 * @Author: jeecg-boot
 * @Date:   2022-01-17
 * @Version: V1.0
 */
public interface IYxzfgzszService extends IService<Yxzfgzsz> {
    List<Yxzfmx> getZfzbxxByHhbm(String hhbm,String tjrq);
}
