package org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

import java.math.BigDecimal;

/**
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Data
public class WgxqtjVo {
	@Dict(dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;//网格编号
	private Integer zhs;//总户数
	private Integer zrs;//总人数
	private Integer clkhs;//我行客户数
	private Integer qzkhs;//潜在客户数

	//业务情况
	private Integer ckkhs;//存款客户数

	private java.math.BigDecimal ckye;//存款总额
	private java.lang.Integer zhnckkhs;//支行内客户数


	private java.math.BigDecimal zhnckye;//支行内存款余额
	private java.math.BigDecimal zhwckye;//支行外存款余额
	private java.lang.Integer zhwkhs;//支行外客户数

	private Integer dkkhs;//贷款客户数
	private java.math.BigDecimal dkfgm;//贷款覆盖面

	private java.math.BigDecimal zjghl;//贷款覆盖面

	private java.math.BigDecimal dkye;//贷款总额

	private java.lang.Integer zhndkhs;//支行内贷款户数

	private java.math.BigDecimal zhndkhszb;//支行内贷款客户占比



	private java.math.BigDecimal zhndkye;//支行内贷款余额


	private java.math.BigDecimal zhwdkye;//支行外贷款余额


	private java.lang.Integer zhwdkhs;//支行外贷款户数
	private java.math.BigDecimal zhwdkhszb;//支行外贷款户数

	private java.lang.Integer dkwyekhs;//贷款无余额客户数

	private java.math.BigDecimal dkje;//合同金额
	private java.math.BigDecimal cknrp;//存款年日平

	private java.math.BigDecimal bldkye;//表内不良贷款余额

	private java.math.BigDecimal bnbldkye;//表外不良贷款余额、
	private java.math.BigDecimal bwbldkye;//表外不良贷款余额
	private java.lang.Integer bnbldkhs;//支行内表内不良贷款
	private java.lang.Integer bwbldkhs;//表外不良贷款户数

	private java.lang.Integer zhnbnbldkhs;//支行内表内不良贷款户数
	private java.math.BigDecimal zhnbnbldkye;//支行内表内不良贷款余额
	private java.math.BigDecimal zhwbnbldkye;//支行外表内不良贷款余额
	private java.lang.Integer zhwbnbldkhs;//支行外表内不良贷款户数

	private java.lang.Integer zhnbwbldkhs;//支行内表外不良贷款户数
	private java.math.BigDecimal zhnbwbldkye;//支行内表外不良贷款余额
	private java.math.BigDecimal zhwbwbldkye;//支行外表外不良贷款余额
	private java.lang.Integer zhwbwbldkhs;//支行外表外不良贷款户数

	private java.lang.Integer  sbkkhs; //我行社保卡

	private java.lang.Integer  wbsbk; //外部社保卡

	private java.lang.Integer sjyhkhs; //手机银行
	private java.lang.Integer  etckhs;// ETC客户数


	//走访数据
	private java.lang.Integer bmdlrhs;//白名单
	private java.lang.Integer huimdhs;//灰名单
	private java.lang.Integer heimdhs;//黑名单
	private java.lang.Integer bkbpyhs;//背靠背评议户数
	private java.lang.Integer zfhs; //走访户数
	private java.lang.Integer sxhs;//授信户数
	private java.math.BigDecimal nhzzsxed;//授信金额
	private java.lang.Integer zlsxhs;//授信户数增量

	private java.math.BigDecimal  zlsxje;//授信金额增量
	private java.lang.Integer yxhs;//用信户数
	private java.math.BigDecimal  yxje;//用信金额
	private java.lang.Integer zlhs;//用信户数增量
	private java.math.BigDecimal zlyxje;//用信金额增量

	//外表数据

	private java.lang.Integer dyrs;//党员人数
	private java.lang.Integer gzryrs;//公职人员
	private java.lang.Integer tgjsrs;//特岗教师
	private java.lang.Integer tpjch;//脱贫检测
	private java.lang.Integer wbdbh;//五保、低保
	private java.lang.Integer zdjbrs;//重大疾病
	private java.lang.Integer zpry;//诈骗人员
	private java.lang.Integer ffjzrs;//非法集资
	private java.lang.Integer xdry;//吸毒人员
	private java.lang.Integer fxry;//服刑人员
	private java.lang.Integer ssrs;//诉讼
	private java.lang.Integer sfsa;//涉案人员

	private java.lang.Integer wgxz;//网格性质


	/*

	public BigDecimal getCkye() {
		return ckye==null?BigDecimal.valueOf(0):ckye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}
	public BigDecimal getZhnckye() {
		return zhnckye==null?BigDecimal.valueOf(0):zhnckye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhwckye() {
		return zhwckye==null?BigDecimal.valueOf(0):zhwckye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getDkye() {
		return dkye==null?BigDecimal.valueOf(0):dkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhndkye() {
		return zhndkye==null?BigDecimal.valueOf(0):zhndkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhwdkye() {
		return zhwdkye==null?BigDecimal.valueOf(0):zhwdkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getDkje() {
		return dkje==null?BigDecimal.valueOf(0):dkje.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getCknrp() {
		return cknrp==null?BigDecimal.valueOf(0):cknrp.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getBldkye() {
		return bldkye==null?BigDecimal.valueOf(0):bldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getBnbldkye() {
		return bnbldkye==null?BigDecimal.valueOf(0):bnbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getBwbldkye() {
		return bwbldkye==null?BigDecimal.valueOf(0):bwbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhnbnbldkye() {
		return zhnbnbldkye==null?BigDecimal.valueOf(0):zhnbnbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhwbnbldkye() {
		return zhwbnbldkye==null?BigDecimal.valueOf(0):zhwbnbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhnbwbldkye() {
		return zhnbwbldkye==null?BigDecimal.valueOf(0):zhnbwbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZhwbwbldkye() {
		return zhwbwbldkye==null?BigDecimal.valueOf(0):zhwbwbldkye.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getNhzzsxed() {
		return nhzzsxed==null?BigDecimal.valueOf(0):nhzzsxed.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZlsxje() {
		return zlsxje==null?BigDecimal.valueOf(0):zlsxje.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getYxje() {
		return yxje==null?BigDecimal.valueOf(0):yxje.divide(BigDecimal.valueOf(10000)).setScale(2);
	}

	public BigDecimal getZlyxje() {
		return zlyxje==null?BigDecimal.valueOf(0):zlyxje.divide(BigDecimal.valueOf(10000)).setScale(2);
	}*/
}



