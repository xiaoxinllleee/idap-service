package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Dydb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * @Description: 抵押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface DydbMapper extends BaseMapper<Dydb> {

	public boolean deleteByMainId(String mainId);

	public List<Dydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:抵押担保信息
	 * @param id
	 * @return
	 */
	List<Dydb> queryMortgageGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:抵押担保信息
	 * @param id
	 * @return
	 */
	List<Dydb> queryMortgageGuaranteeDataById(@Param("id") String id);
}
