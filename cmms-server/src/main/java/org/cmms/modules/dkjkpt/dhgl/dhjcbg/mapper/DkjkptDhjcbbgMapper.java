package org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper;

import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VDkjkptDhjcbbg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
public interface DkjkptDhjcbbgMapper extends BaseMapper<VDkjkptDhjcbbg> {
    public List<VDkjkptDhjcbbg> selectByMainId(String mainId);

}
