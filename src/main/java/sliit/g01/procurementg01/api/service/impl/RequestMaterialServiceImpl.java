package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.repository.RequestMaterialRepository;
import sliit.g01.procurementg01.api.service.RequestMaterialService;;

@Service("requestmaterialService")
public class RequestMaterialServiceImpl implements RequestMaterialService {

	@Autowired
	private PurchaseOrderServiceImpl purchaseOrderService;

	@Autowired
	private RequestMaterialRepository requestmaterialRepository;

	@Override
	public Boolean addOrder(RequestMaterial requestmaterial) {
		return (requestmaterialRepository.save(requestmaterial) != null);

	}

	@Override
	public List<RequestMaterial> getAllrequests() {
		return requestmaterialRepository.findAll();

	}

	@Override
	public RequestMaterial getRequest(String requestId) {
		return requestmaterialRepository.findByRequestId(requestId);
	}

	@Override
	public Boolean deleteRequest(String requestId) {
		requestmaterialRepository.delete(requestmaterialRepository.findByRequestId(requestId));
		return true;
	}

	@Override
	public Boolean updateRequest(String requestId, RequestMaterial requestMaterial) {
		RequestMaterial req = requestmaterialRepository.findByRequestId(requestId);

		System.out.println(requestMaterial.getNotifyManagement());

		if (requestMaterial.getIsProcumentApproved() != null)
			req.setIsProcumentApproved(requestMaterial.getIsProcumentApproved());

		if (requestMaterial.getIsProcumentRejected() != null)
			req.setIsProcumentRejected(requestMaterial.getIsProcumentRejected());

		if (requestMaterial.getNotifyManagement() != null)
			req.setNotifyManagement(requestMaterial.getNotifyManagement());

		if (requestMaterial.getIsSiteManagerApproved() != null)
			req.setIsSiteManagerApproved(requestMaterial.getIsSiteManagerApproved());

		if (requestMaterial.getRequestedDate() != null)
			req.setRequestedDate(requestMaterial.getRequestedDate());

		if (requestMaterial.getIsImmediated() != null)
			req.setIsImmediated(requestMaterial.getIsImmediated());

		if (requestMaterial.getItems() == null)
			requestMaterial.setItems(req.getItems());

		// if the material request is updated, we can go ahead and create the
		// purchase orders.
		if ((requestMaterial.getIsProcumentApproved() != null) && requestMaterial.getIsProcumentApproved()) {
			System.out.println("Creating orders...");
			List<PurchaseOrder> ordersForSuppliers = purchaseOrderService.createOrder(req);
			// save to db so the suppliers can see them.
			purchaseOrderService.addPurchaseOrders(ordersForSuppliers);
		}

		return (requestmaterialRepository.save(req) != null);
	}

	@Override
	public List<RequestMaterial> getRequestsByStatus(String isSiteManagerApproved) {
		return requestmaterialRepository.findByIsSiteManagerApproved(isSiteManagerApproved);
	}

	@Override
	public List<RequestMaterial> getRequestsByImmediated(String isImmediated) {
		return requestmaterialRepository.findByIsImmediated(isImmediated);
	}

	@Override
	public List<RequestMaterial> getSiteMnagerApprovedRequests() {
		return requestmaterialRepository.findByisSiteManagerApproved("1");
	}

	@Override
	public Boolean setProcumentStaffApproved(String requestId, Boolean isProcumentApproved) {
		RequestMaterial req = requestmaterialRepository.findByRequestId(requestId);
		req.setIsProcumentApproved(isProcumentApproved);

		return (requestmaterialRepository.save(req) != null);
	}

	@Override
	public boolean addItem(RequestMaterial requestMaterial) {
		// TODO Auto-generated method stub
		return false;
	}

}
