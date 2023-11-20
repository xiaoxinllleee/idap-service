package org.cmms.modules.khgl.qy.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.qy.entity.*;
import org.cmms.modules.khgl.qy.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 企业客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-02-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="企业客户信息管理")
@RestController
@RequestMapping("/khgl/qy")
public class KhglQyjbxxController extends JeecgController<Qyjbxx, IKhglQyjbxxService> {
	@Autowired
	private IKhglQyjbxxService iKhglQyjbxxService;
	@Autowired
    private IYwhxgywxxService iYwhxgywxxService;
	@Autowired
    private IQyzcxxService iQyzcxxService;
	@Autowired
    private IQyFjxxService iQyFjxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Autowired
    private IStaffInfoService iStaffInfoService;
	
	/**
	 * 分页列表查询
	 * @param qyjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-分页列表查询")
	@ApiOperation(value="企业客户信息管理-分页列表查询", notes="企业客户信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qyjbxx qyjbxx,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<Qyjbxx> queryWrapper = QueryGenerator.initQueryWrapper(qyjbxx, req.getParameterMap());
		Page<Qyjbxx> page = new Page<Qyjbxx>(pageNo, pageSize);
		IPage<Qyjbxx> pageList = iKhglQyjbxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param qyjbxx
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-添加")
	@ApiOperation(value="企业客户信息管理-添加", notes="企业客户信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qyjbxx qyjbxx) {
		iKhglQyjbxxService.save(qyjbxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param qyjbxx
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-编辑")
	@ApiOperation(value="企业客户信息管理-编辑", notes="企业客户信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qyjbxx qyjbxx) {
		iKhglQyjbxxService.updateById(qyjbxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-通过id删除")
	@ApiOperation(value="企业客户信息管理-通过id删除", notes="企业客户信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iKhglQyjbxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-批量删除")
	@ApiOperation(value="企业客户信息管理-批量删除", notes="企业客户信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iKhglQyjbxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业客户信息管理-通过id查询")
	@ApiOperation(value="企业客户信息管理-通过id查询", notes="企业客户信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qyjbxx qyjbxx = iKhglQyjbxxService.getById(id);
		return Result.ok(qyjbxx);
	}

    /**
     * 导出excel
     * @param request
     * @param qyjbxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qyjbxx qyjbxx) {
      return super.exportXls(request, qyjbxx, Qyjbxx.class, "企业客户信息管理");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Qyjbxx.class);
    }

    /** 通过档案编号查询基本信息数据 */
    @ApiOperation(value = "通过档案编号查询基本信息数据", notes="通过档案编号查询基本信息数据")
    @RequestMapping(value = "/jbxx", method = RequestMethod.PUT)
    public Result<?> queryJbxxByDabh(@RequestBody JSONObject dabh) {
        Result<Qyjbxx> result = new Result<>();
        Qyjbxx qyjbxx = new Qyjbxx();
        qyjbxx.setDabh(dabh.getString("dabh"));
        Map<String, String[]> map = new HashMap<>();
        QueryWrapper<Qyjbxx> queryWrapper = QueryGenerator.initQueryWrapper(qyjbxx, map);
        List<Qyjbxx> qyjbxxList = iKhglQyjbxxService.list(queryWrapper);
        if (qyjbxxList == null) {
            result.error500("未找到对应实体！");
        } else {
            result.setResult(qyjbxxList.get(0));
            result.setSuccess(true);
        }
        return result;
    }

