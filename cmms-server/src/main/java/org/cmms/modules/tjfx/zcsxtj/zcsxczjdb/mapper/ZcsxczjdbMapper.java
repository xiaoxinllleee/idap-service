package org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.TjfxZcsxjdbXzdk;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.Zcsxczjdb;

/**
 * @Description: 整村授信村组进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface ZcsxczjdbMapper extends BaseMapper<Zcsxczjdb> {
    @Select("select max(tjrq) from tjfx_zcsxjdb_zu")
    String maxDate();

    IPage<TjfxHnkd> getHnkdDrxxPage(Page page, String wgbh,String lx,String type,String sszh,String sjrq,String nf,String pc);
    List<TjfxHnkd> getHnkdDrxxList(String wgbh,String lx,String type,String sszh,String sjrq,String nf,String pc);


    IPage<TjfxZcsxjdbXzdk> getHnkdQyxxPage(Page page, String wgbh, String sjrq,String type,String sszh,String nf,String pc);
    List<TjfxZcsxjdbXzdk> getHnkdQyxxList(String wgbh,String sjrq,String type,String sszh,String nf,String pc);

    IPage<TjfxZcsxjdbXzdk> getDkhtxxPage(Page page, String wgbh,String sjrq,String otherlx,String type,String sszh,String nf,String pc);
    List<TjfxZcsxjdbXzdk> getDkhtxxList(String wgbh,String sjrq,String otherlx,String type,String sszh,String nf,String pc);

    IPage<TjfxZcsxjdbXzdk> getYXPage(Page page, String wgbh,String sjrq,String otherlx,String type,String sszh,String nf,String pc);
    List<TjfxZcsxjdbXzdk> getYxList(String wgbh,String sjrq,String otherlx,String type,String sszh,String nf,String pc);
}
