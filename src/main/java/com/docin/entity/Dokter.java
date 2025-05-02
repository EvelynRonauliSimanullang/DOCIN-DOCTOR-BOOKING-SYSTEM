package com.docin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "dokter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dokter implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;        // Nama lengkap dokter
    private String spesialisasi;    
    private String phoneNumber;     // No HP
    private String email;           // Email

    @Column(unique = true, nullable = false)
    private String username;        // Username untuk login

    @Column(nullable = false)
    private String password;        //Password untuk login

    //  Implementasi UserDetails 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // belum pakai ROLE
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
