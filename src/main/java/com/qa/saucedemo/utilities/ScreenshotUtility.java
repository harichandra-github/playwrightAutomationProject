package com.qa.saucedemo.utilities;


import com.microsoft.playwright.Page;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import com.qa.saucedemo.logger.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ScreenshotUtility {

    private final Page page;
    private final String screenshotDir;

    public ScreenshotUtility(Page page) {
        this.page = page;
        this.screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        createScreenshotDirectory();
    }

    private void createScreenshotDirectory() {
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            boolean created = directory.mkdir();
            if (created) {
                Log.info("Created screenshots directory: " + screenshotDir);
            } else {
                Log.warn("Failed to create screenshots directory or already exists: " + screenshotDir);
            }
        }
    }

    /**
     * Capture screenshot, save locally, return file path.
     * Does NOT attach to report.
     *
     * @param baseFileName file name without extension ('.png' added automatically)
     * @return saved file path
     * @throws IOException if saving fails
     */
    public String captureScreenshot(String baseFileName) throws IOException {
        String fileName = addPngExtensionIfMissing(baseFileName);
        String filePath = screenshotDir + fileName;
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));
            Log.info("Screenshot captured and saved at: " + filePath);
        } catch (Exception e) {
            Log.error("Failed to capture screenshot: " + e.getMessage());
            throw e;
        }
        return filePath;
    }

    /**
     * Capture screenshot, save locally, attach to report if logger is provided.
     *
     * @param baseFileName file name without extension (".png" added automatically)
     * @param logger ExtentTest logger (if null, attachment skipped)
     * @return saved file path
     * @throws IOException if saving or encoding fails
     */
    public String captureScreenshot(String baseFileName, ExtentTest logger) throws IOException {
        String fileName = addPngExtensionIfMissing(baseFileName);
        String filePath = screenshotDir + fileName;

        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));
            Log.info("Screenshot captured and saved at: " + filePath);

            if (logger != null) {
                byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
                String base64Screenshot = Base64.getEncoder().encodeToString(fileContent);
                logger.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, fileName).build());
                Log.info("Screenshot attached to report with name: " + fileName);
            } else {
                Log.warn("ExtentTest logger is null, skipping screenshot attachment.");
            }

        } catch (Exception e) {
            Log.error("Failed to capture or attach screenshot: " + e.getMessage());
            throw e;
        }

        return filePath;
    }

    private String addPngExtensionIfMissing(String fileName) {
        return fileName.toLowerCase().endsWith(".png") ? fileName : fileName + ".png";
    }
}
