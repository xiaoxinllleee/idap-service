package org.cmms.modules.tjfx.pjsxtjbb.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxtjbb.entity.Pjsxtjbb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;

import java.util.List;

/**
 * @Description: 评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
public interface PjsxtjbbMapper extends BaseMapper<Pjsxtjbb> {
    void init(@Param("tjyf") String tjyf);

    List<NhPjsxxxMx> pjsxtjbbMx(@Param("sswg")String sswg,@Param("sjrq")String sjrq);
}
