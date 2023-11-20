package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.fxd.entity.KhglIndexVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhHmdDataVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
public interface INhxqService extends IService<Nhxq> {
    public void init();
    List<Nhxq> getHByZjhm(String zjhm);

    List<Nhxq> getByHnkd(KhxxglHnkd khxxglHnkd);

    Nhxq getByZjhm(String zjhm);
    //boolean nhIsQxfkData(String id);
    int syncYesNhxx();

    KhglIndexVO getFxdIndex();
    KhglIndexVO getFxdIndex(String wgbh,String yggh);

    List<Nhxq> selectUser(double minlng, double maxlng, double minlat, double maxlat);

    void removeHhbmById(String id);

    void khzyok(Nhxq nhxq,String sszh,String khjl);
    void khzyokList(List<String> list,String khjl);
    void khzyoid(String id,String khjl);

    List<NhHmdDataVo> getHmdData();

    void drhnkdbmd();
    void drhnkdbmdsszh(String sszh);

    List<Nhxq> sj10000();
    void update10000(Nhxq nhxq);
}
