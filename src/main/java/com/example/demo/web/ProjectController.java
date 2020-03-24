package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Project;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	private MapValidationErrorService errorservice;

	@PostMapping("/project")
	public ResponseEntity<?> addProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = errorservice.mapValidationService(result);
		if (errorMap != null)
			return errorMap;
		this.projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);

	}

	@GetMapping("/project/{projectIdentifier}")
	public ResponseEntity<?> getProject(@PathVariable String projectIdentifier) {
		Project project = this.projectService.findProjectByIdentifier(projectIdentifier);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@GetMapping("/project")
	public ResponseEntity<?> getProject() {
		Iterable<Project> project = this.projectService.finaAllProjects();
		return new ResponseEntity<Iterable<Project>>(project, HttpStatus.OK);
	}

	@DeleteMapping("/project/{projectIdentifier}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
		Long projectId = this.projectService.deleteProjectByProjectIdentifier(projectIdentifier);
		return new ResponseEntity<Long>(projectId, HttpStatus.OK);
	}
}
