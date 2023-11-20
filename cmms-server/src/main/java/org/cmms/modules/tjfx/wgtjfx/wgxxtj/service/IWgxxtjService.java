package org.cmms.modules.tjfx.wgtjfx.wgxxtj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.Wgxxtj;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxxtjVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.ZfcjxxVo;

import java.util.List;

/**
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
public interface IWgxxtjService extends IService<Wgxxtj> {
    Wgxxtj getWgxxByWgbh(String wgbh);

    Wgxxtj getWgxxByWgbhTy(List<String> wgbhList);

    IPage<WgxxtjVo> jbxxCs(Page page, String cs,List<String> wgbhList);

    IPage<WgxxtjVo> wbsbk(Page page,List<String> wgbhList);
    List<WgxxtjVo> wbsbk(List<String> wgbhList);

    IPage<WgxxtjVo> tpjjch(Page page,List<String> wgbhList);

    IPage<WgxxtjVo> getSarsByWgbh(Page page,List<String> wgbhList);

    List<ZfcjxxVo> getPyxxPh(List<String> wgbhList,String sxtlx);
}
