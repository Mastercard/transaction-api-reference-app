package com.mastercard.developer.config;

import com.mastercard.developer.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Getter
@Setter
@ConfigurationProperties(prefix = "mastercard.api")
public class MastercardProperties {
    private String format;

    private String basePath;

    private String keystorePassword;
    private String keyFile;

    @PostConstruct
    public void initialize() throws ServiceException {
        if (null == keyFile) {
            throw new ServiceException("Key file does not exist, please add details in application.properties");
        }
    }

}
