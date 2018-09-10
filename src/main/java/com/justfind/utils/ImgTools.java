package com.justfind.utils;







import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import magick.CompositeOperator;
import magick.CompressionType;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;
import magick.PreviewType;      
    
public class ImgTools {      
    static{   
        //不能漏掉这个，不然jmagick.jar的路径找不到   
        System.setProperty("jmagick.systemclassloader","no");   
    }      
    /**  
     * 压缩图片，不变形  
     * @param filePath 源文件路径  
     * @param toPath   缩略图路径  
     * @param width 设定宽  
     * @param height 设定长  
     * @throws MagickException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */  
    public static void changeSize(String filePath, String toPath,int maxSize) throws MagickException, FileNotFoundException, IOException{   
        ImageInfo info = null;   
        MagickImage image = null;   
        Dimension imageDim = null;   
        MagickImage scaled = null;   
        try{   
            info = new ImageInfo(filePath);   
            info.setCompression(CompressionType.ZipCompression);
            info.setQuality(100);   
            image = new MagickImage(info);   
            imageDim = image.getDimension();
            File picture = new File(filePath);  
            System.out.println(String.format("%.1f",picture.length()/1024.0));
            int srcW = imageDim.width;   
            int srcH = imageDim.height; 
            int newW = maxSize;
            int newH = (newW*srcH)/srcW;
            if(newH > maxSize) { 
            	newH = maxSize;   
            	newW = (newH*srcW)/srcH;
            }
            scaled = image.scaleImage(newW, newH);//小图片文件的大小.   
            scaled.setFileName(toPath);   
            scaled.writeImage(info);   
        }finally{   
            if(scaled != null){   
                scaled.destroyImages();   
            }   
        }   
    }      
    
    /**  
     * 水印(图片logo)  
     * @param filePath  源文件路径  
     * @param toImg     修改图路径  
     * @param logoPath  logo图路径  
     * @throws MagickException  
     */  
    public static void initLogoImg(String filePath, String toImg, String logoPath) throws MagickException {   
        ImageInfo info = new ImageInfo();   
        MagickImage fImage = null;   
        MagickImage sImage = null;   
        MagickImage fLogo = null;   
        MagickImage sLogo = null;   
        Dimension imageDim = null;   
        Dimension logoDim = null;   
        try {   
            fImage = new MagickImage(new ImageInfo(filePath));   
            imageDim = fImage.getDimension();   
            int width = imageDim.width;   
            int height = imageDim.height;   
            if (width > 660) {   
                height = 660 * height / width;   
                width = 660;   
            }   
            sImage = fImage.scaleImage(width, height);      
    
            fLogo = new MagickImage(new ImageInfo(logoPath));   
            logoDim = fLogo.getDimension();   
            int lw = width / 8;   
            int lh = logoDim.height * lw / logoDim.width;   
            sLogo = fLogo.scaleImage(lw, lh);      
    
            sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,  width-(lw + lh/10), height-(lh + lh/10));   
            sImage.setFileName(toImg);   
            sImage.writeImage(info);   
        } finally {   
            if(sImage != null){   
                sImage.destroyImages();   
            }   
        }   
    }      
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	try {
    		changeSize("c:/testPic/2a.jpg", "c:/testPic/22a.jpg", 600);
			System.out.println("$$$$$$$$$$$$$$$$");
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**  
     * 水印(文字)  
     * @param filePath 源文件路径  
     * @param toImg    修改图路径  
     * @param text     名字(文字内容自己随意)  
     * @throws MagickException  
     */  
    public static void initTextToImg(String filePath, String toImg,  String text) throws MagickException{   
            ImageInfo info = new ImageInfo(filePath);   
            if (filePath.toUpperCase().endsWith("JPG") || filePath.toUpperCase().endsWith("JPEG")) {   
                info.setCompression(CompressionType.JPEGCompression); //压缩类别为JPEG格式   
                info.setPreviewType(PreviewType.JPEGPreview); //预览格式为JPEG格式   
                info.setQuality(95);   
            }   
            MagickImage aImage = new MagickImage(info);   
            Dimension imageDim = aImage.getDimension();   
            int wideth = imageDim.width;   
            int height = imageDim.height;   
            if (wideth > 660) {   
                height = 660 * height / wideth;   
                wideth = 660;   
            }   
            int a = 0;   
            int b = 0;   
            String[] as = text.split("");   
            for (String string : as) {   
                if(string.matches("[\u4E00-\u9FA5]")){   
                    a++;   
                }   
                if(string.matches("[a-zA-Z0-9]")){   
                    b++;   
                }   
            }   
            int tl = a*12 + b*6 + 300;   
            MagickImage scaled = aImage.scaleImage(wideth, height);   
            if(wideth > tl && height > 5){   
                DrawInfo aInfo = new DrawInfo(info);   
                aInfo.setFill(PixelPacket.queryColorDatabase("white"));   
                aInfo.setUnderColor(new PixelPacket(0,0,0,100));   
                aInfo.setPointsize(12);   
                //解决中文乱码问题,自己可以去随意定义个自己喜欢字体，对于移植有点问题，所以暂且注释   
           //     String fontPath = "C:/WINDOWS/Fonts/MSYH.TTF";   
            //    aInfo.setFont(fontPath);   
                aInfo.setTextAntialias(true);   
                aInfo.setOpacity(0);   
                aInfo.setText("　" + text + "于　" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "　上传于XXXX网，版权归作者所有！");   
                aInfo.setGeometry("+" + (wideth-tl) + "+" + (height-5));   
                scaled.annotateImage(aInfo);   
            }   
            scaled.setFileName(toImg);   
            scaled.writeImage(info);   
            scaled.destroyImages();   
    }      
    
    /**  
     * 切图  
     * @param imgPath 源图路径  
     * @param toPath  修改图路径  
     * @param w  宽度  
     * @param h 高度  
     * @param x 左上角的 X 坐标  
     * @param y 左上角的 Y 坐标  
     * @throws MagickException  
     */  
    public static void cutImg(String imgPath, String toPath, int w, int h, int x, int y) throws MagickException {   
        ImageInfo infoS = null;   
        MagickImage image = null;   
        MagickImage cropped = null;   
        Rectangle rect = null;   
        try {   
            infoS = new ImageInfo(imgPath);   
            image = new MagickImage(infoS);   
            rect = new Rectangle(x, y, w, h);   
            cropped = image.cropImage(rect);   
            cropped.setFileName(toPath);   
            cropped.writeImage(infoS);      
    
        } finally {   
            if (cropped != null) {   
                cropped.destroyImages();   
            }   
        }   
    }   
}  
