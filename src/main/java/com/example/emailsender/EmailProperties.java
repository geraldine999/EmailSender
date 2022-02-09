package com.example.emailsender;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Validated
public class EmailProperties {

    @NotNull
    @NotBlank
    private String mailTo;
}
