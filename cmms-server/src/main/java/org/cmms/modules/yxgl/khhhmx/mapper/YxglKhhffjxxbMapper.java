package org.cmms.modules.yxgl.khhhmx.mapper;

import java.util.List;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
public interface YxglKhhffjxxbMapper extends BaseMapper<YxglKhhffjxxb> {

	public boolean deleteByMainId(String mainId);
    
	public List<YxglKhhffjxxb> selectByMainId(String mainId);
}
