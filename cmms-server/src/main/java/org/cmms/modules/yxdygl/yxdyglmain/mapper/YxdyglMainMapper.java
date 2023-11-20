package org.cmms.modules.yxdygl.yxdyglmain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.yxdyglmain.entity.AppYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 营销单元管理
 * @Author: jeecg-boot
 * @Date:   2021-11-15
 * @Version: V1.0
 */
@Component
public interface YxdyglMainMapper extends BaseMapper<YxdyglMain> {
    List<YxdyglMain> getPreviousLevel(String previousLevel, String pId, String queryOrganize);

    List<YxdyglMain> getWgxxTreeDateBySskhjl(String sskhjl, String wgxz, String wgxzType,String zzbz);

    List<YxdyglMain> getWgxxTreeDateAll(String wgxz, String wgxzType);



    IPage<AppYxdyglMain> getAppList(Page page, @Param("dao") AppYxdyglMain appYxdyglMain, @Param("workNo") String workNo, String queryOrganize);

    List<YxdyglMain> getKhjlList(String khjl);

    List<YxdyglMain> getAllList(String pId);

    List<YxdyglMain> getWgbhParentWgbh(String wgbh);

    List<YxdyglMain> getByQydm(String qydm);

    List<YxdyglMain> getByQydmAndDao(@Param("qydm") String qydm,@Param("dao") YxdyglMain yxdyglMain);

    List<YxdyglMain> getAll();
}
