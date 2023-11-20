package org.cmms.modules.gzap.gzrz.mapper;

import java.util.List;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
public interface GzapRzglKhghMapper extends BaseMapper<GzapRzglKhgh> {

	public boolean deleteByMainId(String mainId);
    
	public List<GzapRzglKhgh> selectByMainId(String mainId);
}
