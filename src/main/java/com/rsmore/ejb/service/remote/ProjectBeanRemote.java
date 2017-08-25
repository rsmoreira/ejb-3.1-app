package com.rsmore.ejb.service.remote;

import java.util.List;

import javax.ejb.Remote;

import com.rsmore.ejb.entities.Project;

@Remote
public interface ProjectBeanRemote {

	void saveProject(Project project);

	Project findProject(Project project);

	List<Project> retrieveAllProjects();

}
