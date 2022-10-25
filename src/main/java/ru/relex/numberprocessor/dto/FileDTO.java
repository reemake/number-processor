package ru.relex.numberprocessor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    @JsonProperty("file_path")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filepath;
    @JsonProperty("operation")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String operation;
    @JsonProperty("max_value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long maxValue;
    @JsonProperty("min_value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long minValue;
    @JsonProperty("median_value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double median;
    @JsonProperty("avg_value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double average;
    @JsonProperty("max_asc_seq")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<List<Long>> ascendingSequence;
    @JsonProperty("max_desc_seq")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<List<Long>> descendingSequence;
}
