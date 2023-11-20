package org.cmms.modules.khgl.cqjm.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.collections.BagUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.cqjm.entity.*;
import org.cmms.modules.khgl.cqjm.service.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.cqjm.vo.CqjmJbxxPage;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 城区居民功能包
 * @Author: jeecg-boot
 * @Date:   2020-02-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="城区居民功能包")
@RestController
@RequestMapping("/khgl.cqjm/cqjmJbxx")
public class CqjmJbxxController extends JeecgController<CqjmJbxx, ICqjmJbxxService> {
	@Autowired
	private ICqjmJbxxService iCqjmJbxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Autowired
    private ICqjmYwhywwlxxService iCqjmYwhywwlxxService;
	@Autowired
    private ICqjmZcxxService iCqjmZcxxService;
	@Autowired
    private ICqjmFjxxService iCqjmFjxxService;
	@Autowired
    private ICqjmHrStaffService iCqjmHrStaffService;
	@Autowired
    private ICqjmZcfzqkService iCqjmZcfzqkService;
	
	/**
	 * 分页列表查询
	 * @param cqjmJbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-分页列表查询")
	@ApiOperation(value="城区居民功能包-分页列表查询", notes="城区居民功能包-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CqjmJbxx cqjmJbxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CqjmJbxx> queryWrapper = QueryGenerator.initQueryWrapper(cqjmJbxx, req.getParameterMap());
		Page<CqjmJbxx> page = new Page<CqjmJbxx>(pageNo, pageSize);
		IPage<CqjmJbxx> pageList = iCqjmJbxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param cqjmJbxxPage
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-添加")
	@ApiOperation(value="城区居民功能包-添加", notes="城区居民功能包-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CqjmJbxxPage cqjmJbxxPage) {
	    CqjmJbxx cqjmJbxx = new CqjmJbxx();
        BeanUtils.copyProperties(cqjmJbxxPage, cqjmJbxx);
        iCqjmJbxxService.saveMain(
           cqjmJbxx, cqjmJbxxPage.getCqjmZcxxList(), cqjmJbxxPage.getCqjmYwhywwlxxList(), cqjmJbxxPage.getCqjmZcfzqkList(), cqjmJbxxPage.getCqjmFjxxList()
        );
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param cqjmJbxxPage
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-编辑")
	@ApiOperation(value="城区居民功能包-编辑", notes="城区居民功能包-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CqjmJbxxPage cqjmJbxxPage) {
		CqjmJbxx cqjmJbxx = new CqjmJbxx();
		BeanUtils.copyProperties(cqjmJbxxPage, cqjmJbxx);
		iCqjmJbxxService.updateMain(
		   cqjmJbxx, cqjmJbxxPage.getCqjmZcxxList(), cqjmJbxxPage.getCqjmYwhywwlxxList(), cqjmJbxxPage.getCqjmZcfzqkList(), cqjmJbxxPage.getCqjmFjxxList()
        );
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过zjhm删除
	 * @param zjhm
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-通过zjhm删除")
	@ApiOperation(value="城区居民功能包-通过zjhm删除", notes="城区居民功能包-通过zjhm删除")
	@DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "zjhm",required = true) String zjhm) {
        try {
            iCqjmJbxxService.deleteMain(zjhm);
        } catch (Exception e) {
            log.error("删除失败！",e.getMessage());
            return Result.error("删除失败！");
        }
        return Result.ok("删除成功!");
    }
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-批量删除")
	@ApiOperation(value="城区居民功能包-批量删除", notes="城区居民功能包-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<CqjmJbxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
	    Result<CqjmJbxx> result = new Result<>();
	    if (ids==null || "".equals(ids.trim())) {
	        result.error500("参数未识别！");
        } else {
            this.iCqjmJbxxService.removeByIds(Arrays.asList(ids.split(",")));
            result.setSuccess(true);
            result.setMessage("批量删除成功!");
        }
	    return result;
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "城区居民功能包-通过id查询")
	@ApiOperation(value="城区居民功能包-通过id查询", notes="城区居民功能包-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CqjmJbxx cqjmJbxx = iCqjmJbxxService.getById(id);
		return Result.ok(cqjmJbxx);
	}

    /**
     * 导出excel
     * @param request
     * @param cqjmJbxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CqjmJbxx cqjmJbxx) {
      return super.exportXls(request, cqjmJbxx, CqjmJbxx.class, "城区居民功能包");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, CqjmJbxx.class);
    }

     /**
      * 通过所属支行与户号编码查询家庭成员信息
      * @param object
      * @return Jtcyqk
      */
    @ApiOperation(value = "通过所属支行与户号编码查询家庭成员信息", notes = "通过所属支行与户号编码查询家庭成员信息")
    @RequestMapping(value = "/queryJtcyqk", method = RequestMethod.PUT)
    public Result<JSONArray> queryJtcyqk(@RequestBody JSONObject object) {
        Result<JSONArray> result = new Result<>();
        CqjmJbxx cqjm = new CqjmJbxx();
        cqjm.setHhbm(object.getString("hhbm"));
        cqjm.setSszh(object.getString("sszh"));
        Map<String, String[]> map = new HashMap<>();
        QueryWrapper<CqjmJbxx> queryWrapper = QueryGenerator.initQueryWrapper(cqjm, map);
        List<CqjmJbxx> cqjmJbxxList = iCqjmJbxxService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (CqjmJbxx cqjmJbxx : cqjmJbxxList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("yhzgx", iSysDictService.queryDictTextByKey("yhzgx", cqjmJbxx.getYhzgx()));
            jsonObject.put("khmc", cqjmJbxx.getKhmc());
            jsonObject.put("zjhm", cqjmJbxx.getZjhm());
            jsonObject.put("sex", iSysDictService.queryDictTextByKey("sex", cqjmJbxx.getSex()));
            jsonObject.put("lxfs", cqjmJbxx.getLxfs());
            jsonObject.put("address", cqjmJbxx.getAddress());
            jsonArray.add(jsonObject);
        }
        result.setResult(jsonArray);
        result.setSuccess(true);
        return result;
    }

