package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.fxd.entity.KhglIndexVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhHmdDataVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
public interface NhxqMapper extends BaseMapper<Nhxq> {
    public void init();
    //除了本人外的家庭关系情况
    List<Nhxq> getHByZjhm(@Param("zjhm") String zjhm);
    List<Nhxq> getByHnkd(@Param("khxxglHnkd") KhxxglHnkd khxxglHnkd,@Param("workNo")String workNo);
    int syncYesNhxx();
    KhglIndexVO getFxdIndex();
    KhglIndexVO getFxdIndex2(@Param("wgbh") String wgbh,@Param("yggh") String yggh);

    List<Nhxq> selectUser(@Param("minlng")double minlng,@Param("maxlng") double maxlng
            ,@Param("minlat") double minlat,@Param("maxlat") double maxlat);

    public void removeHhbmById(String id);

    public void khzyok(@Param("nhxq")Nhxq nhxq,@Param("sszh") String sszh, @Param("khjl") String khjl);
    public void khzyokList(@Param("list")List<String> list, @Param("khjl") String khjl);
    public void khzyoid(@Param("id")String id, @Param("khjl")String khjl);

    List<NhHmdDataVo> getHmdData();

    void drhnkdbmd();
    void drhnkdbmdsszh(String sszh);

    List<Nhxq> sj10000();

    void update10000(@Param("dao") Nhxq nhxq);
}
