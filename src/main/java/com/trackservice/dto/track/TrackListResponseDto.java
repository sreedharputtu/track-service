package com.trackservice.dto.track;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackListResponseDto {
    List<TrackDto> tracks;
    MetadataDto metadata;
}

class MetadataDto {
    private int page;
    private int nextPage;
    private int prevPage;
    private String next;
}
