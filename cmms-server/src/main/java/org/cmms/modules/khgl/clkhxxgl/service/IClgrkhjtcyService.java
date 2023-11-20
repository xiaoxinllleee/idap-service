package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 存量个人客户家庭成员
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface IClgrkhjtcyService extends IService<Clgrkhjtcy> {

	public List<Clgrkhjtcy> selectByMainId(String mainId);
}
