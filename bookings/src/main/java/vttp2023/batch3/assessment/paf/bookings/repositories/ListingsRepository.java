package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {

	@Autowired
	MongoTemplate mTemplate;

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
		GroupOperation go = Aggregation.group("address.country");
		ProjectionOperation po = Aggregation.project("country");
		Aggregation aggregation = Aggregation.newAggregation(go, po);

		List<Document> countryDocumentList = mTemplate.aggregate(aggregation, "listings", Document.class).getMappedResults();

		return countryDocumentList;
	}
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
