package com.trackservice.controller.track;

import com.trackservice.dto.track.TrackDto;
import com.trackservice.dto.track.TrackListResponseDto;
import com.trackservice.dto.track.TrackLogDto;
import com.trackservice.service.track.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TrackService trackService;

    @GetMapping("/page/create")
    public String getCreateTrackPage() {
        return "html/create_track.html";
    }

    @GetMapping("/page/list")
    public String getTrackListPage(Model model){
        TrackListResponseDto response = trackService.getTracksPage();
        log.info("getTrackListPage , response: {}", response);
        model.addAttribute("response", response);
        return "html/track_list.html";
    }


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String saveTrack(@RequestParam Map<String,String> body , Model model) {
        TrackDto dto = modelMapper.map(body, TrackDto.class);
        log.info("save track , TrackDto: {}", dto);
        trackService.saveTrack(dto);
        model.addAttribute("msg","Track created successfully");
        return "html/success.html";
    }
    @GetMapping("/{id}")
    public String getTrackById(@PathVariable Long id , Model model) {
        TrackDto trackDto = trackService.getTrackById(id);
        model.addAttribute("track",trackDto);
        return "html/track_details.html";
    }

    @PostMapping("/{id}/logs")
    public void saveTrackLog(@RequestBody TrackLogDto trackLogDto){

    }
}
