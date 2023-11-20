package org.cmms.modules.khpjsx.pjzxmsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;

import java.util.List;

/**
 * @Description: 金融业务信息
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IPJSX_PJZXMGZSZ_GSService extends IService<PJSX_PJZXMGZSZ_GS> {

	public List<PJSX_PJZXMGZSZ_GS> selectByMainId(String mainId,String khlx);
}
