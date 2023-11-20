package org.cmms.modules.gzap.gzjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gzap.gzjh.entity.GzapJhxfKhjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-26
 * @Version: V1.0
 */
public interface GzapJhxfKhjlMapper extends BaseMapper<GzapJhxfKhjl> {

	public boolean deleteByMainId(String mainId);
    
	public List<GzapJhxfKhjl> selectByMainId(@Param("id") String mainId);
}
