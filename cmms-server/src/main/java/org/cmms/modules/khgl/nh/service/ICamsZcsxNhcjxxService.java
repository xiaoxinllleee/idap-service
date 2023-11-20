package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.CamsZcsxNhcjxx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 农户采集信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface ICamsZcsxNhcjxxService extends IService<CamsZcsxNhcjxx> {

	public List<CamsZcsxNhcjxx> selectByMainId(String mainId);
}
