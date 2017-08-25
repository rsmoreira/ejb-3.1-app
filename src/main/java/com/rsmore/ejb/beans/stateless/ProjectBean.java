package com.rsmore.ejb.beans.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsmore.ejb.entities.Project;
import com.rsmore.ejb.service.remote.ProjectBeanRemote;

/**
 * Session Bean implementation class ProjectBean
 */
@Stateless
public class ProjectBean implements ProjectBeanRemote {

	@PersistenceContext(unitName = "JPADB")
	private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectBean.class);
	
	public ProjectBean() {
	}

	@Override
	public void saveProject(Project project) {
		entityManager.persist(project);

	}

	@Override
	@TransactionAttribute
	public Project findProject(Project project) {
		Project proj = entityManager.find(Project.class, project.getPnumber());

		return proj;
	}

	@Override
	public List<Project> retrieveAllProjects() {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT p FROM ");
		sb.append(Project.class.getName());
		sb.append(" p");

		Query query = entityManager.createQuery(sb.toString());
		List<Project> projects = query.getResultList();

		return projects;
	}

}
