package org.cmms.modules.khjg.zhfyfp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
public interface ZhfyfpHzMapper extends BaseMapper<ZhfyfpHz> {
    public void extract(@Param("sql") String sql,@Param("fpyf") String fpyf, @Param("fylx")  String fylx);
}
