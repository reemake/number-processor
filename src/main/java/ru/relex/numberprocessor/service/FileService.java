package ru.relex.numberprocessor.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.numberprocessor.dto.FileDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class FileService {

    private static List<Long> numbers;

    public void readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        Stream<Long> lineStream = Files
                .newBufferedReader(path)
                .lines()
                .mapToLong(Long::parseLong)
                .boxed();
        numbers = lineStream.collect(toList());
    }
    public ResponseEntity<Object> uploadFileBinary(MultipartFile file, String filepath) {
        try {
            File convertFile = new File(filepath + file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            readFile(filepath + file.getOriginalFilename());
            return new ResponseEntity<Object>("Файл успешно загружен и прочитан", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Object>("Ошибка при загрузке файла", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> uploadFileJson(String filepath) {
        try {
            readFile(filepath);
            return new ResponseEntity<Object>("Файл успешно прочитан", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Object>("Ошибка при чтении файла", HttpStatus.NOT_FOUND);
        }
    }

    public FileDTO getMaxValue() {
        Long maxValue = numbers
                .stream()
                .reduce(Long::max)
                .get();
        FileDTO fileDTO = new FileDTO();
        fileDTO.setMaxValue(maxValue);
        return fileDTO;
    }

    public FileDTO getMinValue() {
        Long minValue = numbers
                .stream()
                .reduce(Long::min)
                .get();
        FileDTO fileDTO = new FileDTO();
        fileDTO.setMinValue(minValue);
        return fileDTO;
    }

    public FileDTO getMedianValue() {
        Double medianValue;
        FileDTO fileDTO = new FileDTO();
        Collections.sort(numbers);
        if (numbers.size() % 2 == 0)
            medianValue = (numbers.get(numbers.size() / 2) + numbers.get(numbers.size() / 2 + 1) / 2D);
        else
            medianValue = numbers.get(numbers.size() / 2).doubleValue();
        fileDTO.setMedian(medianValue);
        return fileDTO;
    }

    public FileDTO getAverageValue() {
        Double averageValue = numbers
                .stream()
                .mapToLong(e -> e)
                .average()
                .getAsDouble();
        FileDTO fileDTO = new FileDTO();
        fileDTO.setAverage(averageValue);
        return fileDTO;
    }

    public List<List<Long>> getMaxSequence(boolean isAsc) {
        List<Long> seq = new ArrayList<>();
        List<List<Long>> seqList = new ArrayList<>();
        List<Long> numbersCopy = new ArrayList<>(numbers);

        Long prev = numbersCopy.get(0);
        seq.add(prev);
        numbersCopy.remove(0);

        for (Long num: numbersCopy) {
            if (num > prev && isAsc || num < prev && !isAsc) {
                seq.add(num);
            }
            else {
                seq.clear();
                seq.add(num);
            }

            seqList.add(new ArrayList<>(seq));
            prev = num;
        }

        int maxSize = seqList
                .stream()
                .mapToInt(List::size)
                .max()
                .getAsInt();

        return seqList.stream()
                .filter(s -> s.size() == maxSize)
                .collect(Collectors.toList());
    }

    public FileDTO getMaxAscSequence() {
        FileDTO fileDTO = new FileDTO();
        List<List<Long>> maxAscSequence = getMaxSequence(true);
        fileDTO.setAscendingSequence(maxAscSequence);
        return fileDTO;
    }

    public FileDTO getMaxDescSequence() {
        FileDTO fileDTO = new FileDTO();
        List<List<Long>> maxDescSequence = getMaxSequence(false);
        fileDTO.setDescendingSequence(maxDescSequence);
        return fileDTO;
    }

    public FileDTO getStatedOperation(String operation) {
        FileDTO response = new FileDTO();

        switch (operation) {
            case ("get_max_value"):
                response = getMaxValue();
                break;
            case ("get_min_value"):
                response = getMinValue();
                break;
            case ("get_median_value"):
                response = getMedianValue();
                break;
            case ("get_avg_value"):
                response = getAverageValue();
                break;
            case ("get_max_asc_seq"):
                response = getMaxAscSequence();
                break;
            case ("get_max_desc_seq"):
                response = getMaxDescSequence();
                break;
        }
        return response;
    }

}
