package org.cmms.modules.khgl.clkhxxgl.mapper;

import java.util.List;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量个人客户资料信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface ClkhzlxxMapper extends BaseMapper<Clkhzlxx> {

	public boolean deleteByMainId(String mainId);

	public List<Clkhzlxx> selectByMainId(String mainId);
}
