package org.cmms.modules.gzap.gzrw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gzap.gzrw.entity.RwxfRwgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
public interface RwxfRwglMapper extends BaseMapper<RwxfRwgl> {

	public boolean deleteByMainId(String mainId);
    
	public List<RwxfRwgl> selectByMainId(@Param("id") String mainId);

}
