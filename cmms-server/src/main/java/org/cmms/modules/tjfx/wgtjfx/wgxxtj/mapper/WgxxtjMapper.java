package org.cmms.modules.tjfx.wgtjfx.wgxxtj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.Wgxxtj;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxxtjVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.ZfcjxxVo;

/**
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
public interface WgxxtjMapper extends BaseMapper<Wgxxtj> {
    Wgxxtj getWgxxByWgbh(String wgbh);

    Wgxxtj getWgxxByWgbhTy(List<String> wgbhList);

    IPage<WgxxtjVo> jbxxCs(Page page,@Param("cs") String cs,@Param("wgbhList")List<String> wgbhList);

    IPage<WgxxtjVo> wbsbk(Page page,@Param("wgbhList")List<String> wgbhList);

    List<WgxxtjVo> wbsbk(@Param("wgbhList")List<String> wgbhList);

    IPage<WgxxtjVo> tpjjch(Page page,@Param("wgbhList")List<String> wgbhList);

    IPage<WgxxtjVo> getSarsByWgbh(Page page,List<String> wgbhList);

    List<ZfcjxxVo> getPyxxPh(@Param("wgbhList")List<String> wgbhList,@Param("sxtlx")String sxtlx);
}
