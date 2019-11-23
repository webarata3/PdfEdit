package dev.webarata3.pdf;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton rotate90Button;
    private JButton convertJpegButton;
    private JTextField jpegFileNamePrefixTextField;
    private JButton separateButton;
    private JTextField separatePrefixTextField;

    private File srcPdfFile;

    public MainFrame() {
        super("PDF編集");
        setSize(600, 400);
        try {
            System.out.println(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        var mainPanel = new JPanel();

        var openFileButton = new JButton("PDFファイルを開く");
        openFileButton.addActionListener(e -> {
            openFile();
        });

        rotate90Button = new JButton("右に90度回転");
        rotate90Button.addActionListener(e -> {
            rotateFile();
        });
        rotate90Button.setEnabled(false);

        jpegFileNamePrefixTextField = new JTextField(3);

        convertJpegButton = new JButton("画像に変換");
        convertJpegButton.addActionListener(e -> {
            convertJpeg();
        });

        separatePrefixTextField = new JTextField(3);
        separateButton = new JButton("分割");
        separateButton.addActionListener(e -> {
            separetePdf();
        });

        mainPanel.add(openFileButton);
        mainPanel.add(rotate90Button);
        mainPanel.add(jpegFileNamePrefixTextField);
        mainPanel.add(convertJpegButton);
        mainPanel.add(separatePrefixTextField);
        mainPanel.add(separateButton);

        var contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private void srcPdfOk() {
        rotate90Button.setEnabled(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF", "pdf"));

        int selected = fileChooser.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            srcPdfFile = fileChooser.getSelectedFile();
            srcPdfOk();
        } else if (selected == JFileChooser.CANCEL_OPTION) {
        } else if (selected == JFileChooser.ERROR_OPTION) {
        }
    }

    private void rotateFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF", "pdf"));

        int selected = fileChooser.showSaveDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            var outputFile = fileChooser.getSelectedFile();
            EditPdf.rotate(srcPdfFile, outputFile, 90);
        } else if (selected == JFileChooser.CANCEL_OPTION) {
        } else if (selected == JFileChooser.ERROR_OPTION) {
        }
    }

    private void convertJpeg() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int selected = fileChooser.showSaveDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            String prefix = jpegFileNamePrefixTextField.getText();
            File outputFolder = fileChooser.getSelectedFile();
            EditPdf.convertJpeg(srcPdfFile, outputFolder, prefix);
        } else if (selected == JFileChooser.CANCEL_OPTION) {
        } else if (selected == JFileChooser.ERROR_OPTION) {
        }
    }

    private void separetePdf() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int selected = fileChooser.showSaveDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            String prefix = separatePrefixTextField.getText();
            File outputFolder = fileChooser.getSelectedFile();
            EditPdf.separate(srcPdfFile, outputFolder, prefix);
        } else if (selected == JFileChooser.CANCEL_OPTION) {
        } else if (selected == JFileChooser.ERROR_OPTION) {
        }
    }
}
