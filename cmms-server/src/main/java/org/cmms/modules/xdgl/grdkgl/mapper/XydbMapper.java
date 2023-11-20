package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Xydb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 信用担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface XydbMapper extends BaseMapper<Xydb> {

	public boolean deleteByMainId(String mainId);

	public List<Xydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:信用担保
	 * @param id
	 * @return
	 */
	List<Xydb> queryCreditGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:信用担保
	 * @param id
	 * @return
	 */
	List<Xydb> queryCreditGuaranteeDataById(@Param("id") String id);
}
