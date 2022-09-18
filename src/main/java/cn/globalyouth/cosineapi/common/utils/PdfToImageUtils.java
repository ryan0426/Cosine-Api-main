package cn.globalyouth.cosineapi.common.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfToImageUtils {
    /**
     * dpi越大转换后越清晰，相对转换速度越慢
     */
    private static final Integer DPI = 360;

    /**
     * 转换后的图片类型
     */
    private static final String IMG_TYPE = "jpg";

    /**
     * PDF转图片
     *
     * @param fileContent PDF文件的二进制流
     * @return 图片文件的二进制流
     */
    public static List<byte[]> pdfToImage(byte[] fileContent) throws IOException {
        List<byte[]> result = new ArrayList<>();
        try (PDDocument document = PDDocument.load(fileContent)) {
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < document.getNumberOfPages(); ++i) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, DPI);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, IMG_TYPE, out);
                result.add(out.toByteArray());
            }
        }
        return result;
    }
    /**
     * PDF转图片
     *
     * @param inputStream PDF文件的二进制流
     * @return 图片文件的二进制流
     */
    public static byte[] pdfToImage2(InputStream inputStream) throws IOException {
        byte[] result = null;
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage bufferedImage = renderer.renderImageWithDPI(0, DPI);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, IMG_TYPE, out);
            result = out.toByteArray();
        }
        return result;
    }




}

