package org.cmms.modules.khlc.khfagl.vo;

import lombok.Data;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;

import java.io.Serializable;
import java.util.List;

@Data
public class ObjPostVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private String schemeId;
	//考核对象
	private String evlObjType;
	private String tsgz;
	//考核场景
	private String khcj;
	private String[] objIds;
	private String[] postIds;
	private String[] zbids;

	private List<Vhrbasstaffpost>  khdxs;
	/**对应的用户id集合*/
	public ObjPostVo(String schemeId,String evlObjType,String tsgz,String khcj,String[] objIds, String[] postIds, String[] zbids,List<Vhrbasstaffpost> khdxs) {
		super();
		this.schemeId = schemeId;
		this.evlObjType = evlObjType;
		this.tsgz = tsgz;
		this.khcj = khcj;
		this.objIds = objIds;
		this.postIds = postIds;
		this.zbids = zbids;
		this.khdxs = khdxs;
	}



}
