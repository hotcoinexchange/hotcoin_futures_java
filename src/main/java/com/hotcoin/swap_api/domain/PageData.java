package com.hotcoin.swap_api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version V1.0
 * @description: PageData
 * @author: hotcoin
 * @date: 2022/4/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {
    private Long total = 0L;
    private List<T> rows;
}
