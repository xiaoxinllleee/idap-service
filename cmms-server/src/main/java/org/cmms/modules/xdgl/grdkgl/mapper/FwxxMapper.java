package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.CamsZcsxFw;
import org.cmms.modules.xdgl.grdkgl.entity.Fwxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface FwxxMapper extends BaseMapper<Fwxx> {

	public boolean deleteByMainId(String mainId);

	public List<Fwxx> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取固定资产信息:房屋信息
	 * @param id
	 * @return
	 */
	List<Fwxx> queryHouseInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取固定资产信息:房屋信息
	 * @param id
	 * @return
	 */
	List<Fwxx> queryHouseDataById(@Param("id") String id);
}