     /**
      * 通过证件号码查询与我行相关业务
      * @param object
      * @return CqjmYwhywwlxx
      */
    @ApiOperation(value = "通过证件号码查询与我行相关业务", notes="通过证件号码查询与我行相关业务")
    @RequestMapping(value = "/queryYwhxgyw", method = RequestMethod.PUT)
    public Result<?> queryYwhxgyw(@RequestBody JSONObject object) {
        Result<CqjmYwhywwlxx> result = new Result<>();
        CqjmYwhywwlxx ywhywwlxx = new CqjmYwhywwlxx();
        ywhywwlxx.setZjhm(object.getString("zjhm"));
        Map<String, String[]> map = new HashMap<>();
        QueryWrapper<CqjmYwhywwlxx> queryWrapper = QueryGenerator.initQueryWrapper(ywhywwlxx, map);
        List<CqjmYwhywwlxx> cqjmYwhxgywList = iCqjmYwhywwlxxService.list(queryWrapper);
        result.setResult(cqjmYwhxgywList.get(0));
        result.setSuccess(true);
        return result;
    }

     /**
      * 通过证件号码查询房产信息
      * @param object
      * @return Fcxx
      */
    @ApiOperation(value = "通过证件号码查询房产信息", notes="通过证件号码查询房产信息")
    @RequestMapping(value = "/queryFcxx", method = RequestMethod.PUT)
    public Result<JSONArray> queryFcxx(@RequestBody JSONObject object) {
        Result<JSONArray> result = new Result<>();
        CqjmZcxx cqjmFcxx = new CqjmZcxx();
        cqjmFcxx.setZjhm(object.getString("zjhm"));
        Map<String, String[]> map = new HashMap<>();
        QueryWrapper<CqjmZcxx> queryWrapper = QueryGenerator.initQueryWrapper(cqjmFcxx, map);
        List<CqjmZcxx> cqjmFcxxList = iCqjmZcxxService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (CqjmZcxx fcxx : cqjmFcxxList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fcbm", fcxx.getFcbm());
            jsonObject.put("fcxz", fcxx.getFcxz()==null?"":iSysDictService.queryDictTextByKey("khgl_fcxz",fcxx.getFcxz()));
            jsonObject.put("fcsl", fcxx.getFcsl());
            jsonObject.put("fcwz", fcxx.getFcwz());
            jsonObject.put("fcmj", fcxx.getFcmj());
            jsonObject.put("fcjz", fcxx.getFcjz());
            jsonObject.put("bz", fcxx.getBz());
            jsonArray.add(jsonObject);
        }
        result.setResult(jsonArray);
        result.setSuccess(true);
        return result;
    }

