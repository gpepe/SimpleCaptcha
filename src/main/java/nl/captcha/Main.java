package nl.captcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.noise.StraightLineNoiseProducer;

public class Main {

	public static void main(String[] args) throws IOException {
		img2ds("data/simpleRNN/", 100);

	}

	public static void img2ds(String dir, int N) throws IOException {
		List<String> ans = new LinkedList<String>();
		PrintWriter out = new PrintWriter("/Users/baidu/dev/simplecaptcha/ans.txt");
		for (int i = 1; i <= N; i++) {
			if (i % 100 == 0)
				System.out.println(i + "," + N);
			Captcha cap = new Captcha.Builder(200, 50).addText().addBackground().addNoise()
					.addBackground(new GradiatedBackgroundProducer()).addNoise(new StraightLineNoiseProducer())
					.gimp(new FishEyeGimpyRenderer()).build();
			img2file("/Users/baidu/dev/simplecaptcha/" + i + ".png", cap.getImage());
			ans.add(cap.getAnswer());
			out.println(cap.getAnswer());
		}
		// System.out.println(ans);
		out.close();
	}

	public static void img2file(String fileName, BufferedImage img) throws IOException {
		ImageIO.write(img, "png", new File(fileName));

	}

}