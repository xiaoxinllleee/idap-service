package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Clxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 车辆信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface ClxxMapper extends BaseMapper<Clxx> {

	public boolean deleteByMainId(String mainId);

	public List<Clxx> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取固定资产信息:车辆信息
	 * @param id
	 * @return
	 */
	List<Clxx> queryVehicleInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取固定资产信息:车辆信息
	 * @param id
	 * @return
	 */
	List<Clxx> queryVehicleDataById(@Param("id") String id);

	String getPljzByZjhm(String zjhm);
}
