package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.models.File;
import com.crafter.crafttroveapi.repositories.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File uploadLogo (MultipartFile file) throws IOException {
        String uniqueUrl = UUID.randomUUID().toString();

        File logo = new File();
        logo.setName(file.getOriginalFilename());
        logo.setContentType(file.getContentType());
        logo.setFileData(file.getBytes());
        logo.setSize(file.getSize());
        logo.setUrl(uniqueUrl);

        return fileRepository.save(logo);
    }

    public Optional<File> getPhotoByUniqueUrl(String uniqueUrl) {
        return fileRepository.findByUrl(uniqueUrl);
    }

}
