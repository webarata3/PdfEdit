package dev.webarata3.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class EditPdf {
    public static void convertJpeg(File inputFile, File outputFolder, String prefix) {
        try (PDDocument doc = PDDocument.load(inputFile)) {
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                PDFRenderer pdfRenderer = new PDFRenderer(doc);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
                ImageIO.write(bim, "JPEG", new File(outputFolder, prefix + i + ".jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rotate(File inputFile, File outputFile, int angle) {
        try (PDDocument doc = PDDocument.load(inputFile)) {
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                PDPage page = doc.getPage(i);
                page.setRotation(angle);
            }
            doc.save(outputFile);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
