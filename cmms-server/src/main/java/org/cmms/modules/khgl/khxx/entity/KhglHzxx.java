package org.cmms.modules.khgl.khxx.entity;

import lombok.Data;

import java.util.List;

@Data
public class KhglHzxx {
    private String hzxm;

    private List<vKhglKhjbxx> khjbxxList;
}
