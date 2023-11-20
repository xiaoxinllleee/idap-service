package org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper;

import java.util.List;

import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
public interface DkjkptDhjcbgfjxxMapper extends BaseMapper<DkjkptDhjcbgfjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<DkjkptDhjcbgfjxx> selectByMainId(String mainId);
}
