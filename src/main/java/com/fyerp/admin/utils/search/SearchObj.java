package com.fyerp.admin.utils.search;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchObj implements Serializable {
    private String type;
    private List<SearchFilter> filters;
    private List<SearchOrder> orders;
    private int page;
    private int amount;
}
