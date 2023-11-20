package org.cmms.modules.system.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.SysLog;

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-26
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

	/**
	 * @功能：清空所有日志记录
	 */
	public void removeAll();

	/**
	 * 获取系统总访问次数
	 *
	 * @return Long
	 */
	Long findTotalVisitCount();

	//update-begin--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
	/**
	 * 获取系统今日访问次数
	 *
	 * @return Long
	 */
	Long findTodayVisitCount(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);

	/**
	 * 获取系统今日访问 IP数
	 *
	 * @return Long
	 */
	Long findTodayIp(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);
	//update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数

	/**
	 * 获取总客户数
	 *
	 * @return Long
	 */
	List<Map<String,Object>> khCount(@Param("jgdm") String jgdm);

	List<Map<String,Object>> zhkhCount();

	Long ckye(@Param("jgdm") String jgdm);
	Long zhckye();
	Long dkye(@Param("jgdm") String jgdm);
	Long zhdkye();

	Long zrckye(@Param("tablename") String tablename,@Param("jgdm") String jgdm);
	Long zrzhckye(@Param("tablename") String tablename);
	Long zrdkye(@Param("tablename") String tablename,@Param("jgdm") String jgdm);
	Long zrzhdkye(@Param("tablename") String tablename);

	Date cksjrq();
	Date dksjrq();

	String cksjrqBig();
	String dksjrqBig();

	String cksjrkrq();
	String dksjrkrq();

	Long shlx(@Param("sxr") Date sxr,@Param("jgdm") String jgdm);
	Long zhshlx(@Param("sxr") Date sxr);
    String zrcksjrq();
	String nccksjrq();
	String zrdksjrq();
	String ncdksjrq();

	String getywjgdm(@Param("zzbz") String  zzbz);
	String getsjzzbz(@Param("zzbz") String  zzbz);
	String getzzlb(@Param("zzbz") String  zzbz);
	String getzzjb(@Param("zzbz") String  zzbz);



	/**
	 *   首页：根据时间统计访问数量/ip数量
	 * @param dayStart
	 * @param dayEnd
	 * @return
	 */
	List<Map<String,Object>> findVisitCount(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd, @Param("dbType") String dbType);

	Map<String,Object> getPjsxxx(@Param("tjyf") Date tjyf);


	Long  getQhZfhs(@Param("tjyf") Date tjyf);
	Long  getZhZfhs(@Param("tjyf") Date tjyf,@Param("zzbz") String  zzbz);
	Long  getQhJdrs(@Param("tjyf") Date tjyf);
	Long  getZhJdrs(@Param("tjyf") Date tjyf,@Param("zzbz") String  zzbz);
	Map<String,Object> getQhPjsxxx(@Param("tjyf") Date tjyf);
	Map<String,Object> getZhPjsxxx(@Param("tjyf") Date tjyf,@Param("zzbz") String  zzbz);

	Map<String,Object> getNdzbrw(@Param("rwnd") Date rwnd,@Param("zzbz") String  zzbz);


}
