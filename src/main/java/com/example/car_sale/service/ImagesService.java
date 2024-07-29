package com.example.car_sale.service;

import com.example.car_sale.dto.ImagesDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.entity.Images;
import com.example.car_sale.exception.NotFoundException;
import com.example.car_sale.mapper.ImagesMapper;
import com.example.car_sale.payload.ImagesPayload;
import com.example.car_sale.repository.CarRepository;
import com.example.car_sale.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagesService {
    private final ImagesRepository imagesRepository;
    private final CarRepository carRepository;
    private final ImagesMapper imagesMapper;

    public List<ImagesDto> uploadImages(ImagesPayload payload) {
        List<MultipartFile> files = payload.getFiles();
        Long carId = payload.getCarId();

        List<ImagesDto> imagesDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();

            // Save the file to a local directory or cloud storage
            String filePath = saveFile(file);

            Images image = new Images();
            image.setFileName(fileName);
            image.setFilePath(filePath);
            image.setFileType(fileType);

            // Associate image with car if carId is provided
            if (carId != null) {
                Car car = carRepository.findById(carId)
                        .orElseThrow(() -> new RuntimeException("Car not found"));
                image.setCar(car);
            }

            Images savedImage = imagesRepository.save(image);
            imagesDtoList.add(imagesMapper.toDto(savedImage));
        }

        return imagesDtoList;
    }

    private String saveFile(MultipartFile file) {
        String folder = "images/";

        try {
            // Ensure the directory exists
            Path folderPath = Paths.get(folder);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            byte[] bytes = file.getBytes();
            Path path = folderPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public byte[] getImageContent(Long id) {
        Images image = imagesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found"));

        try {
            Path path = Paths.get(image.getFilePath());
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to read image file", e);
        }
    }

}