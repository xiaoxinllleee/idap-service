package org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VdkjkptDhdksjtz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
public interface VdkjkptDhdksjtzMapper extends BaseMapper<VdkjkptDhdksjtz> {

    List<VdkjkptDhdksjtz> getListClaim(@Param("khmc") String khmc,@Param("zjhm") String zjhm);

}
