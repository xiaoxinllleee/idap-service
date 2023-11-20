package org.cmms.modules.tjfx.wgtjfx.wgywtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.DzyhByjzVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.PyxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.ZnzdVo;

import java.util.List;

/**
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@DS("eweb")
public interface IWgywtjZnzdService extends IService<Wgywtj> {
    Integer getZnzdHs(String tableName,List<String> wgbhList);
    String getNnzdTableName();
    IPage<ZnzdVo> getZnzdInfoByWgbh(Page page,String tableName, List<String> wgbhList);
}
