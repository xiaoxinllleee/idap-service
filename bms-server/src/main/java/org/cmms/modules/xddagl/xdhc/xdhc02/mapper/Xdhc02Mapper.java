package org.cmms.modules.xddagl.xdhc.xdhc02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
public interface Xdhc02Mapper extends BaseMapper<Xdhc02> {
    public void pXdhc02();
}
