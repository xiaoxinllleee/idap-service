package org.cmms.modules.gzap.rwxf.mapper;

import java.util.List;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface GzapRwxf_RwglMapper extends BaseMapper<GzapRwxf_Rwgl> {

	public boolean deleteByMainId(String mainId);
    
	public List<GzapRwxf_Rwgl> selectByMainId(String mainId);
}
