package org.cmms.modules.yxdygl.cwhfjl.entity;

import lombok.Data;
import lombok.ToString;
import org.cmms.modules.yxdygl.yxdyglmain.entity.WgxxFjVO;

import java.util.List;

/**
 * @Description: 网格基本信息
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
@ToString
public class CwhfxxVO {
	/**网格名称*/
	private String wgbh;
	private String zzbz;

	private String hfmd;
	private String hfqk;
	private String cwpj;


	private List<WgxxFjVO> yxdyfjxxList;

}
