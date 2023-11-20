package org.cmms.modules.yxgl.mapper;

import java.util.List;
import org.cmms.modules.yxgl.entity.Khhffjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface KhhffjxxMapper extends BaseMapper<Khhffjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<Khhffjxx> selectByMainId(String mainId);
}
