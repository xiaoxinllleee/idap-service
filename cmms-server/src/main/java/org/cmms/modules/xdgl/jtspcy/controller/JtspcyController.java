package org.cmms.modules.xdgl.jtspcy.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.codec.binary.Base64;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQueryImpl;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.vKhglKrkhgl;
import org.cmms.modules.khgl.khglgx.entity.Khgl_khglgx;
import org.cmms.modules.system.entity.SysUserDepart;
import org.cmms.modules.system.service.ISysDictItemService;
import org.cmms.modules.system.vo.SysDepartUsersVO;
import org.cmms.modules.xdgl.grdkgl.vo.JtspxzcyVO;
import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import org.cmms.modules.xdgl.jtspcy.service.IJtspcyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Decoder;

/**
 * @Description: 集体审批成员
 * @Author: jeecg-boot
 * @Date: 2020-09-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "集体审批成员")
@RestController
@RequestMapping("/xdgl/jtspcy")
public class JtspcyController extends JeecgController<Jtspcy, IJtspcyService> {
    @Autowired
    private IJtspcyService jtspcyService;
    @Autowired
    private IVhrbasstaffpostService hrbasstaffpostService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    private IDictValueQueryImpl dictValueQuery;



    /**
     * 分页列表查询
     *
     * @param jtspcy
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "集体审批成员-分页列表查询")
    @ApiOperation(value = "集体审批成员-分页列表查询", notes = "集体审批成员-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Jtspcy jtspcy,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Jtspcy> queryWrapper = QueryGenerator.initQueryWrapper(jtspcy, req.getParameterMap());
        Page<Jtspcy> page = new Page<Jtspcy>(pageNo, pageSize);
        IPage<Jtspcy> pageList = jtspcyService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param jtspcy
     * @param req
     * @return
     */
    @AutoLog(value = "集体审批成员-分页列表查询")
    @ApiOperation(value = "集体审批成员-分页列表查询", notes = "集体审批成员-分页列表查询")
    @GetMapping(value = "/getSpcyList")
    public Result<?> getSpcyList(Jtspcy jtspcy, HttpServletRequest req) {
        QueryWrapper<Jtspcy> queryWrapper = QueryGenerator.initQueryWrapper(jtspcy, req.getParameterMap());
        List<Jtspcy> JtspcyList=jtspcyService.list(queryWrapper);
        for(int i=0;i<JtspcyList.size();i++){
            JtspcyList.get(i).setId("");
        }
        return Result.ok(JtspcyList);
    }



    @AutoLog(value = "集体审批成员-分页列表查询")
    @ApiOperation(value = "集体审批成员-分页列表查询", notes = "集体审批成员-分页列表查询")
    @GetMapping(value = "/getYgxxList")
    public Result<?> getYgxxList(String zzbz, HttpServletRequest req) {
        List<Vhrbasstaffpost> ygxx =hrbasstaffpostService.geYgxxByZzbz(zzbz);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<ygxx.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label",ygxx.get(i).getYgxm());
            jsonObject.put("value",ygxx.get(i).getYggh());
            jsonObject.put("gwbz",ygxx.get(i).getGwbz());
            jsonArray.add(jsonObject);
        }
        return Result.ok(jsonArray);
    }



    //根据ID查询责任人信息
    @GetMapping(value = "/queryByIdJtspcy")
    public Result<?> queryByIdJtspcy(@RequestParam("id") String id, @RequestParam("zrrid") String zrrid) {
        Jtspcy jtspcy = jtspcyService.queryById(id, zrrid);
        return Result.ok(jtspcy);
    }

    //根据ID保存签名 审批信息
    @GetMapping(value = "/queryDksp")
    public Result<?> queryDksp(@RequestParam(name = "id", required = true) String id) {
        System.out.println("id-------" + id);
        List<Jtspcy> jtspcy = null;
        JSONObject jsonObject = new JSONObject();
        try {
            QueryWrapper<Jtspcy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            jtspcy = jtspcyService.list(queryWrapper);
            for (Jtspcy jtspcy1 : jtspcy) {
                if (jtspcy1 != null) {
                    if (jtspcy1.getQmtp() != null && jtspcy1.getQmtp().length() > 0) {
                        jtspcy1.setQmtp(getImgBase64Str(uploadpath + File.separator + jtspcy1.getQmtp()));
                    } else {
                        jtspcy1.setQmtp("");
                    }
                }
            }
            jsonObject.put("jtspcy",jtspcy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(jsonObject);
    }

    //风险经理权限控制   角色编码
    @RequestMapping(value = "/queryRoleCode", method = RequestMethod.GET)
    public Result<?> queryRoleCode(@RequestParam("zrrid") String wordNo) {
        return Result.ok(dictValueQuery.getRoleValueByWorkNo(wordNo));
    }

    //行长权限控制       成员类型
    @RequestMapping(value = "/queryBywordNo", method = RequestMethod.GET)
    public Result<?> queryBywordNo(@RequestParam("zrrid") String zrrid,@RequestParam("id") String id) {
        Jtspcy jtspcy = jtspcyService.queryById(id,zrrid);
        return Result.ok(jtspcy);
    }
    /**
     * 将图片转换成Base64编码
     *
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgBase64Str(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null) {
            return new String();
        } else {
            return "data:image/png;base64," + new String(Base64.encodeBase64(data));
        }
    }

    /**
     * 添加
     *
     * @param jtspcy
     * @return
     */
    @AutoLog(value = "集体审批成员-添加")
    @ApiOperation(value = "集体审批成员-添加", notes = "集体审批成员-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Jtspcy jtspcy) {
        jtspcyService.save(jtspcy);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jtspcy
     * @return
     */
    @AutoLog(value = "集体审批成员-编辑")
    @ApiOperation(value = "集体审批成员-编辑", notes = "集体审批成员-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Jtspcy jtspcy) {
        jtspcyService.updateById(jtspcy);
        return Result.ok("编辑成功!");
    }

    /**
     * 贷款审批编辑
     *
     * @param
     * @return
     */
    @AutoLog(value = "集体审批成员-编辑")
    @ApiOperation(value = "集体审批成员-编辑", notes = "集体审批成员-编辑")
    @PostMapping(value = "/editDksp")
    public Result<?> editDksp(@RequestBody Jtspcy jtspcy) throws Exception {
        System.out.println("jjj-----" + jtspcy);
//		 UpdateWrapper<Jtspcy> userUpdateWrapper = new UpdateWrapper<>();
//		 QueryWrapper<Jtspcy> queryWrapper = new QueryWrapper<>();
//		 queryWrapper.eq("id",jtspcy.getId());
//		 queryWrapper.eq("zrrid",jtspcy.getZrrid());
//		 jtspcyService.update(queryWrapper);
        jtspcy.setQmtp(saveImg(jtspcy.getQmtp()));
        jtspcyService.updatespjl(jtspcy);
        return Result.ok("编辑成功!");
    }


    public String saveImg(String baseImg) throws Exception {
        //定义一个正则表达式的筛选规则，为了获取图片的类型
        String rgex = "data:image/(.*?);base64";
        String type = getSubUtilSimple(baseImg, rgex);
        //去除base64图片的前缀
        baseImg = baseImg.replaceFirst("data:(.+?);base64,", "");
        byte[] b;
        byte[] bs;
        OutputStream os = null;
        String fileName = "";
        String nowDate = "";
        // 格式化并获取当前日期（用来命名）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = format.format(new Date());
        //把图片转换成二进制
        b = Base64.decodeBase64(baseImg.replaceAll(" ", "+"));
        //生成路径
        String path = uploadpath + File.separator + "imgSign" + File.separator + nowDate + File.separator;
        //随机生成图片的名字，同时根据类型结尾
        fileName = UUID.randomUUID().toString() + "." + type;
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        File imageFile = new File(path + "/" + fileName);
        BASE64Decoder d = new BASE64Decoder();
        // 保存
        try {
            bs = d.decodeBuffer(Base64.encodeBase64String(b));
            os = new FileOutputStream(imageFile);
            os.write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.getLocalizedMessage();
                }
            }
        }

        return "imgSign" + File.separator + nowDate + File.separator + fileName;
    }

    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";


    }

    /**
     * 给指定部门添加对应的用户
     */
    @RequestMapping(value = "/editJtspcy", method = RequestMethod.POST)
    public Result<String> editJtspcy(@RequestBody JtspxzcyVO jtspxzcyVO) {
        Result<String> result = new Result<String>();
        try {
            String jtspid = jtspxzcyVO.getId();
            List<String> zrrids=new ArrayList<>();
            for (Jtspcy jtspcy : jtspxzcyVO.getJtspcyList()) {
                zrrids.add(jtspcy.getZrrid());
            }

            jtspcyService.deleteJtspcyByZrrids(jtspid,zrrids);

            for (Jtspcy jtspcy : jtspxzcyVO.getJtspcyList()) {
                jtspcy.setId(jtspid);
                QueryWrapper<Jtspcy> queryWrapper = new QueryWrapper<Jtspcy>();
                queryWrapper.eq("id", jtspid).eq("zrrid", jtspcy.getZrrid());
                Jtspcy one = jtspcyService.getOne(queryWrapper);
                if (one == null) {
                    Vhrbasstaffpost hrbasstaffpost = hrbasstaffpostService.selectYgList(jtspcy.getZrrid(), jtspxzcyVO.getZzbz());
                    if (hrbasstaffpost != null) {
                        jtspcy.setZrrgwid(String.valueOf(hrbasstaffpost.getGwbz().intValue()));
                        jtspcy.setZrrmc(hrbasstaffpost.getYgxm());
                        jtspcyService.save(jtspcy);
                    } else {
                        result.setMessage("添加失败，员工岗位信息不存在!");
                        result.setSuccess(false);
                    }
                }else{
                    one.setZrrlx(jtspcy.getZrrlx());
                    jtspcyService.update(one,queryWrapper);
                    result.setMessage("修改成功!");
                    result.setSuccess(true);

                }
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }


    /**
     * 给指定部门添加对应的用户
     */
    @GetMapping(value = "/deleteJtspcy")
    public Result<String> deleteJtspcy(@RequestParam(name = "id") String id, @RequestParam(name = "yggh") String yggh) {
        Result<String> result = new Result<String>();
        try {
            jtspcyService.deleteJtspcy(id, yggh);
            result.setMessage("操作成功!");
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "集体审批成员-通过id删除")
    @ApiOperation(value = "集体审批成员-通过id删除", notes = "集体审批成员-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        jtspcyService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "集体审批成员-批量删除")
    @ApiOperation(value = "集体审批成员-批量删除", notes = "集体审批成员-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jtspcyService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "集体审批成员-通过id查询")
    @ApiOperation(value = "集体审批成员-通过id查询", notes = "集体审批成员-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Jtspcy jtspcy = jtspcyService.getById(id);
        return Result.ok(jtspcy);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jtspcy
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Jtspcy jtspcy) {
        return super.exportXls(request, jtspcy, Jtspcy.class, "集体审批成员");
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
        return super.importExcel(request, response, Jtspcy.class);
    }

}
