package org.cmms.modules.tjfx.birthdayreminder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.birthdayreminder.entity.srtx;


/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
public interface srtxMapper extends BaseMapper<srtx> {

    public void initDataBySheduler();
}
