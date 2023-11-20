package org.cmms.modules.khgl.xjjl.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.common.appfjxx.entity.AppFjxx;
import org.cmms.modules.common.appfjxx.service.IAppFjxxService;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.jhsh.service.ITgacsTpsMchntInfoService;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import org.cmms.modules.khgl.xjjl.service.IAppJhshXjjlService;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 聚合商户巡检记录
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="聚合商户巡检记录")
@RestController
@RequestMapping("/xjjl/appJhshXjjl")
public class AppJhshXjjlController extends JeecgController<AppJhshXjjl, IAppJhshXjjlService> {
	@Autowired
	private IAppJhshXjjlService appJhshXjjlService;
	@Autowired
	IAppFjxxService appFjxxService;
	@Autowired
	ISysDicService sysDicService;
	 @Autowired
	 private ITgacsTpsMchntInfoService tgacsTpsMchntInfoService;
	 /**
	 * 分页列表查询
	 *
	 * @param appJhshXjjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "聚合商户巡检记录-分页列表查询")
	@ApiOperation(value="聚合商户巡检记录-分页列表查询", notes="聚合商户巡检记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppJhshXjjl appJhshXjjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String mchntId, String namecn,
								   HttpServletRequest req) {
//		QueryWrapper<AppJhshXjjl> queryWrapper = QueryGenerator.initQueryWrapper(appJhshXjjl, req.getParameterMap());
//		if (StringUtils.isNotBlank(namecn)){
//			queryWrapper.like("simp_mchnt_name",namecn);
//		}
		QueryWrapper<AppJhshXjjl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("mchnt_id",mchntId);
		queryWrapper.orderByDesc("xjsj");
		Page<AppJhshXjjl> page = new Page<AppJhshXjjl>(pageNo, pageSize);
		IPage<AppJhshXjjl> pageList = appJhshXjjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}



	 /**
	  * 是否巡检
	  */
	 @GetMapping(value = "/getXj")
	 public boolean sfxj(@RequestParam(name="mchnt_id",required=true)String mchntId){
		 List<AppJhshXjjl> list = appJhshXjjlService.getXjsj(mchntId);
		 if (list.size()>0){
			return false;
		 }
		 return true;
	 }

	 /**
	  * 全部客户
	  */
	 @GetMapping(value = "/getAllList")
	 public Result<?> getAllList(int start,int end,String namecn){
		 List<TgacsTpsMchntInfo> list = appJhshXjjlService.getAllList(start, end, namecn);
		 return Result.ok(list);
	 }

	 /**
	  * 待巡检客户
	  */
	 @GetMapping(value = "/showXJ")
	 public Result<?> showXJ(int start,int end,String namecn){
		 List<TgacsTpsMchntInfo> list = appJhshXjjlService.showXJ(start, end, namecn);
		 return Result.ok(list);
	 }

