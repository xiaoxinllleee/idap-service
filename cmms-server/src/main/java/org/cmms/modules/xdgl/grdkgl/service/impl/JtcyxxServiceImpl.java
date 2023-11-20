package org.cmms.modules.xdgl.grdkgl.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.xdgl.grdkgl.entity.Jtcyxx;
import org.cmms.modules.xdgl.grdkgl.mapper.JtcyxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IJtcyxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 家庭成员
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class JtcyxxServiceImpl extends ServiceImpl<JtcyxxMapper, Jtcyxx> implements IJtcyxxService {

	@Autowired
	private JtcyxxMapper mapper;

	@Override
	public List<Jtcyxx> selectByMainId(String hhbm ,String zjhm) {
		return baseMapper.selectByMainId(hhbm,zjhm);
	}

	@Override
	public Jtcyxx selectPeiOuByHhbm(String hhbm) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("HHBM",hhbm);
		queryWrapper.eq("YHZGX","2");
		List<Jtcyxx> list = baseMapper.selectList(queryWrapper);
		if (CollUtil.isNotEmpty(list))
			return list.get(0);
		return null;
	}

	@Override
	public List<Jtcyxx> queryFamilyListByHhbmAndId(String hhbm, String id) {
		return mapper.queryFamilyListByHhbmAndId(hhbm,id);
	}

	@Override
	public List<Jtcyxx> queryFamilyDataByHhbmAndID(String hhbm, String id) {
		return mapper.queryFamilyDataByHhbmAndID(hhbm,id);
	}

	@Override
	public void deleteFamilyInfoById(String id) {
		mapper.deleteFamilyInfoById(id);
	}
}
