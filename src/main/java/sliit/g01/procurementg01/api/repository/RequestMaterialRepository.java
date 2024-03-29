package sliit.g01.procurementg01.api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.RequestMaterial;

public interface RequestMaterialRepository extends MongoRepository<RequestMaterial, String> {
	RequestMaterial findByRequestId(String requestId);

	List<RequestMaterial> findByIsSiteManagerApproved(String isSiteManagerApproved);

	List<RequestMaterial> findByIsImmediated(String isImmediated);

	List<RequestMaterial> findByisSiteManagerApproved(String isSiteManagerApproved);

	
	// RequestMaterial findOne(String orderId);

	// void updateRequest();
}
