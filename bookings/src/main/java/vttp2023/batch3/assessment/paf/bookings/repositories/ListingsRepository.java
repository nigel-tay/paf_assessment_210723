package vttp2023.batch3.assessment.paf.bookings.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
	public void getCountriesFromMongo() {
		// continue with query and check if it returns properly


		System.out.println();
	}
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
