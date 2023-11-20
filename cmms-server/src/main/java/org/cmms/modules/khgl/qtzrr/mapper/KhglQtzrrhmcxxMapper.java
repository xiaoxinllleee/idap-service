package org.cmms.modules.khgl.qtzrr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.qtzrr.entity.KhglQtzrrhmcxx;

/**
 * @Description: 其他自然人花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface KhglQtzrrhmcxxMapper extends BaseMapper<KhglQtzrrhmcxx> {

    public  boolean deleteByMainId(String zjhm);

}
