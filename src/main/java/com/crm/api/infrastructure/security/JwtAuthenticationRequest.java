package com.crm.api.infrastructure.security;

import java.io.Serializable;

public record JwtAuthenticationRequest(String username, String password) implements Serializable  {
}
