package com.example.web_login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.web_login.entity.Serevice_add;


@Repository
public interface Admin_repo extends JpaRepository<Serevice_add, Long> {
    int countByServiceId(Long serviceId);
}
