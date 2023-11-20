package org.cmms.modules.system.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.IPUtils;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.system.entity.SysLog;
import org.cmms.modules.system.mapper.SysLogMapper;
import org.cmms.modules.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-26
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Resource
	private SysLogMapper sysLogMapper;

	@Autowired
	@Lazy
	private ISysBaseAPI sysBaseAPI;
	/**
	 * @功能：清空所有日志记录
	 */
	@Override
	public void removeAll() {
		sysLogMapper.removeAll();
	}

	@Override
	public Long findTotalVisitCount() {
		return sysLogMapper.findTotalVisitCount();
	}

	@Override
	public List<Map<String,Object>> khCount(String jgdm) {
		return sysLogMapper.khCount(jgdm);
	}

	@Override
	public Long zrckye(String tablename,String jgdm) {
		return sysLogMapper.zrckye(tablename,jgdm);
	}

	@Override
	public Long zrzhckye(String tablename) {
		return sysLogMapper.zrzhckye(tablename);
	}

	@Override
	public Long zrdkye(String tablename,String jgdm) {
		return sysLogMapper.zrdkye(tablename,jgdm);
	}

	@Override
	public Long zrzhdkye(String tablename) {
		return sysLogMapper.zrzhdkye(tablename);
	}


	public Long ckye(String jgdm) {
		return sysLogMapper.ckye(jgdm);
	}
	@Override
	public Long zhckye() {
		return sysLogMapper.zhckye();
	}

	@Override
	public Long dkye(String jgdm) {
		return sysLogMapper.dkye(jgdm);
	}
	@Override
	public Long zhdkye() {
		return sysLogMapper.zhdkye();
	}


	@Override
	public Date cksjrq() {
		return sysLogMapper.cksjrq();
	}

	@Override
	public Date dksjrq() {
		return sysLogMapper.dksjrq();
	}

	@Override
	@DS("sjxf")
	public String cksjrqBig() {
		return sysLogMapper.cksjrqBig();
	}
	@Override
	@DS("sjxf")
	public String dksjrqBig() {
		return sysLogMapper.dksjrqBig();
	}

	@Override
	public String cksjrkrq()  { return sysLogMapper.cksjrkrq();  }

	@Override
	public String dksjrkrq()  { return sysLogMapper.dksjrkrq();  }

	@Override
	public List<Map<String,Object>> zhkhCount() {
		return sysLogMapper.zhkhCount();
	}

	@Override
	public Long shlx(Date sxr, String jgdm) {
		return sysLogMapper.shlx(sxr,jgdm);
	}

	@Override
	public Long zhshlx(Date sxr) {
		return sysLogMapper.zhshlx(sxr);
	}
	@Override
	public String zrcksjrq(){
		return sysLogMapper.zrcksjrq();
	}
	@Override
	public String zrdksjrq(){
		return sysLogMapper.zrdksjrq();
	}


	@Override
	public String nccksjrq(){
		return sysLogMapper.nccksjrq();
	}
	@Override
	public String ncdksjrq(){
		return sysLogMapper.ncdksjrq();
	}


	//update-begin--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
	@Override
	public Long findTodayVisitCount(Date dayStart, Date dayEnd) {
		return sysLogMapper.findTodayVisitCount(dayStart,dayEnd);
	}


	@Override
	public Long findTodayIp(Date dayStart, Date dayEnd) {
		return sysLogMapper.findTodayIp(dayStart,dayEnd);
	}
	//update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数

	@Override
	public List<Map<String,Object>> findVisitCount(Date dayStart, Date dayEnd) {
		try {
			String dbType = sysBaseAPI.getDatabaseType();
			return sysLogMapper.findVisitCount(dayStart, dayEnd,dbType);
		} catch (SQLException e) {
		}
		return null;
	}

	@Override
	public void addLog(String LogContent, Integer logType, Integer operatetype) {
		SysLog sysLog = new SysLog();
		//注解上的描述,操作日志内容
		sysLog.setLogContent(LogContent);
		sysLog.setLogType(logType);
		sysLog.setOperateType(operatetype);

		//请求的方法名
		//请求的参数

		try {
			//获取request
			HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
			//设置IP地址
			sysLog.setIp(IPUtils.getIpAddr(request));
		} catch (Exception e) {
			sysLog.setIp("127.0.0.1");
		}

		//获取登录用户信息
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if(sysUser!=null){
			sysLog.setUserid(sysUser.getUsername());
			sysLog.setUsername(sysUser.getRealname());

		}
		sysLog.setCreateTime(new Date());
		//保存系统日志
		sysLogMapper.insert(sysLog);
	}

	/*@Override
	public List<Map<String,Object>> findVisitCount(Date dayStart, Date dayEnd) {
		return sysLogMapper.findVisitCount(dayStart, dayEnd);
	}*/
	@Override
	public String getywjgdm(String zzbz){
		return sysLogMapper.getywjgdm(zzbz);
	}

	@Override
	public String getsjzzbz(String zzbz){
		return sysLogMapper.getsjzzbz(zzbz);
	}

	@Override
	public String getzzlb(String zzbz){
		return sysLogMapper.getzzlb(zzbz);
	}

	@Override
	public String getZzjb(String zzbz){
		return sysLogMapper.getzzjb(zzbz);
	}

	@Override
	public Long getQhZfhs(Date tjyf) {
		return sysLogMapper.getQhZfhs(tjyf);
	}

	@Override
	public Long getZhZfhs(Date tjyf, String zzbz) {
		return sysLogMapper.getZhZfhs(tjyf,zzbz);
	}

	@Override
	public Long getQhJdrs(Date tjyf) {
		return sysLogMapper.getQhJdrs(tjyf);
	}

	@Override
	public Long getZhJdrs(Date tjyf, String zzbz) {
		return sysLogMapper.getZhJdrs(tjyf,zzbz);
	}


	@Override
	public Map<String,Object> getPjsxxx(Date tjyf) {
		return sysLogMapper.getPjsxxx(tjyf);
	}

	@Override
	public Map<String, Object> getQhPjsxxx(Date tjyf) {
		return sysLogMapper.getQhPjsxxx(tjyf);
	}

	@Override
	public Map<String, Object> getZhPjsxxx(Date tjyf, String zzbz) {
		return sysLogMapper.getZhPjsxxx(tjyf,zzbz);

	}

	@Override
	public Map<String, Object> getNdzbrw(Date rwnd, String zzbz) {
		return sysLogMapper.getNdzbrw(rwnd,zzbz);

	}
}
