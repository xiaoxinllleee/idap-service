package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.grkhgl.entity.VKhglGrkhgl;
import org.springframework.stereotype.Component;

/**
 * @Description: 视图
 * @Author: jeecg-boot
 * @Date:   2020-07-20
 * @Version: V1.0
 */
@Component
public interface VKhglGrkhglMapper extends BaseMapper<VKhglGrkhgl> {
    public Integer addJtcyxx( VKhglGrkhgl v);
}
