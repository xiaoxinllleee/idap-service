package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 存量个人客户资料信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface IClkhzlxxService extends IService<Clkhzlxx> {

	public List<Clkhzlxx> selectByMainId(String mainId);
}