     /** 通过法人证件号码查询金融业务信息 */
     @ApiOperation(value = "通过法人证件号码查询金融业务信息", notes="通过法人证件号码查询金融业务信息")
     @RequestMapping(value = "/jrywxx", method = RequestMethod.PUT)
     public Result<?> queryJrywxxByFrzjhm(@RequestBody JSONObject frzjhm) {
         Result<Ywhxgyw> result = new Result<>();
         Ywhxgyw ywhxgyw = new Ywhxgyw();
         ywhxgyw.setZjhm(frzjhm.getString("frzjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<Ywhxgyw> queryWrapper = QueryGenerator.initQueryWrapper(ywhxgyw, map);
         List<Ywhxgyw> ywhxgywList = iYwhxgywxxService.list(queryWrapper);
         if (ywhxgywList == null) {
             result.error500("未找到对应实体！");
         } else {
             result.setResult(ywhxgywList.get(0));
             result.setSuccess(true);
         }
         return result;
     }

     /** 通过法人证件号码查询企业资产信息 */
     @ApiOperation(value = "通过法人证件号码查询企业资产信息", notes="通过法人证件号码查询企业资产信息")
     @RequestMapping(value = "/qyzcxx", method = RequestMethod.PUT)
     public Result<JSONArray> queryQyzcxxByFrzjhm(@RequestBody JSONObject frzjhm) {
         Result<JSONArray> result = new Result<>();
         Qyzcxx qyzcxx = new Qyzcxx();
         qyzcxx.setZjhm(frzjhm.getString("frzjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<Qyzcxx> queryWrapper = QueryGenerator.initQueryWrapper(qyzcxx, map);
         List<Qyzcxx> qyzcxxList = iQyzcxxService.list(queryWrapper);
         if (qyzcxx == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             for (Qyzcxx qyzc : qyzcxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("khmc", qyzc.getKhmc());
                 jsonObject.put("fcxz", qyzc.getFcxz()==null ? "" : iSysDictService.queryDictTextByKey("khgl_fcxz", qyzc.getFcxz()));
                 jsonObject.put("fcwz", qyzc.getFcwz());
                 jsonObject.put("fcmj", qyzc.getFcmj());
                 jsonObject.put("fcjz", qyzc.getFcjz());
                 jsonObject.put("brand", qyzc.getBrand());
                 jsonObject.put("car", qyzc.getCar());
                 jsonObject.put("cph", qyzc.getCph());
                 jsonObject.put("clzjz", qyzc.getClzjz());
                 jsonArray.add(jsonObject);
             }
             result.setResult(jsonArray);
             result.setSuccess(true);
         }
         return result;
     }

     /** 通过法人证件号码查询附件信息 */
     @ApiOperation(value = "通过法人证件号码查询附件信息", notes="通过法人证件号码查询附件信息")
     @RequestMapping(value = "/qyfjxx", method = RequestMethod.PUT)
     public Result<JSONArray> queryFjxxByFrzjhm(@RequestBody JSONObject frzjhm) {
         Result<JSONArray> result = new Result<>();
         List<String> list = new ArrayList<String>();
         QyFjxx qyFjxx = new QyFjxx();
         qyFjxx.setZjhm(frzjhm.getString("frzjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<QyFjxx> queryWrapper = QueryGenerator.initQueryWrapper(qyFjxx, map);
         List<QyFjxx> qyFjxxList = iQyFjxxService.list(queryWrapper);
         if (qyFjxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             for (QyFjxx qyfj : qyFjxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("zlbh", qyfj.getZlbh());
                 jsonObject.put("zlmc", qyfj.getZlmc());
                 jsonObject.put("bz", qyfj.getBz());
                 jsonObject.put("fwlj", qyfj.getFwlj()); // 访问路径
                 HrBasStaff hrBasStaff = new HrBasStaff();
                 hrBasStaff.setYggh(qyfj.getScr());
                 Map<String, String[]> hashMap = new HashMap<>();
                 QueryWrapper<HrBasStaff> staffQueryWrapper = QueryGenerator.initQueryWrapper(hrBasStaff, hashMap);
                 List<HrBasStaff> hrBasStaffList = iStaffInfoService.list(staffQueryWrapper);
                 if (hrBasStaffList.size() != 0) {
                     jsonObject.put("scr", hrBasStaffList.get(0).getYgxm());
                 } else {
                     jsonObject.put("scr", qyfj.getScr());
                 }
                 jsonObject.put("scsj", qyfj.getScsj());
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
     public JSON queryDownFilePath(@RequestBody JSONObject param) {
         QyFjxx qyFjxx = new QyFjxx();
         qyFjxx.setZlbh(param.getString("zlbh"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<QyFjxx> queryWrapper = QueryGenerator.initQueryWrapper(qyFjxx, map);
         List<QyFjxx> qyFjxxList = iQyFjxxService.list(queryWrapper);
         JSONObject jsonObject = new JSONObject();
         if (qyFjxxList.size() != 0) {
             jsonObject.put("zllj", qyFjxxList.get(0).getZllj());
         }
         return jsonObject;
     }
 }
