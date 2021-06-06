package com.ecs.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OfferDto {

    private String title;
    private Integer duration;
    private Date startDate;
    private Date endDate;
    private String description;
    private Integer pay;
    private Boolean status;
    private String field;
    private Long companyId;
}
