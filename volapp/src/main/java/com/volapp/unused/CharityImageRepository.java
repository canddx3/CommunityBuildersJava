package com.volapp.unused;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityImageRepository extends JpaRepository<CharityImage, String> {

}
