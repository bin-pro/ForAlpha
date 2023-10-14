package com.example.legendfive.overall.controller;

//import com.example.legendfive.overall.Service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

//    private final HomeService homeService;

//    @GetMapping("/trading-volumes")
//    public Response<List<HomeDto.volumeListDto>> tradingVolume(){
//        return Response.of(homeService.getTradingVolumes());
//    }
}
