package com.trembear.bookinfo.service;

import com.trembear.bookinfoapi.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfoapi.dto.BookDto;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-12-06 16:35
 */
public interface BookInfoService {
     /**
      * BookController里面的几个方法，在service里面需要实现
      * getPageDetail
      * searchBook
      * getBookDetail
      * addBook
      * deleteBook
      * collect
      * updateBook
      */
     PageDetail<BookDto> getPageDetail(Integer type, Integer pageNum, Integer pageSize);
     PageDetail<BookDto> searchBook( Integer type,String keyword, Integer pageNum,Integer pageSize);
     BookDto getBookDetail(Long id);
     RestFulVO<String> addBook(BookDto bookDto);
     RestFulVO<String> deleteBook(Long id);
     RestFulVO<String> collect(Integer bookId,Integer userId,Integer type);
     RestFulVO<String> updateBook(BookDto bookDto);
}
