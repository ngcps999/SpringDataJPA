package com.fyerp.admin.utils.search;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchOrder implements Serializable {
    private String propName;
    private String order;
}
