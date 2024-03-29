package sliit.g01.procurementg01.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Policy;
import sliit.g01.procurementg01.api.service.impl.PolicyServiceImpl;


@RestController
public class PolicyController {

	@Autowired
	private PolicyServiceImpl policyService;

	// add new policies.
	@PostMapping("/policy")
	public Policy addPolicy(@RequestBody @Validated Policy policy) {
		policy.setPolicyId("PC" + RandomStringUtils.randomNumeric(5));

		System.out.println(policy.getDescription());
		return policyService.addPolicy(policy);
	}

	// view all company policies.
	@GetMapping("/policy/all")
	public List<Policy> viewAllPolicies() {
		return policyService.getAllPolicies();
	}

	// get a specific policies by id.
	@GetMapping("/policy/{policyId}")
	public Policy viewPolicy(@PathVariable String policyId) {
		return policyService.viewPolicy(policyId);
	}

	// get policies(s) that has the provided keyword in the description.
	// this is going to have a query parameter.
	// possible url: <hostname>:<port>/policies?description=<keyword>
	@GetMapping("/policy")
	public List<Policy> viewPoliciesWithDescription(@RequestParam Map<String, String> query) {
		if (query.containsKey("description")) {
			String keyword = query.get("description");
			return policyService.getPoliciesWithDescription(keyword);
		}

		return new ArrayList<Policy>();
	}

	// delete a policies.
	@DeleteMapping("policy/{policyId}")
	public boolean deletePolicy(@PathVariable String policyId) {
		return policyService.deletePolicy(policyId);
	}

}
