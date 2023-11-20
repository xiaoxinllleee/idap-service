package org.cmms.modules.khgl.sh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.sh.entity.Shpjsxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
public interface ShpjsxxxMapper extends BaseMapper<Shpjsxxx> {

    public Map<String,Object> queryZcxx(@Param("shid") String shid);
}
