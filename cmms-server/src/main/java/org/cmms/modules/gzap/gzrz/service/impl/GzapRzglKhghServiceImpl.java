package org.cmms.modules.gzap.gzrz.service.impl;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import org.cmms.modules.gzap.gzrz.mapper.GzapRzglKhghMapper;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglKhghService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@Service
public class GzapRzglKhghServiceImpl extends ServiceImpl<GzapRzglKhghMapper, GzapRzglKhgh> implements IGzapRzglKhghService {
	
	@Autowired
	private GzapRzglKhghMapper gzapRzglKhghMapper;
	
	@Override
	public List<GzapRzglKhgh> selectByMainId(String mainId) {
		return gzapRzglKhghMapper.selectByMainId(mainId);
	}
}