     /**
      * 通过证件号码查询资产情况
      * @param object
      * @return Zcqk
      */
    @ApiOperation(value = "通过证件号码查询资产情况", notes="通过证件号码查询资产情况")
    @RequestMapping(value = "/queryZcqk", method = RequestMethod.PUT)
    public Result<JSONArray> queryZcqk(@RequestBody JSONObject object) {
        Result<JSONArray> result = new Result<>();
        List<CqjmZcfzqk> cqjmZcfzqkList = iCqjmZcfzqkService.selectByZjhm(object.getString("zjhm"));
        JSONArray jsonArray = new JSONArray();
        for (CqjmZcfzqk zcfzqk : cqjmZcfzqkList) {
            JSONObject object1 = new JSONObject();
            object1.put("zclx", "股权");
            object1.put("zcsl", zcfzqk.getGqsl());
            object1.put("zcjz", zcfzqk.getGqjz());
            object1.put("zcxqsm", zcfzqk.getGqxqsm());
            JSONObject object2 = new JSONObject();
            object2.put("zclx", "农机具");
            object2.put("zcsl", zcfzqk.getNjjsl());
            object2.put("zcjz", zcfzqk.getNjjjz());
            object2.put("zcxqsm", zcfzqk.getNjjqxsm());
            JSONObject object3 = new JSONObject();
            object3.put("zclx", "家用电器");
            object3.put("zcsl", zcfzqk.getJydqsl());
            object3.put("zcjz", zcfzqk.getJydqjz());
            object3.put("zcxqsm", zcfzqk.getJydqxqsm());
            JSONObject object4 = new JSONObject();
            object4.put("zclx", "交通工具");
            object4.put("zcsl", zcfzqk.getJtgjsl());
            object4.put("zcjz", zcfzqk.getJtgjjz());
            object4.put("zcxqsm", zcfzqk.getJtgjxqsm());
            JSONObject object5 = new JSONObject();
            object5.put("zclx", "其它资产");
            object5.put("zcsl", zcfzqk.getQtzcsl());
            object5.put("zcjz", zcfzqk.getQtzcjz());
            object5.put("zcxqsm", zcfzqk.getQtzcxqsm());
            jsonArray.add(object1);
            jsonArray.add(object2);
            jsonArray.add(object3);
            jsonArray.add(object4);
            jsonArray.add(object5);
        }
        result.setResult(jsonArray);
        result.setSuccess(true);
        return result;
    }

     /**
      * 通过证件号码查询负债情况
      * @param object
      * @return Fzqk
      */
    @ApiOperation(value="通过证件号码查询负债情况", notes="通过证件号码查询负债情况")
    @RequestMapping(value = "/queryFzqk", method = RequestMethod.PUT)
    public Result<JSONArray> queryFzqk(@RequestBody JSONObject object) {
        Result<JSONArray> result = new Result<>();
        List<CqjmZcfzqk> cqjmZcfzqkList = iCqjmZcfzqkService.selectByZjhm(object.getString("zjhm"));
        JSONArray jsonArray = new JSONArray();
        for (CqjmZcfzqk zcfzqk : cqjmZcfzqkList) {
            JSONObject object1 = new JSONObject();
            object1.put("jkfs", "本系统");
            object1.put("zqr", zcfzqk.getBxtjkzqr());
            object1.put("fzje", zcfzqk.getBxtjksl());
            object1.put("fzsm", zcfzqk.getBxtjkxqsm());
            JSONObject object2 = new JSONObject();
            object2.put("jkfs", "他行");
            object2.put("zqr", zcfzqk.getThjkzqr());
            object2.put("fzje", zcfzqk.getThjksl());
            object2.put("fzsm", zcfzqk.getThjkxqsm());
            JSONObject object3 = new JSONObject();
            object3.put("jkfs", "信用卡");
            object3.put("zqr", zcfzqk.getXykzqr());
            object3.put("fzje", zcfzqk.getXyksl());
            object3.put("fzsm", zcfzqk.getXykxqsm());
            JSONObject object4 = new JSONObject();
            object4.put("jkfs", "其它");
            object4.put("zqr", zcfzqk.getQtfzzqr());
            object4.put("fzje", zcfzqk.getQtfzsl());
            object4.put("fzsm", zcfzqk.getQtfzxqsm());
            jsonArray.add(object1);
            jsonArray.add(object2);
            jsonArray.add(object3);
            jsonArray.add(object4);
        }
        result.setResult(jsonArray);
        result.setSuccess(true);
        return result;
    }

