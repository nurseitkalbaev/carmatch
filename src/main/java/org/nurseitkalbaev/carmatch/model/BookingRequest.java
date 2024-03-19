package org.nurseitkalbaev.carmatch.model;

import lombok.Data;

import java.util.Date;

@Data
public class BookingRequest {
    private Long userId;
    private Long carId;
    private Date startDate;
    private Date endDate;

}
