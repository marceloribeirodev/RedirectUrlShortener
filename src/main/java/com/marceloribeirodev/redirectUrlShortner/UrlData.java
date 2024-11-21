package com.marceloribeirodev.redirectUrlShortner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UrlData {
    private String originalUrl;
    private long expirationTime;

}
