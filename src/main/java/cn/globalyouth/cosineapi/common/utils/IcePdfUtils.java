package cn.globalyouth.cosineapi.common.utils;

/**
 * @author liuyufeng
 * icepdf pdf转图片工具
 */
public class IcePdfUtils {

    /**
     * pdf首页转图片
     *
     * @param inFile  pdf文件路径
     * @param outFile 输出图片路径
     */
    public static void generateThumb(String inFile, String outFile) throws Exception {
//        Document document = new Document();
//        document.setFile(inFile);
//        BufferedImage thumbPic = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
//                Page.BOUNDARY_CROPBOX, 0f, 0.8f);
//        Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("png");
//        ImageWriter writer = iter.next();
//        FileOutputStream out = new FileOutputStream(outFile);
//        ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
//        writer.setOutput(outImage);
//        writer.write(new IIOImage(thumbPic, null, null));
    }
}
