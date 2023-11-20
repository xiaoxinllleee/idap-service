package org.cmms.modules.yxdygl.yxdyglmain.service;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.AntSelectOptions;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;

import java.util.List;

/**
 * @Description: 营销单元管理
 * @Author: jeecg-boot
 * @Date:   2021-11-15
 * @Version: V1.0
 */
public interface IYxdyglMainService extends IService<YxdyglMain> {
    List<YxdyglMain> listTree(String khjl, String previousLevel, String pId, String queryOrganize);
    List<YxdyglMain> listTreeByKhjl(String khjl, String pId);
    List<YxdyglMain> listTreeAll(String pId);
    List<YxdyglMain> getWgxxTreeDateBySskhjl(String sskhjl, int maxLevel, String wgxz, String wgxzType, String disableSelect,String minLevel,String zzbz);
    List<YxdyglMain> getWgxxTreeDateAll(int maxLevel, String wgxz, String wgxzType, String disableSelect);

    //根据wgbh找到镇一级的网格编号
    String getZhenByNoRoot(String wgbh,String level);
    List<YxdyglMain> getWgbhParentWgbh(String wgbh);

    List<YxdyglMain> getByQydm(String qydm);
    List<YxdyglMain> getByQydm(String qydm,YxdyglMain yxdyglMain);

    List<YxdyglMain> getWgxxTreeDate(Integer maxLevel,String disableSelect,String minLevel);

}
