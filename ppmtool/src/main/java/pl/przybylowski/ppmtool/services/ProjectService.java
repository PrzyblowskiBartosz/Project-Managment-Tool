package pl.przybylowski.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.przybylowski.ppmtool.domain.Project;
import pl.przybylowski.ppmtool.exceptions.ProjectIdException;
import pl.przybylowski.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier() + "' already exist.");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null)
            throw new ProjectIdException("Project ID '" + projectId +"' doesn't exist.");

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdencifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null)
            throw new ProjectIdException("Cannot Project with ID '" +projectId+ "' This project doesnt exis. ");

        projectRepository.delete(project);
    }
}
