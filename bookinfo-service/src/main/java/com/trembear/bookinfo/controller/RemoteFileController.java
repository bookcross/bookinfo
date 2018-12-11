package com.trembear.bookinfo.controller;
import com.trembear.bookinfo.common.enums.SystemRest;
import com.trembear.bookinfo.common.util.IDUtils;
import com.trembear.bookinfo.common.util.SFTPUtil;
import com.trembear.bookinfo.common.vo.RestFulVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-11 17:36
 */
@RestController
@RequestMapping("/file")
public class RemoteFileController {
    private static final Logger logger = LoggerFactory.getLogger(RemoteFileController.class);
    @Value("${sftp.url}")
    private String sftpUrl;
    @Value("${sftp.port}")
    private String port;
    @Value("${sftp.username}")
    private String username;
    @Value("${sftp.password}")
    private String password;
    @Value("${file.path}")
    private String path;
    @Value("${file.url}")
    private String url;

    /**
     * 单文件上传
     *
     * @param file
     * @return RestFulVO
     */
    @PostMapping("/upload")
    @ResponseBody
    public RestFulVO<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream ins = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            ins = file.getInputStream();
        }
        try {
            String filePath = url + SFTPUtil.upload(sftpUrl, new Integer(port).intValue(), username, password, path, IDUtils.createData(), IDUtils.createID() + "_" + file.getOriginalFilename(), ins);
            return new RestFulVO<String>(SystemRest.SUCCESS, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestFulVO<String>(SystemRest.UNKNOWN_ERROR);
    }
}
