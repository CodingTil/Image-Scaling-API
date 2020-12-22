package api.tools.image.image_scaling.api;

import api.tools.image.image_scaling.model.ImageData;
import api.tools.image.image_scaling.model.ScalingAlgorithms;
import api.tools.image.image_scaling.service.ScalingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("api/tools/image/image_scaling")
@RestController
public class ScalingController {

	private final ScalingService scalingService;

	@Autowired
	public ScalingController(ScalingService scalingService) {
		this.scalingService = scalingService;
	}

	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<byte[]> resize(@RequestParam("imageData") MultipartFile file,
	                                     @RequestParam ("algorithm") ScalingAlgorithms algorithm,
	                                     @RequestParam ("newWidth") int newWidth,
	                                     @RequestParam ("newHeight") int newHeight) throws IOException {
		ImageData imageData = ImageData.fromByteArray(file.getBytes());
		ImageData newImageData = scalingService.resize(imageData, algorithm, newWidth, newHeight);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(newImageData.asByteArray(), headers, HttpStatus.OK);

		return responseEntity;
	}
}
