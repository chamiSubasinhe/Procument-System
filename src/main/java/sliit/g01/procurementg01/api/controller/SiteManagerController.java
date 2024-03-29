package sliit.g01.procurementg01.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.service.impl.SiteManagerServiceImpl;

@RestController
public class SiteManagerController {

	@Autowired
	private SiteManagerServiceImpl siteManagerService;

	// add a new site manager as an employee to the system.
	// this is different from adding an existing manager to a site.
	@PostMapping("/employee/site-manager")
	public SiteManager addSiteManager(@Validated @RequestBody SiteManager siteManager) {
		siteManager.setEmployeeId("SM" + RandomStringUtils.randomNumeric(5));

		return siteManagerService.addSiteManager(siteManager);
	}

	// get the site manager whose managing the specified site.
	@GetMapping("/employee/site-manager/sites/{siteId}")
	public SiteManager getSiteManagerOfSite(@PathVariable String siteId) {
		return siteManagerService.getSiteManagerOfSite(siteId);
	}

	// get the site that the specified manager is managing.
	@GetMapping("/employee/site-manager/{managerId}/site")
	public String getManagedSiteId(@PathVariable String managerId) {
		return siteManagerService.getManagedSite(managerId);
	}

	// get all site managers
	// TODO: get only the available site managers.
	@GetMapping("/employee/site-manager")
	public List<SiteManager> getAllSiteManagers() {
		return siteManagerService.getAllSiteManagers();
	}

	// assign a free manager to a site that has no manager currently.
	// this is going to have a query parameter.
	// possible url:
	// <hostname>:<port>/employee/site-manager/assign?employeeId=<employeeId>&siteId=<siteId>
	// TODO: make sure to check if the specified person is already assigned to a
	// site or not.
	@PutMapping("/employee/site-manager/assign")
	public String assignManagerToSite(@RequestParam Map<String, String> query) {
		boolean status = false;

		if (query.containsKey("employeeId") && query.containsKey("siteId")) {
			String employeeId = query.get("employeeId");
			String siteId = query.get("siteId");
			// this is a double operation.
			// we must specify the site id in the site manager's dataset.
			// we must also specify the site manager's id in the site's dataset.
			// it's all taken care of assignSiteManager method in
			// SiteManagerservice.
			status = siteManagerService.assignSiteToSiteManager(siteId, employeeId);
		}

		return "Success:" + Boolean.toString(status);
	}

	/**
	 * Retrieve unassigned site manager list
	 * 
	 * @return
	 */
	@GetMapping("/employee/site-manager/unassign")
	public List<SiteManager> unassignedSiteManagers() {
		return siteManagerService.getUnAssignedSiteManagers();
	}
}
