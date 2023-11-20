package org.cmms.modules.tjfx.wgtjfx.wgywtj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.DzyhByjzVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.PyxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.WgxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;

import java.util.List;

/**
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
public interface IWgywtjService extends IService<Wgywtj> {
    Wgywtj getWgywxxByWgbh(String wgbh);
    Wgywtj getWgywxxByWgbhTy(List<String> wgbhList);
    int getFxezf(List<String> wgbhList);
    DzyhByjzVo getDzyhByjz(List<String> wgbhList);
    PyxxVo getPyxx(List<String> wgbhList);
    Integer getZnzdHs(String tableName,List<String> wgbhList);
    List<WgxxVo> getWgxxToNum(List<String> wgbhList);
    Integer getCjdxrsToWgxx(List<String> wgbhList);
}
