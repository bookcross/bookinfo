package com.trembear.bookinfo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-11-27 16:08
 */
@Service("requestAuthenticationService")
public class RequestAuthenticationService {
    Logger logger=LoggerFactory.getLogger(RequestAuthenticationService.class);
    /**
     *@description  是否拥有控制权限
     *@param  [ authentication,request ]
     *@return boolean
     *@Author junwei.xiong
     *@date 16:15 2018/11/27
     **/
    public boolean isPossessPermission(Authentication authentication, HttpServletRequest request){
        Object principal =  authentication.getPrincipal();
        logger.info("访问URI为"+request.getRequestURI());
        if(principal instanceof UserDetails){
            UserDetails backendUser = (UserDetails)principal;
            logger.info("UserDetails正常用户为"+ backendUser.getUsername());
        }else{
           String name=(String)principal;
            if(!name.equals("tom")){
                logger.info("匿名用户为"+ principal);
                return true;
            }
            logger.info("String类型正常用户为"+ name);
        }
        return true;
    }
}
