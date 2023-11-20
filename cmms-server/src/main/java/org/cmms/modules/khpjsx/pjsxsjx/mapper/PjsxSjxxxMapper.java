package org.cmms.modules.khpjsx.pjsxsjx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.pjsxsjx.entity.PjsxSjxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级授信数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface PjsxSjxxxMapper extends BaseMapper<PjsxSjxxx> {

    PjsxSjxxx querySjxid(Map<String,String> sql);

}
