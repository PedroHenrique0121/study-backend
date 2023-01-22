package com.pedro.study.config;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize(value = "permitAll()")
public @interface PermitAll {
}
