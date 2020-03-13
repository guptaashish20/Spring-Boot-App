package com.ashish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.ashish.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal,Long> {

}
