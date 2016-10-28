package com.upsmart.message.dto;

import java.io.File;

public class FileDto {
    private String fileName;
    private File fileBytes;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(File fileBytes) {
        this.fileBytes = fileBytes;
    }
}
