package org.cmms.modules.yxdygl.yxdyglmain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;

/**
 * @Description: 营销单元菜单
 * @Author: jeecg-boot
 * @Date:   2021-11-16
 * @Version: V1.0
 */
public interface YxdyglMenuMapper extends BaseMapper<YxdyglMenu> {

    List<YxdyglMenu> getKhjlList(@Param("khjl")String khjl);
}
