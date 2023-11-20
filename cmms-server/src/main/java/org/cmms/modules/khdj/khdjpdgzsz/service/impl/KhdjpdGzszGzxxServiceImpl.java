package org.cmms.modules.khdj.khdjpdgzsz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;
import org.cmms.modules.khdj.khdjpdgzsz.mapper.KhdjpdGzszGzxxMapper;
import org.cmms.modules.khdj.khdjpdgzsz.service.IKhdjpdGzszGzxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class KhdjpdGzszGzxxServiceImpl extends ServiceImpl<KhdjpdGzszGzxxMapper, KhdjpdGzszGzxx> implements IKhdjpdGzszGzxxService {

	@Autowired
	private KhdjpdGzszGzxxMapper khdjpdGzszGzxxMapper;

	@Override
	public List<KhdjpdGzszGzxx> selectByMainId(String mainId) {
		return khdjpdGzszGzxxMapper.selectByMainId(mainId);
	}

	@Override
	public List<KhdjpdGzszGzxx> selectByMainIdAndKey(String mainId, String key) {
		return khdjpdGzszGzxxMapper.selectByMainIdAndKey(mainId, key);
	}
}
