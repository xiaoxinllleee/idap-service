package org.cmms.modules.dkjkpt.dkjkptfxtsh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DedkmxVo;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DkjkptFxtsh;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.YqdkmxVo;

/**
 * @Description: 风险提示函
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
public interface DkjkptFxtshMapper extends BaseMapper<DkjkptFxtsh> {
    void init(@Param("tjyf")String tjyf);

    List<DedkmxVo> dedk(@Param("table")String table, @Param("jgdm")String jgdm, @Param("tjyf") String tjyf);
    List<YqdkmxVo> yqdk(@Param("table")String table, @Param("jgdm")String jgdm, @Param("tjyf") String tjyf);
}
