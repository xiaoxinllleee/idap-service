package org.cmms.modules.yxdygl.pqqxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxglTree;

/**
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date:   2021-11-17
 * @Version: V1.0
 */
public interface YxdyglPqqxglMapper extends BaseMapper<YxdyglPqqxgl> {

    int getcount();

    List<YxdyglPqqxglTree> getByPage(@Param("startPage")long startPage,
                                     @Param("endPage")long endPage,
    @Param("dao") YxdyglPqqxgl yxdyglPqqxgl);

    List<String> getMenuIdsByZkhjl();


    List<String> getMenuIdsByKhjlgh(@Param("yggh") String yggh);
}
