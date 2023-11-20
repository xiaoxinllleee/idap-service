package org.cmms.modules.common.fkxx.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.common.appfjxx.entity.AppFjxx;
import org.cmms.modules.common.appfjxx.service.IAppFjxxService;
import org.cmms.modules.common.fkxx.entity.AppFkxx;
import org.cmms.modules.common.fkxx.service.IAppFkxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 反馈信息
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="反馈信息")
@RestController
@RequestMapping("/fkxx/appFkxx")
public class AppFkxxController extends JeecgController<AppFkxx, IAppFkxxService> {
	@Autowired
	private IAppFkxxService appFkxxService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;

	 @Autowired
	 private IAppFjxxService appFixxService;

	 @Autowired
	 RedisUtil redisUtil;
	/**
	 * 分页列表查询
	 *
	 * @param appFkxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "反馈信息-分页列表查询")
	@ApiOperation(value="反馈信息-分页列表查询", notes="反馈信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppFkxx appFkxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppFkxx> queryWrapper = QueryGenerator.initQueryWrapper(appFkxx, req.getParameterMap());
		Page<AppFkxx> page = new Page<AppFkxx>(pageNo, pageSize);
		IPage<AppFkxx> pageList = appFkxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @return
	 */
	@AutoLog(value = "反馈信息-添加")
	@ApiOperation(value="反馈信息-添加", notes="反馈信息-添加")
	@RequestMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		Object o = redisUtil.get("appFkxxAdd" + getWorkNo());
		if (o != null){
			Integer integer = Integer.valueOf(o.toString());
			if (integer >= 3)
			{
				return Result.error("24小时最多反馈信息3次！");
			}else {
				integer++;
				redisUtil.set("appFkxxAdd" + getWorkNo(),integer,1*60*60*24);
			}
		}else {
			redisUtil.set("appFkxxAdd" + getWorkNo(),1,1*60*60*24);
		}

		String serverPort = ":"+request.getServerPort()+"/";
		String uuid = UUID.randomUUID().toString().replace("-","");
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        AppFkxx appFkxx=new AppFkxx();
		JSONArray array = jsonObject.getJSONArray("filePath");
		appFkxx.setFklx(Integer.valueOf(jsonObject.getString("fklx")));
        appFkxx.setFkxx(jsonObject.getString("fkxx"));
        appFkxx.setFkr(sysUser.getUsername());
        appFkxx.setFkrrealname(sysUser.getRealname());
        appFkxx.setId(uuid);
        appFkxx.setLrr(sysUser.getUsername());
        appFkxx.setLrsj(new Date());
        appFkxx.setLrbz(1);
		appFkxxService.save(appFkxx);
		AppFjxx appFjxx=null;
        if (array.size()>0){
			String path =uploadpath+"/app/"+"save/"+sysUser.getRealname()+"/";
			File file = new File(path);
			if (!file.exists()){
				file.mkdirs();
			}
			InputStream in=null;
			OutputStream out=null;
			for (int i=0;i<array.size();i++){
                String paths = array.get(i).toString();
                String suffix = paths.substring(paths.lastIndexOf(".") + 1, paths.length());
                if (!(suffix.equals("png") || suffix.equals("jpg"))){
                    return Result.error("图片格式不正确,只支持 png 或 jgp 格式");
                }
				String name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
				String savePath=path+name;
				String fwlj="/app/"+"save/"+sysUser.getRealname()+"/"+name;
				try {
					System.out.println(paths.substring(paths.lastIndexOf(serverPort)+5,paths.length()));
					path=uploadpath+paths.substring(paths.lastIndexOf(serverPort)+5,paths.length());
					System.out.println(path);
					File f=new File(path);
					in=new FileInputStream(f);
					out =new FileOutputStream(new File(savePath));
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = in.read(b)) != -1) {
						out.write(b);
					}
					out.close();
					in.close();
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

				appFjxx = new AppFjxx();
				appFjxx.setFjlx(appFkxx.getFklx());
				appFjxx.setId(uuid);
				appFjxx.setFjlx(1);
				appFjxx.setWjlj(savePath);
				appFjxx.setFwlj(fwlj);
				appFjxx.setLrbz(appFkxx.getLrbz());
				appFjxx.setLrsj(appFkxx.getLrsj());
				appFjxx.setLrr(appFkxx.getLrr());
				appFixxService.save(appFjxx);
			}
		}

        System.out.println(appFkxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appFkxx
	 * @return
	 */
	@AutoLog(value = "反馈信息-编辑")
	@ApiOperation(value="反馈信息-编辑", notes="反馈信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppFkxx appFkxx) {
		appFkxxService.updateById(appFkxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "反馈信息-通过id删除")
	@ApiOperation(value="反馈信息-通过id删除", notes="反馈信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appFkxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "反馈信息-批量删除")
	@ApiOperation(value="反馈信息-批量删除", notes="反馈信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appFkxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "反馈信息-通过id查询")
	@ApiOperation(value="反馈信息-通过id查询", notes="反馈信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppFkxx appFkxx = appFkxxService.getById(id);
		return Result.ok(appFkxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appFkxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppFkxx appFkxx) {
      return super.exportXls(request, appFkxx, AppFkxx.class, "反馈信息");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, AppFkxx.class);
  }

}
