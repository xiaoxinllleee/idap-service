package org.cmms.modules.khgl.jgsydw.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.jgsydw.entity.*;
import org.cmms.modules.khgl.jgsydw.service.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 机关事业单位
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机关事业单位")
@RestController
@RequestMapping("/khgl.jgsydw/jgsydwJbxx")
public class JgsydwJbxxController extends JeecgController<JgsydwJbxx, IJgsydwJbxxService> {
	@Autowired
	private IJgsydwJbxxService iJgsydwJbxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Autowired
    private IJgsydwYwhxgywService iJgsydwYwhxgywService;
	@Autowired
    private IJgsydwZcxxService iJgsydwZcxxService;
	@Autowired
    private IJgsydwFjxxService iJgsydwFjxxService;
	@Autowired
    private IJgsydwStaffService iJgsydwStaffService;
	
	/**
	 * 分页列表查询
	 * @param jgsydwJbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机关事业单位-分页列表查询")
	@ApiOperation(value="机关事业单位-分页列表查询", notes="机关事业单位-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(JgsydwJbxx jgsydwJbxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<JgsydwJbxx> queryWrapper = QueryGenerator.initQueryWrapper(jgsydwJbxx, req.getParameterMap());
		Page<JgsydwJbxx> page = new Page<JgsydwJbxx>(pageNo, pageSize);
		IPage<JgsydwJbxx> pageList = iJgsydwJbxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param jgsydwJbxx
	 * @return
	 */
	@AutoLog(value = "机关事业单位-添加")
	@ApiOperation(value="机关事业单位-添加", notes="机关事业单位-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JgsydwJbxx jgsydwJbxx) {
		iJgsydwJbxxService.save(jgsydwJbxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param jgsydwJbxx
	 * @return
	 */
	@AutoLog(value = "机关事业单位-编辑")
	@ApiOperation(value="机关事业单位-编辑", notes="机关事业单位-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody JgsydwJbxx jgsydwJbxx) {
		iJgsydwJbxxService.updateById(jgsydwJbxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关事业单位-通过id删除")
	@ApiOperation(value="机关事业单位-通过id删除", notes="机关事业单位-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iJgsydwJbxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机关事业单位-批量删除")
	@ApiOperation(value="机关事业单位-批量删除", notes="机关事业单位-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iJgsydwJbxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关事业单位-通过id查询")
	@ApiOperation(value="机关事业单位-通过id查询", notes="机关事业单位-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		JgsydwJbxx jgsydwJbxx = iJgsydwJbxxService.getById(id);
		return Result.ok(jgsydwJbxx);
	}

    /**
     * 导出excel
     * @param request
     * @param jgsydwJbxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JgsydwJbxx jgsydwJbxx) {
      return super.exportXls(request, jgsydwJbxx, JgsydwJbxx.class, "机关事业单位");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, JgsydwJbxx.class);
    }

     /** 通过档案编号查询基本信息数据 */
     @ApiOperation(value = "通过档案编号查询基本信息数据", notes="通过档案编号查询基本信息数据")
     @RequestMapping(value = "/jbxx", method = RequestMethod.PUT)
     public Result<?> queryJbxxByDabh(@RequestBody JSONObject dabh) {
         Result<JgsydwJbxx> result = new Result<>();
         JgsydwJbxx jbxx = new JgsydwJbxx();
         jbxx.setDabh(dabh.getString("dabh"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<JgsydwJbxx> queryWrapper = QueryGenerator.initQueryWrapper(jbxx, map);
         List<JgsydwJbxx> jbxxList = iJgsydwJbxxService.list(queryWrapper);
         if (jbxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             result.setResult(jbxxList.get(0));
             result.setSuccess(true);
         }
         return result;
     }

     /** 通过档案编号查询机关事业单位资产信息 */
     @ApiOperation(value = "通过档案编号查询机关事业单位资产信息", notes="通过档案编号查询机关事业单位资产信息")
     @RequestMapping(value = "/dwzcxx", method = RequestMethod.PUT)
     public Result<JSONArray> queryDwzcxxByDabh(@RequestBody JSONObject dabh) {
         Result<JSONArray> result = new Result<>();
         JgsydwZcxx jgsydwZcxx = new JgsydwZcxx();
         jgsydwZcxx.setDabh(dabh.getString("dabh"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<JgsydwZcxx> queryWrapper = QueryGenerator.initQueryWrapper(jgsydwZcxx, map);
         List<JgsydwZcxx> zcxxList = iJgsydwZcxxService.list(queryWrapper);
         if (zcxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             for (JgsydwZcxx zcxx : zcxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("khmc", zcxx.getKhmc());
                 jsonObject.put("fcbh", zcxx.getFcbh());
                 jsonObject.put("fcxz", zcxx.getFcxz() == null ? "" : iSysDictService.queryDictTextByKey("khgl_fcxz", zcxx.getFcxz()));
                 jsonObject.put("fcwz", zcxx.getFcwz());
                 // jsonObject.put("fcsl", zcxx.getFcsl());
                 jsonObject.put("fczmj", zcxx.getFczmj());
                 jsonObject.put("fczjz", zcxx.getFczjz());
                 jsonObject.put("clsl", zcxx.getClsl());
                 jsonObject.put("clpp", zcxx.getClpp());
                 jsonObject.put("clhm", zcxx.getCphm());
                 jsonObject.put("clzjz", zcxx.getClzjz());
                 jsonObject.put("bz", zcxx.getBz());
                 jsonArray.add(jsonObject);
             }
             result.setResult(jsonArray);
             result.setSuccess(true);
         }
         return result;
     }

     /** 通过负责人证件号码查询金融业务信息 */
     @ApiOperation(value = "通过负责人证件号码查询金融业务信息", notes="通过负责人证件号码查询金融业务信息")
     @RequestMapping(value = "/jrywxx", method = RequestMethod.PUT)
     public Result<?> queryJrywxxByFzrzjhm(@RequestBody JSONObject fzrzjhm) {
         Result<JgsydwYwhxgyw> result = new Result<>();
         JgsydwYwhxgyw ywhxgyw = new JgsydwYwhxgyw();
         ywhxgyw.setZjhm(fzrzjhm.getString("fzrzjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<JgsydwYwhxgyw> queryWrapper = QueryGenerator.initQueryWrapper(ywhxgyw, map);
         List<JgsydwYwhxgyw> ywhxgywList = iJgsydwYwhxgywService.list(queryWrapper);
         if (ywhxgywList == null) {
             result.error500("未找到对应实体！");
         } else {
             result.setResult(ywhxgywList.get(0));
             result.setSuccess(true);
         }
         return result;
     }

     /** 通过负责人证件号码查询附件信息 */
     @ApiOperation(value = "通过负责人证件号码查询附件信息", notes="通过负责人证件号码查询附件信息")
     @RequestMapping(value = "/dwfjxx", method = RequestMethod.PUT)
     public Result<JSONArray> queryFjxxByFzrzjhm(@RequestBody JSONObject fzrzjhm) {
         Result<JSONArray> result = new Result<>();
         JgsydwFjxx jgsydwFjxx = new JgsydwFjxx();
         jgsydwFjxx.setZjhm(fzrzjhm.getString("fzrzjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<JgsydwFjxx> queryWrapper = QueryGenerator.initQueryWrapper(jgsydwFjxx, map);
         List<JgsydwFjxx> fjxxList = iJgsydwFjxxService.list(queryWrapper);
         if (fjxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             for (JgsydwFjxx fjxx : fjxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("zjbh", fjxx.getZlbh());
                 jsonObject.put("zlmc", fjxx.getZlmc());
                 jsonObject.put("fwlj", fjxx.getFwlj());
                 jsonObject.put("bz", fjxx.getBz());
                 // 上传人
                 HrBasStaff hrBasStaff = new HrBasStaff();
                 hrBasStaff.setYggh(fjxx.getScr());
                 Map<String, String[]> hashMap = new HashMap<>();
                 QueryWrapper<HrBasStaff> staffQueryWrapper = QueryGenerator.initQueryWrapper(hrBasStaff, hashMap);
                 List<HrBasStaff> staffList = iJgsydwStaffService.list(staffQueryWrapper);
                 if (staffList.size() != 0) {
                     jsonObject.put("scr", staffList.get(0).getYgxm());
                 } else {
                     jsonObject.put("scr", fjxx.getScr());
                 }
                 jsonObject.put("scsj", fjxx.getScsj());
                 jsonArray.add(jsonObject);
             }
             result.setResult(jsonArray);
             result.setSuccess(true);
         }
         return result;
     }

     /** 附件下载路径查询 */
     @ApiOperation(value = "附件下载路径查询", notes="附件下载路径查询")
     @RequestMapping(value = "/filePath", method = RequestMethod.PUT)
     public JSON queryDownFilePath(@RequestBody JSONObject zlbh) {
         JgsydwFjxx fjxx = new JgsydwFjxx();
         fjxx.setZlbh(zlbh.getString("zlbh"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<JgsydwFjxx> queryWrapper = QueryGenerator.initQueryWrapper(fjxx, map);
         List<JgsydwFjxx> fjxxList = iJgsydwFjxxService.list(queryWrapper);
         JSONObject jsonObject = new JSONObject();
         if (fjxxList.size() != 0) {
             jsonObject.put("zllj", fjxxList.get(0).getZllj());
         }
         return jsonObject;
     }

}
