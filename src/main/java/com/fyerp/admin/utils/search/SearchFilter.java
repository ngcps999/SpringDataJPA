package com.fyerp.admin.utils.search;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchFilter implements Serializable {
    private String propName;
    private int op;
    private String propValue;
    private String propValue2;
    private String logic;
    private int leftSplitter;
    private int rightSplitter;
}
