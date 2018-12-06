package com.trembear.bookinfo.controller;

import com.trembear.bookinfo.common.vo.PageDetail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-12-06 17:57
 */
@RestController
@RequestMapping("/bookInfo")
public class BookInfoController {
    @RequestMapping("/readAll")
    public PageDetail getPageDetail(@RequestParam(value = "labelId") Integer labelId, @RequestParam(value = "type", defaultValue = "1") Integer type,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return null;
    }
}
