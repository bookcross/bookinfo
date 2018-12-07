package com.trembear.bookinfo;

import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.entity.BookInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookinfoApplicationTests {
    @Autowired
    private BookInfoDao demoDao;
    @Test
    public void contextLoads() {
//        BookInfo bookInfo=new BookInfo();
//        bookInfo.setId(1L);
//        bookInfo.setAuthor("金庸");
//        bookInfo.setBookName("射雕英雄传");
//        demoDao.save(bookInfo);
//        bookInfo.setId(2L);
//        bookInfo.setAuthor("金庸");
//        bookInfo.setBookName("天龙八部");
//        demoDao.save(bookInfo);
//        bookInfo.setId(3L);
//        bookInfo.setAuthor("金庸");
//        bookInfo.setBookName("鹿鼎记");
//        demoDao.save(bookInfo);
//        bookInfo.setId(4L);
//        bookInfo.setAuthor("金庸");
//        bookInfo.setBookName("神雕侠侣");
//        demoDao.save(bookInfo);
        Map map=new HashMap<String, String>();
        map.put("bookName","神雕侠侣");
        System.out.println("总条数"+demoDao.recordTotal(map));
        List<BookInfo> list= demoDao.pageList(1,2,null,new Sort(Sort.Direction.DESC, "id"));
        List<String> list1=new ArrayList<>();
        list1.add("id");list1.add("bookName");
        List<BookInfo> list3= demoDao.pageList(1,2,null,new Sort(Sort.Direction.DESC, "id"),list1);
        for(BookInfo bookInfo1:list3){
//            BeanUtils.copyProperties(dto,bookInfo1);
            System.out.println(bookInfo1.getId()+bookInfo1.getBookName());
        }
//        bookInfo.setBookName("天龙八部");
//        demoDao.updateDemo(bookInfo);
//        System.out.println(demoDao.findDemoById(1L).getBookName());
    }

}
