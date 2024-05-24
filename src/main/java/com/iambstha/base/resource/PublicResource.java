package com.iambstha.base.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/public")
public class PublicResource {

    @GetMapping
    public String getAdmin() {
        return "Hello from public.";
    }

}
