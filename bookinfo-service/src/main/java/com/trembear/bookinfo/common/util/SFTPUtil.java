package com.trembear.bookinfo.common.util;

import com.trembear.bookinfo.entity.SFTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;
/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-11 17:27
 */
public class SFTPUtil {
    private static final Logger log = LoggerFactory.getLogger(SFTPUtil.class);

    /**
     * 连接ftp/sftp服务器
     *
     * @param
     */
    public static void getConnect(SFTP s, String host, int port, String username, String password) {

        /** 密钥的密码  */
//      String privateKey ="key";
        /** 密钥文件路径  */
//      String passphrase ="path";
        /** 主机 */
//        String host = "10.*.*.*";
        /** 端口 */
//        int port = 22;
        /** 用户名 */
//        String username = "root";
        /** 密码 */
//        String password = "****";

        Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;

        JSch jsch = new JSch();
        /**
         * 设置密钥和密码
         * 支持密钥的方式登陆，只需在jsch.getSession之前设置一下密钥的相关信息就可以了
         **/
//              if (privateKey != null && !"".equals(privateKey)) {
//                     if (passphrase != null && "".equals(passphrase)) {
//                      //设置带口令的密钥
//                         jsch.addIdentity(privateKey, passphrase);
//                     } else {
//                      //设置不带口令的密钥
//                         jsch.addIdentity(privateKey);
//                     }
//              }
        try {
            session = jsch.getSession(username, host, port);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        session.setPassword(password);
        Properties config = new Properties();
        // 不验证 HostKey
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected()) {
                session.disconnect();
            }
            log.error("连接服务器失败,请检查主机[" + host + "],端口[" + port
                    + "],用户名[" + username + "],端口[" + port
                    + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        try {
            channel = session.openChannel("sftp");
        } catch (JSchException e) {
            e.printStackTrace();
        }
        try {
            channel.connect();
        } catch (Exception e) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
            log.error("连接服务器失败,请检查主机[" + host + "],端口[" + port
                    + "],用户名[" + username + "],密码是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        sftp = (ChannelSftp) channel;
        s.setChannel(channel);
        s.setSession(session);
        s.setSftp(sftp);
    }

    public static void disConn(Session session, Channel channel, ChannelSftp sftp) {
        if (null != sftp) {
            sftp.disconnect();
            sftp.exit();
            sftp = null;
        }
        if (null != channel) {
            channel.disconnect();
            channel = null;
        }
        if (null != session) {
            session.disconnect();
            session = null;
        }
    }

    public static String upload(String sftpUrl, int port, String username, String password, String directory, String dateDir, String fileName, InputStream in) {
        SFTP s = new SFTP();
        getConnect(s, sftpUrl, port, username, password);
        Session session = s.getSession();
        Channel channel = s.getChannel();
        ChannelSftp sftp = s.getSftp();
        directory = directory + "/" + dateDir;
        try {
            sftp.cd(directory);
        } catch (SftpException sException) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                try {
                    sftp.mkdir(directory);
                    sftp.cd(directory);
                } catch (SftpException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            sftp.put(in, fileName);
            in.close();
            return dateDir + "/" + fileName;

        } catch (Exception e) {

        } finally {
            disConn(session, channel, sftp);
        }
        return "failure";
    }

}
