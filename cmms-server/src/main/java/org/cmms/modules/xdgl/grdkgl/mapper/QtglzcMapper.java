package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Qtglzc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 其他固定资产
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface QtglzcMapper extends BaseMapper<Qtglzc> {

	public boolean deleteByMainId(String mainId);

	public List<Qtglzc> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取固定资产信息:其它固定资产
	 * @param id
	 * @return
	 */
	List<Qtglzc> queryOtherFixedAssetsInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取固定资产信息:其它固定资产
	 * @param id
	 * @return
	 */
	List<Qtglzc> queryOtherFixedAssetsDataById(@Param("id") String id);

	String getPjgByZjhm(String zjhm);
}
