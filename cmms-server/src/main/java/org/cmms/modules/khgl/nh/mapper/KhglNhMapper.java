package org.cmms.modules.khgl.nh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.KhglNhhzzllb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface KhglNhMapper extends BaseMapper<KhglNhhzzllb> {

    public List<KhglNhhzzllb> selectByMainId(String hhbm);

}
