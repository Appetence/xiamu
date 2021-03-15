package com.umpay.rms.gpd.user.util.fileUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: rms-gpd
 * @description: 文件工具类
 * @author: xiamu
 * @create: 2020-06-08 15:12
 */
@Component
public class FileUtill {

    public static String localLoad;

    public static double filesize =1887436.8;//1843.2*1024;   b

    public static double excelfilesize =1;//m

    public static double getFilesize() {
        return filesize;
    }
    @Value("${all.in.one.filesize}")
    public void setFilesize(double filesize) {
        FileUtill.filesize = filesize;
    }

    public static double getExcelfilesize() {
        return excelfilesize;
    }
    @Value("${all.in.one.excelfilesize}")
    public  void setExcelfilesize(double excelfilesize) {
        FileUtill.excelfilesize = excelfilesize;
    }

    /**
     * 判断文件大小//
     * 1991680
     * 1887436.8
     * @param len
     *            文件长度
     * @param size
     *            限制大小
     * @param unit
     *            限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, double size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
    public static  String saveFile(MultipartFile multipartFile,HttpServletRequest request) throws IllegalStateException, IOException {
        String fileUrl = null;
        String strBackUrl = "http://" + request.getServerName(); //服务器地
        if (multipartFile != null && !multipartFile.isEmpty()) {
            Calendar date1 = Calendar.getInstance();
            String dataurl = date1.get(Calendar.YEAR) + File.separator + (date1.get(Calendar.MONTH) + 1)+File.separator + (date1.get(Calendar.DATE));
            File dateDirs = new File(dataurl);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            // uploads文件夹位置
            String rootPath = localLoad;
            // 原始名称
            String originalFileName = multipartFile.getOriginalFilename();
            // 新文件名
            String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));
            File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
            // 判断目标文件所在目录是否存在
            if (!newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }

            // 将内存中的数据写入磁盘
            multipartFile.transferTo(newFile);
            // 完整的url
            fileUrl = "/static/upload/"+dataurl+newFileName;
            System.out.println("fileurl:"+fileUrl);
        }
        return fileUrl;
    }
}
