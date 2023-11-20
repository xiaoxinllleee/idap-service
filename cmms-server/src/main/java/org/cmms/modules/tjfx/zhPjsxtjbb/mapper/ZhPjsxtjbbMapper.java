package org.cmms.modules.tjfx.zhPjsxtjbb.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.Vo.ZhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.entity.ZhPjsxtjbb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
public interface ZhPjsxtjbbMapper extends BaseMapper<ZhPjsxtjbb> {
    List<ZhPjsxxxMx> zhPjsxtjbbMx(@Param("sszh")String sszh,@Param("sjrq")String sjrq);
}
