package org.cmms.modules.khgl.khxx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khxx.entity.vKhglKhjbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
public interface vKhglKhjbxxMapper extends BaseMapper<vKhglKhjbxx> {

    public  Map<String,Object> selectByHhbm(String hhbm);
}
