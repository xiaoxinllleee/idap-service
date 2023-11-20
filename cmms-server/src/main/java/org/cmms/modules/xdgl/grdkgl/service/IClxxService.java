package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Clxx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 车辆信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IClxxService extends IService<Clxx> {

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
}
