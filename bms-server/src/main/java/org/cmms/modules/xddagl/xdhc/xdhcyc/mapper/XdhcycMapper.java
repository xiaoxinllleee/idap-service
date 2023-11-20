package org.cmms.modules.xddagl.xdhc.xdhcyc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.Xdhcyc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 信贷T+1核查先隐藏
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
public interface XdhcycMapper extends BaseMapper<Xdhcyc> {
    public void pXdhcyc();

}
