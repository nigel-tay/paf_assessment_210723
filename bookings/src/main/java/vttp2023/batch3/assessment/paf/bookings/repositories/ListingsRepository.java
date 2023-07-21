package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;
import java.util.Optional;

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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.models.BookingForm;
import vttp2023.batch3.assessment.paf.bookings.models.Vacancy;

@Repository
public class ListingsRepository {

	@Autowired
	MongoTemplate mTemplate;

	@Autowired
	JdbcTemplate jTemplate;

	private final String C_LISTINGS = "listings";
	private final String A_ADDRESS_COUNTRY = "address.country";
	private final String A_ACCOMODATES = "accommodates";
	private final String A_ADDRESS = "address.street";
	private final String A_ADDRESS_SUBURB = "address.suburb";
	private final String A_AMENITITES = "amenities";
	private final String A_COUNTRY = "country";
	private final String A_DESCRIPTION = "description";
	private final String A_ID = "_id";
	private final String A_IMAGE = "images.picture_url";
	private final String A_PRICE = "price";

	private final String GET_VACANCY_SQL = "SELECT vacancy FROM acc_occupancy WHERE acc_id = ?";

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
		
		return listingDocuments;
	}

	//TODO: Task 4
	/**
	 * db.getCollection('listings').aggregate(
		[
			{ $match: { _id: '12740176' } },
			{
			$project: {
				_id: 1,
				description: 1,
				'address.street': 1,
				'address.suburb': 1,
				'address.country': 1,
				'images.picture_url': 1,
				price: 1,
				amenities: 1
			}
			}
		]
		);
	 */
	public List<Document> getDetails(String id) {
		Criteria criteria = Criteria.where(A_ID).is(id);
		MatchOperation mo = Aggregation.match(criteria);
		ProjectionOperation po = Aggregation.project(
											A_ID,
											A_DESCRIPTION,
											A_ADDRESS,
											A_ADDRESS_SUBURB,
											A_ADDRESS_COUNTRY,
											A_IMAGE,
											A_PRICE,
											A_AMENITITES);
		Aggregation aggregation = Aggregation.newAggregation(mo, po);
		List<Document> detailsList = mTemplate.aggregate(aggregation, C_LISTINGS, Document.class).getMappedResults();

		return detailsList;
	}

	//TODO: Task 5
	public Boolean checkVacancy(Integer stay, String id) {
		// List<String> resultList = jTemplate.query(GET_VACANCY_SQL, BeanPropertyRowMapper.newInstance(String.class), id);
		System.out.println(id);
		List<Vacancy> vacancyList = jTemplate.query(GET_VACANCY_SQL, BeanPropertyRowMapper.newInstance(Vacancy.class), Integer.parseInt(id));
		Integer vacancy = vacancyList.get(0).getVacancy();
		System.out.println("HERE>>>>>>>>>>>>>>>>>>>>"+vacancy);
		
		return false;
	}

}
