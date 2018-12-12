package com.trembear.bookinfo.dao;

import java.util.List;

import com.trembear.bookinfo.entity.BCAdmin;
import com.trembear.bookinfo.entity.BCAdminExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Mapper
public interface BCAdminMapper {

    int countByExample(BCAdminExample example);

    int deleteByExample(BCAdminExample example);

    int deleteByPrimaryKey(Integer adminid);

    int insert(BCAdmin record);

    int insertSelective(BCAdmin record);

    List<BCAdmin> selectByExample(BCAdminExample example);

    BCAdmin selectByPrimaryKey(Integer adminid);

    int updateByExampleSelective(@Param("record") BCAdmin record, @Param("example") BCAdminExample example);

    int updateByExample(@Param("record") BCAdmin record, @Param("example") BCAdminExample example);

    int updateByPrimaryKeySelective(BCAdmin record);

    int updateByPrimaryKey(BCAdmin record);
}