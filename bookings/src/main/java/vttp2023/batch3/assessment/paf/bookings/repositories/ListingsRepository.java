package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {

	@Autowired
	MongoTemplate mTemplate;

	private final String C_LISTINGS = "listings";
	private final String A_ADDRESS_COUNTRY = "address.country";
	private final String A_ACCOMODATES = "accommodates";
	private final String A_ADDRESS = "address.street";
	private final String A_COUNTRY = "country";
	private final String A_ID = "_id";
	private final String A_IMAGE = "images.picture_url";
	private final String A_NAME = "name";
	private final String A_PRICE = "price";

	//TODO: Task 2
	/**
	 * db.getCollection('listings').aggregate(
		[
			{
			$group: {
				_id: '$address.country',
				country: { $first: '$address.country' }
			}
			},
			{ $project: { _id: 0, country: 1 } }
		]
		);
	 */
	public List<Document> getCountriesFromMongo() {
		// continue with query and check if it returns properly
		GroupOperation go = Aggregation.group(A_ADDRESS_COUNTRY);
		ProjectionOperation po = Aggregation.project(A_COUNTRY);
		Aggregation aggregation = Aggregation.newAggregation(go, po);

		List<Document> countryDocumentList = mTemplate.aggregate(aggregation, C_LISTINGS, Document.class).getMappedResults();

		return countryDocumentList;
	}
	
	//TODO: Task 3
	/**
	 * db.getCollection('listings').aggregate(
		[
			{
			$match: {
				'address.country': {
				$regex: RegExp('australia', 'i')
				},
				accommodates: 10,
				price: { $gte: 10, $lte: 2000 }
			}
			},
			{ $sort: { price: -1 } },
			{
			$project: {
				_id: 1,
				address: '$address.street',
				price: 1,
				image: '$images.picture_url'
			}
			}
		]
		);
	 */
	public List<Document> getListingsFromMongo(String country, Integer numberOfPerson, Double min, Double max) {
		Criteria criteria = Criteria.where(A_ADDRESS_COUNTRY).regex(country, "i")
									.and(A_ACCOMODATES).is(numberOfPerson)
									.and(A_PRICE).gte(min).lte(max);
		MatchOperation mo = Aggregation.match(criteria);
		SortOperation so = Aggregation.sort(Direction.DESC, A_PRICE);
		ProjectionOperation po = Aggregation.project(A_ID, A_ADDRESS, A_PRICE, A_IMAGE);
		Aggregation aggregation = Aggregation.newAggregation(mo, so, po);
		List<Document> listingDocuments = mTemplate.aggregate(aggregation, C_LISTINGS, Document.class).getMappedResults();
		
		System.out.println(listingDocuments.toString());
		return listingDocuments;
	}

	//TODO: Task 4
	

	//TODO: Task 5


}
