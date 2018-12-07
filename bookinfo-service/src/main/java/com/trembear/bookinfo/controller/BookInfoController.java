package com.trembear.bookinfo.controller;

import com.trembear.bookinfo.common.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfoapi.dto.BookDetailDto;
import com.trembear.bookinfoapi.dto.BookDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Junwei.Xiong
 * description
 * since 2018-12-06 17:57
 */
@RestController
@RequestMapping("/bookInfo")
public class BookInfoController {
   /**
    * @author junwei.xiong
    * Description  //TODO getPageDetail
    * @date 10:55 2018/12/7
    * @param [type, pageNum, pageSize]
    * @return com.trembear.bookinfo.common.vo.PageDetail
    **/
    @RequestMapping("/readAll")
    public PageDetail getPageDetail(@RequestParam(value = "type") Integer type,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO searchBook
     * @date 10:55 2018/12/7
     * @param [field, keyword, pageNum, pageSize]
     * @return com.trembear.bookinfo.common.vo.PageDetail
     **/
    @RequestMapping("/searchBook")
    public PageDetail searchBook(
            @RequestParam(value = "field") String field,
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO 获取详细信息，需要组装图书和评论为BookDetialDto
     * @date 11:27 2018/12/7
     * @param []
     * @return com.trembear.bookinfoapi.dto.BookDetailDto
     **/
    @RequestMapping("/getBookDetail")
    public BookDetailDto getBookDetail(@RequestParam(value = "id")Integer id){
        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO 添加图书
     * @date 11:31 2018/12/7
     * @param [bookDto]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/addBook")
    public RestFulVO addBook(@RequestBody BookDto bookDto){
        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO deleteBook 删除图书信息
     * @date 11:34 2018/12/7
     * @param [id]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/deleteBook")
    public RestFulVO deleteBook(@RequestParam(value = "id")Integer id){
        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO collect 收藏/取消收藏，type=1:收藏，type=0：取消收藏
     * @date 11:36 2018/12/7
     * @param [bookId, userId]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/collect")
    public RestFulVO collect(@RequestParam(value = "bookId")Integer bookId,@RequestParam(value = "userId")Integer userId,@RequestParam(value = "type")Integer type){
        return null;
    }
    /**
     * @author junwei.xiong
     * Description  //TODO updateBook 修改
     * @date 11:45 2018/12/7
     * @param [bookDto]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/updateBook")
    public RestFulVO updateBook(@RequestBody BookDto bookDto){
        return null;
    }


}
