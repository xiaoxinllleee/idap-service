package org.cmms.modules.khgl.wcwgkhmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.wcwgkhmx.entity.Wcwgkhmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 外出务工客户明细
 * @Author: penghr
 * @Date:   2020-02-11
 * @Version: V1.0
 */
public interface WcwgkhmxMapper extends BaseMapper<Wcwgkhmx> {
    public void initData();
}
