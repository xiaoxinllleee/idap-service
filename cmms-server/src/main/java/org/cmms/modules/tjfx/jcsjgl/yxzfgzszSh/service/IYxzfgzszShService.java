package org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.entity.YxzfgzszSh;

import java.util.List;

/**
 * @Description: 有效走访规则设置_商户
 * @Author: jeecg-boot
 * @Date:   2022-05-11
 * @Version: V1.0
 */
public interface IYxzfgzszShService extends IService<YxzfgzszSh> {
    List<Yxzfmx> getZfzbxxByShid(String shid,String tjrq);
}