     /**
      * 通过证件号码查询经营情况
      * @param object
      * @return Jyqk
      */
     @ApiOperation(value="通过证件号码查询经营情况", notes="通过证件号码查询经营情况")
     @RequestMapping(value = "/queryJyqk", method = RequestMethod.PUT)
     public Result<JSONArray> queryJyqk(@RequestBody JSONObject object) {
        Result<JSONArray> result = new Result<>();
         List<CqjmZcfzqk> cqjmZcfzqkList = iCqjmZcfzqkService.selectByZjhm(object.getString("zjhm"));
         JSONArray jsonArray = new JSONArray();
         for (CqjmZcfzqk zcfzqk : cqjmZcfzqkList) {
             JSONObject object1 = new JSONObject();
             object1.put("xmlx", "种植业");
             object1.put("xmqk", zcfzqk.getZzxmqk());
             object1.put("xmnzc", zcfzqk.getZzxmzc());
             object1.put("xmnsr", zcfzqk.getZzxmsr());
             object1.put("xmjlr", zcfzqk.getZzxmjsr());
             JSONObject object2 = new JSONObject();
             object2.put("xmlx", "养殖业");
             object2.put("xmqk", zcfzqk.getYzxmqk());
             object2.put("xmnzc", zcfzqk.getYzxmzc());
             object2.put("xmnsr", zcfzqk.getYzxmsr());
             object2.put("xmjlr", zcfzqk.getYzxmjsr());
             JSONObject object3 = new JSONObject();
             object3.put("xmlx", "商业");
             object3.put("xmqk", zcfzqk.getSyxmqk());
             object3.put("xmnzc", zcfzqk.getSyxmzc());
             object3.put("xmnsr", zcfzqk.getSyxmsr());
             object3.put("xmjlr", zcfzqk.getSyxmjsr());
             JSONObject object4 = new JSONObject();
             object4.put("xmlx", "劳务");
             object4.put("xmqk", zcfzqk.getNwxmqk());
             object4.put("xmnzc", zcfzqk.getNwxmzc());
             object4.put("xmnsr", zcfzqk.getNwxmsr());
             object4.put("xmjlr", zcfzqk.getNwxmjsr());
             JSONObject object5 = new JSONObject();
             object5.put("xmlx", "其它");
             object5.put("xmqk", zcfzqk.getQtxmqk());
             object5.put("xmnzc", zcfzqk.getQtxmzc());
             object5.put("xmnsr", zcfzqk.getQtxmsr());
             object5.put("xmjlr", zcfzqk.getQtxmjsr());
             jsonArray.add(object1);
             jsonArray.add(object2);
             jsonArray.add(object3);
             jsonArray.add(object4);
             jsonArray.add(object5);
         }
         result.setResult(jsonArray);
         result.setSuccess(true);
         return result;
     }

     /**
      * 通过证件号码查询附件信息
      * @param object
      * @return Fjxx
      */
     @ApiOperation(value = "通过证件号码查询附件信息", notes="通过证件号码查询附件信息")
     @RequestMapping(value = "/queryFjxx", method = RequestMethod.PUT)
     public Result<JSONArray> queryFjxx(@RequestBody JSONObject object) {
         Result<JSONArray> result = new Result<>();
         CqjmFjxx cqjmFjxx = new CqjmFjxx();
         cqjmFjxx.setZjhm(object.getString("zjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<CqjmFjxx> queryWrapper = QueryGenerator.initQueryWrapper(cqjmFjxx, map);
         List<CqjmFjxx> cqjmFjxxList = iCqjmFjxxService.list(queryWrapper);
         JSONArray jsonArray = new JSONArray();
         for (CqjmFjxx fjxx : cqjmFjxxList) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("fjbh", fjxx.getZlbh());
             jsonObject.put("fjmc", fjxx.getZlmc());
             jsonObject.put("fwlj", fjxx.getFwlj());
             jsonObject.put("bz", fjxx.getBz());
             // 上传人名称
             HrBasStaff hrStaff = new HrBasStaff();
             hrStaff.setYggh(fjxx.getScr());
             Map<String, String[]> hashMap = new HashMap<>();
             QueryWrapper<HrBasStaff> staffQueryWrapper = QueryGenerator.initQueryWrapper(hrStaff, hashMap);
             List<HrBasStaff> cqjmHrStaffList = iCqjmHrStaffService.list(staffQueryWrapper);
             if (cqjmHrStaffList.size() != 0) {
                 jsonObject.put("scr", cqjmHrStaffList.get(0).getYgxm());
             } else {
                 jsonObject.put("scr", fjxx.getScr());
             }
             jsonObject.put("scsj", fjxx.getScsj());
             jsonArray.add(jsonObject);
         }
         result.setResult(jsonArray);
         result.setSuccess(true);
         return result;
     }

     /**
      * 附件下载路径查询
      * @param object
      * @return filePath
      */
     @ApiOperation(value = "附件下载路径查询", notes="附件下载路径查询")
     @RequestMapping(value = "/filePath", method = RequestMethod.PUT)
     public JSON queryDownFilePath(@RequestBody JSONObject object) {
         CqjmFjxx cqjmFjxx = new CqjmFjxx();
         cqjmFjxx.setZlbh(object.getString("fjbh"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<CqjmFjxx> queryWrapper = QueryGenerator.initQueryWrapper(cqjmFjxx, map);
         List<CqjmFjxx> cqjmFjxxList = iCqjmFjxxService.list(queryWrapper);
         JSONObject jsonObject = new JSONObject();
         if (cqjmFjxxList.size() != 0) {
             jsonObject.put("filePath", cqjmFjxxList.get(0).getZllj());
         }
         return jsonObject;
     }
 }
