package com.lj.redis.controller;

import com.lj.redis.common.Result;
import com.lj.redis.common.enums.ErrorMsgEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description: 文件控制器
 * @ClassName: FileController
 * @Author: liang_jun
 * @Date: 2020/9/1 14:42
 */
@RestController
@RequestMapping("/file")
public class FileController {
    /**
     * 本地文件上传
     *
     * @param file : 文件对象
     * @author liang_jun
     * @date 2020/9/1 14:45
     */
    @PostMapping("/fileUpload")
    public Result<Object> fileUpload(@RequestParam("file") MultipartFile file) {
        //文件名称
        String fileName = file.getOriginalFilename();
        //目标文件夹
        File outFile = new File("D:" + File.separator + "file" + File.separator + fileName);

        try {
            file.transferTo(outFile);
            return new Result<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result<>(ErrorMsgEnum.FILE_UPLOAD_ERROR.getCode(), ErrorMsgEnum.FILE_UPLOAD_ERROR.getMsg());
    }

    /**
     * 文件下载
     *
     * @author liang_jun
     * @date 2020/9/1 15:11
     */
    @GetMapping("/fileDownload")
    public Result<Object> fileDownload(HttpServletResponse response) {

        String path = "D:\\file\\迪玛利亚.jpg";
        //下载路径
        File file = new File(path);
        if (!file.exists()) {
            return new Result<Object>(ErrorMsgEnum.FILE_THERE_IS_NO.getCode(), ErrorMsgEnum.FILE_THERE_IS_NO.getMsg());
        }
        //文件名称
        String fileName = file.getName();
        // 取得文件的后缀名。
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
        // 以流的形式下载文件。
        InputStream fis = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(ErrorMsgEnum.FILE_DOWNLOAD_ERROR.getCode(), ErrorMsgEnum.FILE_DOWNLOAD_ERROR.getMsg());
        }
        return null;
    }
}
