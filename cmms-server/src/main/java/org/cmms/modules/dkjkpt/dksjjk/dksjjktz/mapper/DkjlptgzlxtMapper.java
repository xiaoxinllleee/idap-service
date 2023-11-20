package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjlptgzlxt;

/**
 * @Description: 贷款监控平台关注类_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-01
 * @Version: V1.0
 */
public interface DkjlptgzlxtMapper extends BaseMapper<Dkjlptgzlxt> {
    void gzl(@Param("tjnf") String tjnf,@Param("zjhm") String zjhm);
}
