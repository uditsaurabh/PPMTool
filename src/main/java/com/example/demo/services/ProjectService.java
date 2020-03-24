package com.example.demo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Project;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toLowerCase());
			return this.projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project id");

		}
	}

	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = this.projectRepository.findByProjectIdentifier(projectIdentifier);
		if (project == null) {
			throw new ProjectIdException("Project id");
		}
		return project;
	}

	public Iterable<Project> finaAllProjects() {
		return this.projectRepository.findAll();
	}

	@Transactional
	public Long deleteProjectByProjectIdentifier(String projectIdentifier) {
		return this.projectRepository.deleteProjectByProjectIdentifier(projectIdentifier);
	}

}
