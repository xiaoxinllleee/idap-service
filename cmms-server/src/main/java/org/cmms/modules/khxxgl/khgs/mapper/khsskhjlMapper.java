package org.cmms.modules.khxxgl.khgs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khgs.entity.khsskhjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户归属客户经理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface khsskhjlMapper extends BaseMapper<khsskhjl> {
    List<String> getKhjbzlZjhmKhjlgh(@Param("yggh") String yggh);

}
