package com.trembear.bookinfo.common.exception;

import com.trembear.bookinfo.common.vo.RestFulVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-12-06 16:45
 */
public class BookException extends RuntimeException implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookException.class);
    public BookException(RestFulVO restFulVO){
        super(restFulVO.getRestMsg());
        this.restFulVO = restFulVO;
        LOGGER.error("BbsException error:{},{}",restFulVO.getRestCode(),restFulVO.getRestMsg());
    }
    public BookException() {
        super();
    }
    private RestFulVO restFulVO;
    public RestFulVO getRestFulVO() {
        return restFulVO;
    }
}
