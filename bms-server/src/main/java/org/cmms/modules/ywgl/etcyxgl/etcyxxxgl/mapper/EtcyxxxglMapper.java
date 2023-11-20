package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;

/**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
public interface EtcyxxxglMapper extends BaseMapper<Etcyxxxgl> {
    Date getMaxTjyf();
}
