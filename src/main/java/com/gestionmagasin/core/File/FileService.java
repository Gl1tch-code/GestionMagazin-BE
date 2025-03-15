package com.gestionmagasin.core.File;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setFiledata(file.getBytes());
        fileEntity.setContentType(file.getContentType());
        return fileRepository.save(fileEntity);
    }

    public void deleteFile(Long id) {
        Optional<FileEntity> file = fileRepository.findById(id);
        file.ifPresent(fileEntity -> fileRepository.delete(fileEntity));
    }
}
