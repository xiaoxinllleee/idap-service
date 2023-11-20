package org.cmms.modules.gzap.gzrz.mapper;

import java.util.List;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 今天计划总结
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
public interface GzapRzglJrjhzjMapper extends BaseMapper<GzapRzglJrjhzj> {

	public boolean deleteByMainId(String mainId);
    
	public List<GzapRzglJrjhzj> selectByMainId(String mainId);
}
