package com.tyss.restdemo.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path rootDirectory;

    public FileStorageService(@Value("${app.file-storage.directory:uploads/employee-documents}") String directory) {
        this.rootDirectory = Paths.get(directory).toAbsolutePath().normalize();
    }

    public StoredFile store(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required and cannot be empty");
        }

        Files.createDirectories(rootDirectory);
        String originalName = StringUtils.cleanPath(file.getOriginalFilename() == null
                ? "file"
                : file.getOriginalFilename());
        String extension = "";
        int dot = originalName.lastIndexOf('.');
        if (dot >= 0) extension = originalName.substring(dot);

        String storedName = UUID.randomUUID() + extension;
        Path target = rootDirectory.resolve(storedName).normalize();
        if (!target.getParent().equals(rootDirectory)) {
            throw new IllegalArgumentException("Invalid file name");
        }

        Files.copy(file.getInputStream(), target);
        return new StoredFile(originalName, storedName, file.getContentType(), file.getSize(), target);
    }

    public void delete(Path path) throws IOException {
        if (path != null) Files.deleteIfExists(path);
    }

    public record StoredFile(
            String originalFileName,
            String storedFileName,
            String contentType,
            long size,
            Path path) {}
}
