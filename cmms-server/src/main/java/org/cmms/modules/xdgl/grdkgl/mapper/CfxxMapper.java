package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Cfxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 厂房信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface CfxxMapper extends BaseMapper<Cfxx> {

	public boolean deleteByMainId(String mainId);

	public List<Cfxx> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取固定资产信息:厂房信息
	 * @param id
	 * @return
	 */
	List<Cfxx> queryFactoryInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取固定资产信息:厂房信息
	 * @param id
	 * @return
	 */
	List<Cfxx> queryFactoryDataById(@Param("id") String id);
}
