package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Rating;


public interface RatingService {

	boolean addRatings(Rating rating);

	List<Rating> getConstructorRatings();

	List<Rating> getSupplierRatings();

}
