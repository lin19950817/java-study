package org.lzn;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/07 17:32
 */
public class FourHttpServletResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 110;
        int height = 25;
        // 在内存中创建一个图像对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 创建一个画笔
        Graphics graphics = image.getGraphics();

        // 将图片添加背景色
        graphics.setColor(Color.PINK);
        graphics.fillRect(1, 1, width - 2, height - 2);

        // 给边框一个颜色
        graphics.setColor(Color.RED);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 设置文本样式
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 15));

        // 生成四个随机数
        Random rand = new Random();
        int position = 20;
        for (int i = 0; i < 4; i++) {
            // 给图片添加文本
            graphics.drawString(Integer.toString(rand.nextInt(10)), position, 20);
            position += 20;
        }

        // 添加 9 条干扰线
        for (int i = 0; i < 9; i++) {
            graphics.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
        }

        // 高速客户端不使用缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setIntHeader("expires", 0);

        // 将图片对象以流的形式
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}
