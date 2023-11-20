package org.cmms.modules.yxdygl.yxdyfjxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;

import java.util.List;

/**
 * @Description: 营销单元附件信息
 * @Author: jeecg-boot
 * @Date:   2020-07-28
 * @Version: V1.0
 */
public interface IYxdyfjxxService extends IService<Yxdyfjxx> {

	public List<Yxdyfjxx> selectByMainId(String mainId);
}
