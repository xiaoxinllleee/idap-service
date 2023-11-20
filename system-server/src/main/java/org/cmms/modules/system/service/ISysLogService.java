package org.cmms.modules.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.system.entity.SysLog;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-26
 */
public interface ISysLogService extends IService<SysLog> {

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
	Long findTodayVisitCount(Date dayStart, Date dayEnd);

	/**
	 * 日志添加
	 * @param LogContent 内容
	 * @param logType 日志类型(0:操作日志;1:登录日志;2:定时任务)
	 * @param operatetype 操作类型(1:添加;2:修改;3:删除;)
	 */
	void addLog(String LogContent, Integer logType, Integer operatetype);


	/**
	 * 获取总客户数
	 *
	 * @return Long
	 */
	List<Map<String,Object>> khCount(String jgdm);
	List<Map<String,Object>> zhkhCount();

	Long ckye(String jgdm);
	Long zhckye();
	Long dkye(String jgdm);
	Long zhdkye();
	Long zrckye(String tablename,String jgdm);
	Long zrzhckye(String tablename);
	Long zrdkye(String tablename,String jgdm);
	Long zrzhdkye(String tablename);
	Date cksjrq();
	Date dksjrq();

	String cksjrqBig();
	String dksjrqBig();

	String cksjrkrq();
	String dksjrkrq();

	Long shlx(Date sxr, String jgdm);
	Long zhshlx(Date sxr);
	String zrcksjrq();
	String zrdksjrq();
	String nccksjrq();
	String ncdksjrq();
	String getywjgdm(String zzbz);
	String getsjzzbz(String zzbz);
	String getzzlb(String zzbz);
	String getZzjb(String zzbz);
	Long getQhZfhs(Date tjyf);
	Long getZhZfhs(Date tjyf,String zzbz);
	Long getQhJdrs(Date tjyf);
	Long getZhJdrs(Date tjyf,String zzbz);
	/**
	 * 获取系统今日访问 IP数
	 *
	 * @return Long
	 */
	Long findTodayIp(Date dayStart, Date dayEnd);
	//update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数

	/**
	 *   首页：根据时间统计访问数量/ip数量
	 * @param dayStart
	 * @param dayEnd
	 * @return
	 */
	List<Map<String,Object>> findVisitCount(Date dayStart, Date dayEnd);

	Map<String, Object> getPjsxxx(Date tjyf);

	Map<String, Object> getQhPjsxxx(Date tjyf);

	Map<String, Object> getZhPjsxxx(Date tjyf,String zzbz);

	Map<String, Object> getNdzbrw(Date rwnd,String zzbz);

}
