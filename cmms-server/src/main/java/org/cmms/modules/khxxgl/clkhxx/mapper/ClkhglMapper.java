package org.cmms.modules.khxxgl.clkhxx.mapper;

import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量客户管理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface ClkhglMapper extends BaseMapper<Clkhgl> {
    public void extract();

    public int syncYesClkhxx();
}
