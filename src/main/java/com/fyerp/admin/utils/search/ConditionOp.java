package com.fyerp.admin.utils.search;

import com.sun.org.apache.xpath.internal.operations.Lte;

public class ConditionOp {
    public static final int GR_OP = 0;
    public static final int LT_OP = ( GR_OP + 1 ) ;//1
    public static final int GREQ_OP = ( LT_OP + 1 );//2
    public static final int LTEQ_OP = ( GREQ_OP + 1 ) ;//3
    public static final int EQ_OP = ( LTEQ_OP + 1 ) ;//4
    public static final int NOTEQ_OP = ( EQ_OP + 1 ) ;//5
    public static final int BETWEEN_OP = ( NOTEQ_OP + 1 ) ;//6
    public static final int CONTAIN_OP = ( BETWEEN_OP + 1 ) ;//7
    public static final int NOTCONTAIN_OP = ( CONTAIN_OP + 1 ) ;//8
    public static final int PRECONTAIN_OP = ( NOTCONTAIN_OP + 1 ) ;//9
    public static final int POSTCONTAIN_OP = ( PRECONTAIN_OP + 1 ) ;//10

    public static String getOp(int op){
        if(op == GR_OP)
            return " > ";
        if(op == LT_OP)
            return " < ";
        if(op == GREQ_OP)
            return " >= ";
        if(op == LTEQ_OP)
            return " <= ";
        if(op == EQ_OP)
            return " = ";
        if(op == NOTEQ_OP)
            return " <> ";
        if(op == BETWEEN_OP)
            return " between ";
        if(op == CONTAIN_OP)
            return " contain " ;
        if(op == NOTCONTAIN_OP)
            return " not_contain ";
        if(op == PRECONTAIN_OP)
            return " pre_contain ";
        if(op == POSTCONTAIN_OP)
            return " post_contain ";
        return " = ";
    }

}
