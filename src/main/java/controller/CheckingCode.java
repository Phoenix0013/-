package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checking")
public class CheckingCode {
	@RequestMapping("code")
	public void CkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int width = 120;
		int height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.black);
		graphics.drawRect(0, 0, width - 1, height - 1);
		String str = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 4; i++) {
			int index = random.nextInt(str.length());
			char c = str.charAt(index);
			sb.append(c);
			graphics.drawString(c + "", width / 5 * i, height / 2);
		}
		String code = sb.toString();
		request.getSession().setAttribute("code", code);
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
}
