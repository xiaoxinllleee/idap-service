package org.cmms.modules.khgl.qtzrr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.qtzrr.entity.KhglQtzrrhzzllb;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface QtzrrhzzllbMapper extends BaseMapper<KhglQtzrrhzzllb> {

    public List<KhglQtzrrhzzllb> selectByMainId(String hhbm);

}
