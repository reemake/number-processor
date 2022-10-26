package ru.relex.numberprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.numberprocessor.dto.FileDTO;
import ru.relex.numberprocessor.service.FileService;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    @Value("${file.upload-dir}")
    private String FILE_DIRECTORY;

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFileBinary")
    public ResponseEntity<Object> uploadFileBinary(@RequestPart MultipartFile file) {
            return fileService.uploadFileBinary(file, FILE_DIRECTORY);
    }

    @PostMapping("/uploadFileJson")
    public ResponseEntity<Object> uploadFileJson(@RequestBody FileDTO fileDTO) {
        return fileService.uploadFileJson(fileDTO.getFilepath());
    }

    @GetMapping("/get_max_value")
    public FileDTO getMaxValue() {
        return fileService.getMaxValue();
    }

    @GetMapping("/get_min_value")
    public FileDTO getMinValue() {
        return fileService.getMinValue();
    }

    @GetMapping("/get_median_value")
    public FileDTO getMedianValue() {
        return fileService.getMedianValue();
    }

    @GetMapping("/get_avg_value")
    public FileDTO getAverageValue() {
        return fileService.getAverageValue();
    }

    @GetMapping("/get_max_asc_seq")
    public FileDTO getMaxAscSequence() {
        return fileService.getMaxAscSequence();
    }

    @GetMapping("/get_max_desc_seq")
    public FileDTO getMaxDescSequence() {
        return fileService.getMaxDescSequence();
    }

    @GetMapping("/get_stated_operation")
    public FileDTO getStatedOperation(@RequestBody FileDTO fileDTO) {
        return fileService.getStatedOperation(fileDTO.getOperation());
    }
}
