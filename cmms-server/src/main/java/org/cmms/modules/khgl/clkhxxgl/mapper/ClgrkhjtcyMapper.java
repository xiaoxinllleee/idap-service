package org.cmms.modules.khgl.clkhxxgl.mapper;

import java.util.List;
import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量个人客户家庭成员
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface ClgrkhjtcyMapper extends BaseMapper<Clgrkhjtcy> {

	public boolean deleteByMainId(String mainId);

	public List<Clgrkhjtcy> selectByMainId(String mainId);
}
