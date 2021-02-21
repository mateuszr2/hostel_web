package com.jsfproject.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import org.primefaces.model.SortOrder;

import com.jsf.dao.LogDAO;
import jpa_entities.ActionLog;

	@Named
	@ViewScoped
	public class LogListBB extends LazyDataModel<ActionLog> implements Serializable {
		private static final long serialVersionUID = 1L;
		private LazyDataModel<ActionLog> model;
	

		@Inject
		LogDAO logDAO;
		
		@PostConstruct
	    public void init() {
	        
	        model = new LazyDataModel<ActionLog>() {
				@Override
			    public List<ActionLog> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filterMeta){
			        List<ActionLog> dataa = new ArrayList<ActionLog>();
			        dataa = logDAO.getLazyLogs(first, pageSize);
			 
			        //rowCount - could be outside the load function
			        int dataSize = logDAO.countLogs();
			        this.setRowCount(dataSize);
			        
			        return dataa;
				}
			};
	    }
		
		
	    public LazyDataModel<ActionLog> getModel() {
	        return model;
	    }

	


	

}
