package api.tools.image.image_scaling.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageData {

	private final String imageDataB64;

	public ImageData(String imageDataB64) {
		this.imageDataB64 = imageDataB64;
	}

	public String getImageData() {
		return imageDataB64;
	}

	public byte[] asByteArray() {
		return Base64.getDecoder().decode(imageDataB64);
	}

	public BufferedImage asBufferedImage() throws IOException {
		return ImageIO.read(new ByteArrayInputStream(asByteArray()));
	}

	public static ImageData fromBufferedImage(BufferedImage image) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		byte[] bytes = baos.toByteArray();
		String imageData64 =  Base64.getEncoder().encodeToString(bytes);
		return new ImageData(imageData64);
	}

	public static ImageData fromByteArray(byte[] bytes) {
		String imageData64 =  Base64.getEncoder().encodeToString(bytes);
		return new ImageData(imageData64);
	}
}
