package org.cmms.modules.khgl.qtzrr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.qtzrr.entity.CamsZcsxQtzrrcjxx;

import java.util.List;

/**
 * @Description: 其他自然人采集信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface ICamsZcsxQtzrrcjxxService extends IService<CamsZcsxQtzrrcjxx> {

	public List<CamsZcsxQtzrrcjxx> selectByMainId(String mainId);
}
