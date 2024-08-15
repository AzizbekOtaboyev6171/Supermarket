package com.example.supermarket.service.impl;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.entity.Attachment;
import com.example.supermarket.exceptions.FileStorageException;
import com.example.supermarket.exceptions.ResourceNotFoundException;
import com.example.supermarket.mapper.AttachmentMapper;
import com.example.supermarket.repository.AttachmentRepository;
import com.example.supermarket.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public AttachmentDTO upload(MultipartFile file) {
        File folder = new File("uploads");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String fileName = UUID.randomUUID().toString();
        File fileToSave = new File(folder.getAbsolutePath() + "/" + fileName + getExtension(file.getOriginalFilename()));
        try {
            file.transferTo(fileToSave);
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setPath(fileToSave.getAbsolutePath());
            attachment.setContentType(file.getContentType());
            attachment.setExtension(getExtension(file.getOriginalFilename()));
            attachment.setSize(file.getSize());
            attachment = attachmentRepository.save(attachment);
            return attachmentMapper.toDTO(attachment);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", e);
        }
    }

    @Override
    public Resource download(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment not found"));
        File file = new File(attachment.getPath());
        if (!file.exists()) {
            throw new ResourceNotFoundException("Attachment not found");
        }
        return new FileSystemResource(file);
    }

    @Override
    public AttachmentDTO findById(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment not found"));
        return attachmentMapper.toDTO(attachment);
    }

    @Override
    public void delete(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment not found"));
        File file = new File(attachment.getPath());
        if (file.exists()) {
            file.delete();
        }
        attachmentRepository.delete(attachment);
    }

    private String getExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}