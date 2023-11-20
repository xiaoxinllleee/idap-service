package org.cmms.modules.khdj.khdjpdgzsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;

import java.util.List;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IKhdjpdGzszGzxxService extends IService<KhdjpdGzszGzxx> {

	public List<KhdjpdGzszGzxx> selectByMainId(String mainId);

	public List<KhdjpdGzszGzxx> selectByMainIdAndKey(String mainId, String key);
}
