package com.rajeshkawali.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajeshkawali.entity.FileDetails;

/**
 * @author Rajesh_Kawali
 *
 */
@Repository
public interface FileRepository extends JpaRepository<FileDetails, Integer> {
	Optional<FileDetails> findByName(String fileName);
}
