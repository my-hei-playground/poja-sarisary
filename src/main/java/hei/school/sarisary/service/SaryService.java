package hei.school.sarisary.service.event;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

@Service
public class SaryService {

    public File convertByteArrayToGrayScaleFile(byte[] imageData, String fileName) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));

        BufferedImage grayImage = Scalr.apply(originalImage, Scalr.OP_GRAY);

        File file = new File(getJpgFileName(fileName));
        ImageIO.write(grayImage, "jpg", file);

        return file;
    }

    public File convertByteArrayToFile(byte[] imageData, String fileName) throws IOException {
        File file = new File(getJpgFileName(fileName));

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
        }

        return file;
    }

    private String getJpgFileName(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        if (!lowerCaseFileName.endsWith(".jpg") &&
                !lowerCaseFileName.endsWith(".jpeg") &&
                !lowerCaseFileName.endsWith(".png") &&
                !lowerCaseFileName.endsWith(".gif")) {
            fileName += ".jpg";
        }
        return fileName;
    }
}