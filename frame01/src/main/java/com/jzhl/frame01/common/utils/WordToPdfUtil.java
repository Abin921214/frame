package com.jzhl.frame01.common.utils;

import com.aspose.words.Shape;
import com.aspose.words.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @ClassName: Word2PdfUtilt
 * @Description: PDF转换工具类
 * @author xiaobin
 * @date 2018年7月30日 下午5:26:44
 *
 */
public class WordToPdfUtil {

    public static void main(String[] args) throws Exception{
        System.out.println(wordTopdf("E:\\word\\qqq.docx","E:\\word\\test.pdf", "高超网络", true));
    }


    /**
     * word 转 pdf 格式工具 并添加水印
     * @param inPath      word文档路径  绝对路径
     * @param outPath     输入文档路径  绝对路径
     * @param watermarkText   水印文字
     * @param isWater      是否水印
     * @return
     */
    public static Boolean wordTopdf(String inPath, String outPath, String watermarkText, Boolean isWater) {
        FileOutputStream os =null;
        try {
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            if(isWater){
                insertWatermarkText(doc,watermarkText);
            }
            doc.save(os, SaveFormat.PDF);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            // EPUB, XPS, SWF 相互转换
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @Title: insertWatermarkText
     * @Description: PDF生成水印
     * @author mzl
     * @param doc
     * @param watermarkText
     * @throws Exception
     * @throws
     */
    private static void insertWatermarkText(Document doc, String watermarkText) throws Exception
    {
        Paragraph watermarkPara = getParagraph(doc, watermarkText);
        for (Section sect : doc.getSections()) {
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }

        System.out.println("Watermark Set");
    }


    //水印设置
    public static Paragraph getParagraph1(Document doc, String watermarkText){
        // TEXT_PLAIN_TEXT
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);


        //水印内容
        watermark.getTextPath().setText(watermarkText);
        //水印字体
        watermark.getTextPath().setFontFamily("宋体");
        //水印宽度
        watermark.setWidth(100);
        //水印高度
        watermark.setHeight(20);
        //旋转水印
        watermark.setRotation(-40);
        //水印颜色
        watermark.getFill().setColor(Color.lightGray);
        watermark.setStrokeColor(Color.lightGray);

        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);
        return watermarkPara;
    }

    //水印设置
    public static Paragraph getParagraph(Document doc, String watermarkText){

        Paragraph watermarkPara = new Paragraph(doc);
        Double topStart = 50.00;
        Double leftStart = 50.00;

        for (int i=1; i<5; i++){
            for (int j=1;j<8;j++){
                // TEXT_PLAIN_TEXT
                Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);

                //水印内容
                watermark.getTextPath().setText(watermarkText);
                //水印字体
                watermark.getTextPath().setFontFamily("宋体");
                //水印宽度
                watermark.setWidth(70);
                //水印高度
                watermark.setHeight(17);
                //旋转水印
                watermark.setRotation(-40);
                //水印颜色
                watermark.getFill().setColor(Color.lightGray);
                watermark.setStrokeColor(Color.lightGray);
                /*watermark.getFill().setColor(Color.getHSBColor(192,192,192));
                watermark.setStrokeColor(Color.getHSBColor(192,192,192));*/

                watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
                watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
                watermark.setWrapType(WrapType.NONE);
                /*watermark.setVerticalAlignment(VerticalAlignment.CENTER);
                watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);*/
                watermark.setTop(topStart * j * 2);
                watermark.setLeft(leftStart * i * 2);
                watermarkPara.appendChild(watermark);
            }
        }
        return watermarkPara;
    }


    private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception
    {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

        if (header == null)
        {
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }

        header.appendChild(watermarkPara.deepClone(true));
    }


}