	 /**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "聚合商户巡检记录-添加")
	@ApiOperation(value="聚合商户巡检记录-添加", notes="聚合商户巡检记录-添加")
	@RequestMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		//String uuid = UUID.randomUUID().toString().replace("-","");
		//String s = IdUtil.fastSimpleUUID();
		AppJhshXjjl appJhshXjjl = JSON.toJavaObject(jsonObject,AppJhshXjjl.class);
		String filePath = jsonObject.getString("filePath");
		JSONArray array = jsonObject.getJSONArray("filePath");
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		appJhshXjjl.setXjr(loginUser.getRealname());
		appJhshXjjl.setXjsj(new Date());

		if (appJhshXjjl.getType1() != null && appJhshXjjl.getType1() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType2() != null && appJhshXjjl.getType2() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType3() != null && appJhshXjjl.getType3() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType4() != null && appJhshXjjl.getType4() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType5() != null && appJhshXjjl.getType5() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType6() != null && appJhshXjjl.getType6() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType7() != null && appJhshXjjl.getType7() ==2){
			appJhshXjjl.setXjjg(2);
		}else if (appJhshXjjl.getType8() != null && appJhshXjjl.getType8() ==2){
			appJhshXjjl.setXjjg(2);
		}else {
			appJhshXjjl.setXjjg(1);
		}

//		if (appJhshXjjl.getType1() == 2 || appJhshXjjl.getType2() == 2 || appJhshXjjl.getType3() == 2 ||
//			appJhshXjjl.getType4() == 2 || appJhshXjjl.getType5() == 2 || appJhshXjjl.getType6() == 2 ||
//			appJhshXjjl.getType7() == 2 || appJhshXjjl.getType8() == 2)
//		{
//			appJhshXjjl.setXjjg(2);
//		}else {
//			appJhshXjjl.setXjjg(1);
//		}

		AppFjxx fjxx = null;
		if (array.size()>0){
//			String path =uploadpath+"/app/"+loginUser.getWorkNo()+"/";
//			File file = new File(path);
//			if (!file.exists()){
//				file.mkdirs();
//			}
//			InputStream in = null;
//			OutputStream out = null;
//			for (int i=0;i<array.size();i++){
//				String name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
//				String savePath=path+name;
//				String fwlj="/app/"+loginUser.getWorkNo()+"/"+name;
//				try {
//					File f=new File(array.get(i).toString());
//					in=new FileInputStream(f);
//					out =new FileOutputStream(new File(savePath));
//					int len = 0;
//					byte[] b = new byte[1024];
//					while ((len = in.read(b)) != -1) {
//						out.write(b);
//					}
//					out.close();
//					in.close();
//					f.delete();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			String s = IdUtil.fastSimpleUUID();

			for (int i = 0; i < array.size(); i++) {
				String o = (String)array.get(i);
				if (o.contains("app")){
					String app = o.substring(o.indexOf("app") - 1);
					fjxx = new AppFjxx();
					fjxx.setId(s);
					fjxx.setWjlj(uploadpath+app);
					fjxx.setFwlj(app);
					fjxx.setLrsj(appJhshXjjl.getXjsj());
					fjxx.setLrr(getWorkNo());
					appFjxxService.save(fjxx);
				}
			}
				appJhshXjjl.setFjxx(s);
		}

		SysDic sysDic = sysDicService.queryByCode("101001");
		if (sysDic != null && !QydmEnums.CILI.getQydmCode().equals(sysDic.getValue())) {


			String fwlj = "/sign/" + getUsername();
			boolean directory = FileUtil.isDirectory(uploadpath + fwlj);
			if (!directory) {
				FileUtil.mkdir(uploadpath + fwlj);
			}
			if (org.apache.commons.lang3.StringUtils.isNotBlank(appJhshXjjl.getXjyqm())) {
				String fw = fwlj + "/" + System.currentTimeMillis() + ".png";
				String wjlj = uploadpath + fw;
				Base64Util.toImage(appJhshXjjl.getXjyqm(), wjlj);
				AppFjxx appFjxx = new AppFjxx();
				String s = IdUtil.fastSimpleUUID();
				appFjxx.setId(s);
				appFjxx.setWjlj(wjlj);
				appFjxx.setFwlj(fw);
				appFjxx.setLrsj(new Date());
				appFjxx.setLrr(getUsername());
				appFjxxService.save(appFjxx);
				appJhshXjjl.setXjyqm(s);
			}

			if (org.apache.commons.lang3.StringUtils.isNotBlank(appJhshXjjl.getShqm())) {
				String fw = fwlj + "/" + System.currentTimeMillis() + ".png";
				String wjlj = uploadpath + fw;
				Base64Util.toImage(appJhshXjjl.getShqm(), wjlj);
				AppFjxx appFjxx = new AppFjxx();
				String s = IdUtil.fastSimpleUUID();
				appFjxx.setId(s);
				appFjxx.setWjlj(wjlj);
				appFjxx.setFwlj(fw);
				appFjxx.setLrsj(new Date());
				appFjxx.setLrr(getUsername());
				appFjxxService.save(appFjxx);
				appJhshXjjl.setShqm(s);
			}
		}
		log.info("===商户巡检保存的数据{}===",appJhshXjjl.toString());
		appJhshXjjlService.save(appJhshXjjl);

//		TgacsTpsMchntInfo tgacsTpsMchntInfo=new TgacsTpsMchntInfo();
//		QueryWrapper<TgacsTpsMchntInfo> queryWrapper = new QueryWrapper<>();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//		tgacsTpsMchntInfo.setSDate(sdf.format(appJhshXjjl.getXjsj()));
//		queryWrapper.eq("mchnt_id",appJhshXjjl.getMchntId());
//		tgacsTpsMchntInfoService.update(tgacsTpsMchntInfo,queryWrapper);

		return Result.ok("添加成功！");
	}

	
	/**
	 * 编辑
	 *
	 * @param appJhshXjjl
	 * @return
	 */
	@AutoLog(value = "聚合商户巡检记录-编辑")
	@ApiOperation(value="聚合商户巡检记录-编辑", notes="聚合商户巡检记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppJhshXjjl appJhshXjjl) {
		appJhshXjjlService.updateById(appJhshXjjl);
		return Result.ok("编辑成功!");
	}
	

}
