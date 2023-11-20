package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Bzdb;
import org.springframework.stereotype.Component;

/**
 * @Description: 保存担保
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
@Component
public interface BzdbMapper extends BaseMapper<Bzdb> {

	public boolean deleteByMainId(String mainId);

	public List<Bzdb> selectByMainId(String mainId);

	public String getJeByZjhm(String zjhm);

	/**
	 * 根据"ID"获取担保方式:保证担保信息
	 * @param id
	 * @return
	 */
	List<Bzdb> queryGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:保证担保信息
	 * @param id
	 * @return
	 */
	List<Bzdb> queryGuaranteeDataById(@Param("id") String id);
}
