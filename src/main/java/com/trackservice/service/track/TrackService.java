package com.trackservice.service.track;

import com.trackservice.dto.track.TrackDto;
import com.trackservice.dto.track.TrackListResponseDto;
import com.trackservice.entity.track.Track;
import com.trackservice.entity.track.TrackLog;
import com.trackservice.repository.track.TrackLogRepository;
import com.trackservice.repository.track.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrackService {
    @Autowired
    TrackRepository trackRepository;
    @Autowired
    TrackLogRepository trackLogRepository;
    public void saveTrack(TrackDto trackDto) {
        Track track = Track.builder()
                .name(trackDto.getName())
                .customerName(trackDto.getCustomerName())
                .customerEmail(trackDto.getCustomerEmail())
                .description(trackDto.getDescription())
                .createdAt(new Date())
                .status("active")
                .build();
        trackRepository.save(track);
    }
    public TrackDto getTrackById(Long id) {
        Track track = trackRepository.findById(id).orElse(null);
        if (track == null) {
            return null;
        }
        TrackDto trackDto = TrackDto.builder()
                .name(track.getName())
                .id(track.getId())
                .customerName(track.getCustomerName())
                .customerEmail(track.getCustomerEmail())
                .description(track.getDescription())
                .status(track.getStatus())
                .build();
        return trackDto;
    }

    public TrackListResponseDto getTracksPage() {
        List<TrackDto> trackDtos = new ArrayList<>();
        List<Track> tracks = trackRepository.findAll();
        for (Track track : tracks) {
            TrackDto trackDto = TrackDto.builder()
                    .name(track.getName())
                    .id(track.getId())
                    .customerName(track.getCustomerName())
                    .customerEmail(track.getCustomerEmail())
                    .description(track.getDescription())
                    .status(track.getStatus())
                    .build();
            trackDtos.add(trackDto);
        }
        return TrackListResponseDto.builder()
                .tracks(trackDtos)
                .build();
    }
    public void saveTrackLog(TrackLog trackLog) {
        trackLogRepository.save(trackLog);
    }
}
