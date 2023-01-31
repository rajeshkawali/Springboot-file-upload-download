package com.rajeshkawali.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajeshkawali.entity.ImageDetails;

/**
 * @author Rajesh_Kawali
 *
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageDetails, Long> {

	Optional<ImageDetails> findByName(String fileName);
}