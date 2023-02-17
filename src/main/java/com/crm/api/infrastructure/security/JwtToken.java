package com.crm.api.infrastructure.security;

import com.crm.application.ApplicationToken;

public record JwtToken( String token, String refreshToken) implements ApplicationToken {
}
