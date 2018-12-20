package com.trembear.bookinfo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trembear.bookinfo.annotation.AutoInc;
import com.trembear.bookinfo.config.RedisConfig;
import com.trembear.bookinfo.dao.BCAdminMapper;
import com.trembear.bookinfo.dao.BookCrossRecoderDao;
import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.dao.BookReplyDao;
import com.trembear.bookinfo.entity.BCAdmin;
import com.trembear.bookinfo.entity.BCAdminExample;
import com.trembear.bookinfo.entity.BookCrossRecoder;
import com.trembear.bookinfo.entity.BookInfo;
import com.trembear.bookinfo.entity.BookReply;
import com.trembear.bookinfoapi.dto.BookDto;
import com.trembear.bookinfoapi.dto.BookReplyDto;
import com.trembear.bookinfoapi.dto.PicFileDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.AssertTrue;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookinfoApplicationTests {
    @Autowired
    private BookInfoDao demoDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BookReplyDao bookReplyDao;
    @Autowired
    private BCAdminMapper bcAdminMapper;
    @Autowired
    private BookCrossRecoderDao bookCrossRecoderDao;
@Test
public void mongoTest(){
    List<BookCrossRecoder> bookReplies = bookCrossRecoderDao.pageList(1, 5, null);
    for (BookCrossRecoder bookReply:bookReplies){
        System.out.println(bookReplies.get(0));
    }
}


    @Test
    public void bookTest(){
//        Map condition = new HashMap<String, String>();
//        condition.put("isDelete", "0");

        List<BookInfo> list3 = demoDao.pageList(1, 100, null, new Sort(Sort.Direction.DESC, "id"));
        for (BookInfo bookInfo:list3) {
//            demoDao.delete(bookInfo.getId());
            List<PicFileDto> list=(List<PicFileDto>) JSON.parseArray(bookInfo.getBookPic(),PicFileDto.class);
            for (PicFileDto picFileDto:list)
                System.out.println(picFileDto.getUrl());
            System.out.println(bookInfo.getId()+"---"+bookInfo.getBookName());
        }
    }


//    @Test
    public void redisTest() {
//        stringRedisTemplate.opsForValue().set("tom","123");
//        Assert.assertEquals("123",stringRedisTemplate.opsForValue().get("tom"));
//        BookReply bf=new BookReply();
//        bf.setContent("射雕英雄传");
//        BookReplyDto bt=new BookReplyDto();
//        BeanUtils.copyProperties(bf,bt);
//        System.out.println(bf.getContent());
//        System.out.println(bt.getContent());

//        for(int i=3;i<20;i++){
//            BCAdmin bcAdmin=new BCAdmin();
//            bcAdmin.setAdminid(i);
//            bcAdmin.setAdminname("admin"+i);
//            bcAdmin.setCreateby(0);
//            bcAdmin.setIsdelete("0");
//            bcAdmin.setIsused("1");
//            bcAdmin.setPassword("admin");
//            bcAdmin.setSalt("salt");
//            bcAdmin.setType("0");
//            bcAdminMapper.insert(bcAdmin);
//        }
       BCAdminExample bcAdminExample=new BCAdminExample();
       BCAdminExample.Criteria criteria = bcAdminExample.createCriteria();
        criteria.andAdminnameLike("tom");
        PageHelper.startPage(1, 5);
        List<BCAdmin> list= bcAdminMapper.selectByExample(bcAdminExample);
//        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
//        System.out.println("PageInfo: " + JSON.toJSONString(pageInfo));
        System.out.println("PageInfo: " + JSON.toJSONString(page));

    }
    //    @Test
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
        Map map = new HashMap<String, String>();
        map.put("bookName", "神雕侠侣");
        System.out.println("总条数" + demoDao.recordTotal(map));
        List<BookInfo> list = demoDao.pageList(1, 2, null, new Sort(Sort.Direction.DESC, "id"));
        List<String> list1 = new ArrayList<>();
//        list1.add("id");list1.add("bookName");
        List<BookInfo> list3 = demoDao.pageList(1, 2, null, new Sort(Sort.Direction.DESC, "id"), list1);
        for (BookInfo bookInfo1 : list3) {
//            BeanUtils.copyProperties(dto,bookInfo1);
            System.out.println(bookInfo1.getId() + bookInfo1.getBookName());
        }
//        bookInfo.setBookName("天龙八部");
//        demoDao.updateDemo(bookInfo);
//        System.out.println(demoDao.findDemoById(1L).getBookName());
    }

}
