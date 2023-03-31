package com.smartlabs.projecttender.repositories;

import com.smartlabs.projecttender.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {


}
