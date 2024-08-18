package com.example.supermarket.dto.attachment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "AttachmentDTO", description = "Data Transfer Object (DTO) used to represent details of an attachment associated with a supplier. This includes information such as the attachment's name, path, content type, and size. It is used to show the metadata and status of the uploaded file.")
public class AttachmentDTO {
    Long id;
    String name;
    String path;
    String contentType;
    String extension;
    Long size;
}