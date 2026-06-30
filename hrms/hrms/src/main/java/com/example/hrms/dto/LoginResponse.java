    package com.example.hrms.dto;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;

    @Data
    @Builder
    @AllArgsConstructor
    public class LoginResponse {
        private String token;
        private String role;
        private Long employeeId;
        private String firstName;
    }
