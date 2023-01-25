package com.sombra.promotion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String modifiedBy;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String createdBy;
}
