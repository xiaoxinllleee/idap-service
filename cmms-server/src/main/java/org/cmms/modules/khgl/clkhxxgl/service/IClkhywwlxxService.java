package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存量个人客户业务往来信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface IClkhywwlxxService extends IService<Clkhywwlxx> {

	public Clkhywwlxx selectByMainId(String mainId);
}
