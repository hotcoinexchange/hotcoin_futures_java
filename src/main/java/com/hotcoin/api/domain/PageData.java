package com.hotcoin.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {

    private Long total = 0L;

    private List<T> rows;

}
