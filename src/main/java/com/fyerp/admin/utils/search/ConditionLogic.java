package com.fyerp.admin.utils.search;

import org.apache.commons.lang3.StringUtils;

public class ConditionLogic {
    public static final int AND_OP = 0;
    public static final int OR_OP = AND_OP+1;

    public static String getLogic(String str){
        if(StringUtils.isNotEmpty(str)){
            int i = Integer.valueOf(str).intValue();
            if(i == AND_OP){
                return " and ";
            }else if(i == OR_OP){
                return " or ";
            }else{
                return " ";
            }
        }else {
            return " ";
        }
    }
}
