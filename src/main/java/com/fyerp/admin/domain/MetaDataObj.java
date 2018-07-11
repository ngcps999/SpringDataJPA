package com.fyerp.admin.domain;

import lombok.Data;

/**
 * @author:xiasc
 * @Date:2018/7/11
 * @Time:14:52
 **/
@Data
public class MetaDataObj {
    private String name;
    private String label;
    private boolean notNull;
    private String type;
    private String length;
}
