package com.organic.certification.inspection_checklist.controller;

import com.organic.certification.common.config.ApiConfig;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CheckListController.PATH)
@AllArgsConstructor
public class CheckListController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "checklists";
}
