package org.cmms.modules.pad.pyxx.vo;

import lombok.Data;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PlpyFjxx {
    //评议员在农户信息中的ID
    String pyyKhid;
    //评议员证件号码
    String pyyzjhm;
    //评议员ID
    String pyyid;
    //客户ID
    String sxdxid;
    //上传的附件列表
    List<Pyfjxx>  uploadFiles;
    //删除的附件列表
    List<Pyfjxx> deleteFiles;
}
