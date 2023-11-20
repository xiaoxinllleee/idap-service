package org.cmms.modules.khgl.clkhxxgl.mapper;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量个人客户业务往来信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface ClkhywwlxxMapper extends BaseMapper<Clkhywwlxx> {

	public boolean deleteByMainId(String mainId);

	public Clkhywwlxx selectByMainId(String mainId);
}
