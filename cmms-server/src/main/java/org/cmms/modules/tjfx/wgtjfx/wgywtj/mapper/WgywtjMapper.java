package org.cmms.modules.tjfx.wgtjfx.wgywtj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.*;

/**
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
public interface WgywtjMapper extends BaseMapper<Wgywtj> {
    Wgywtj getWgywxxByWgbh(String wgbh);
    Wgywtj getWgywxxByWgbhTy(@Param("wgbhList")List<String> wgbhList);
    int getFxezf(@Param("wgbhList")List<String> wgbhList);
    DzyhByjzVo getDzyhByjz(@Param("wgbhList") List<String> wgbhList);
    PyxxVo getPyxx(@Param("wgbhList")List<String> wgbhList);
    Integer getZnzdHs(@Param("tableName")String tableName,@Param("wgbhList")List<String> wgbhList);
    String getNnzdTableName();
    IPage<ZnzdVo> getZnzdInfoByWgbh(Page page,String tableName, List<String> wgbhList);
    List<WgxxVo> getWgxxToNum(@Param("wgbhList")List<String> wgbhList);
    Integer getCjdxrsToWgxx(@Param("wgbhList")List<String> wgbhList);
}
