package org.cmms.modules.xdgl.grdkgl.vo;

import lombok.Data;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.io.Serializable;
import java.util.List;

@Data
public class JtspxzcyVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String zzbz;
	private String khmc;
	private String zjhm;
	private List<Jtspcy> jtspcyList;
	/**对应的用户id集合*/
	public JtspxzcyVO(String khmc, String zjhm,String id, String zzbz,List<Jtspcy> jtspcyList) {
		super();
		this.id = id;
		this.zzbz = zzbz;
		this.khmc = khmc;
		this.zjhm = zjhm;
		this.jtspcyList = jtspcyList;
	}



}
