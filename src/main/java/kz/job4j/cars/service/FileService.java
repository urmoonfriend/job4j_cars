package kz.job4j.cars.service;

import kz.job4j.cars.models.dto.FileDto;
import kz.job4j.cars.models.entity.Photo;

import java.util.Optional;

public interface FileService {
    Photo save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    void deleteById(int id);
}
