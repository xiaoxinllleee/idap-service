package org.cmms.modules.xddagl.dkdagl.dhjczllr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjcbgfjxx;

import java.util.List;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
public interface DhjcbgfjxxMapper extends BaseMapper<Dhjcbgfjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<Dhjcbgfjxx> selectByMainId(String mainId);
}
