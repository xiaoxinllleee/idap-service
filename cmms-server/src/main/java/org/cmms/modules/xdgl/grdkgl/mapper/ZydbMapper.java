package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Zydb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 质押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface ZydbMapper extends BaseMapper<Zydb> {

	public boolean deleteByMainId(String mainId);

	public List<Zydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:质押担保信息
	 * @param id
	 * @return
	 */
	List<Zydb> queryPledgeGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:质押担保信息
	 * @param id
	 * @return
	 */
	List<Zydb> queryPledgeGuaranteeDataById(@Param("id") String id);
}
