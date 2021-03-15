package base;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * @program: xiaomu-oauth
 * @description:
 * @author: xiamu
 * @create: 2021-03-09 17:49
 */
public class BaseTest {
    public static void main(String[] args){
        MultiFormatReader multiFormatReader=new MultiFormatReader();
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"GBK");
        try{
            BufferedImage source= ImageIO.read(new File("/Users/liuhao/Desktop/WechatIMG3259.jpeg"));
            BinaryBitmap binaryImg=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(source)));
            Result result=multiFormatReader.decode(binaryImg,hints);
            System.out.println(result.getText());
            System.out.println(result.getBarcodeFormat());

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
