package com.example.supermarket.dto.attachment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDTO {
    Long id;
    String name;
    String path;
    String contentType;
    String extension;
    Long size;
}