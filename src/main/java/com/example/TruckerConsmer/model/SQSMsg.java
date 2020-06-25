package com.example.TruckerConsmer.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SQSMsg {
    @JsonProperty("Type")
    private  String type;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("MessageId")
    private String messageId;
}