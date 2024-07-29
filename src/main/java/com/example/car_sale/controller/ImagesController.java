package com.example.car_sale.controller;

import com.example.car_sale.dto.ImagesDto;
import com.example.car_sale.payload.ImagesPayload;
import com.example.car_sale.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImagesController {
    private final ImagesService imagesService;

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam(value = "carId", required = false) Long carId) {
        if (files.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Files must not be empty");
        }

        ImagesPayload payload = new ImagesPayload();
        payload.setFiles(files);
        payload.setCarId(carId);

        List<ImagesDto> imageDtos = imagesService.uploadImages(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(imageDtos);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        byte[] imageContent = imagesService.getImageContent(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageContent.length);

        ByteArrayResource resource = new ByteArrayResource(imageContent);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}

