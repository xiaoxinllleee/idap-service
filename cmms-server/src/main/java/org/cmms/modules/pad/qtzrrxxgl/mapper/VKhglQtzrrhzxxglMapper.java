package org.cmms.modules.pad.qtzrrxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxgl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
public interface VKhglQtzrrhzxxglMapper extends BaseMapper<VKhglQtzrrhzxxgl> {

    public void init(String hhbm);
}
