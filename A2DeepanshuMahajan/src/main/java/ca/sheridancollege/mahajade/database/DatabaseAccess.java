package ca.sheridancollege.mahajade.database;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mahajade.beans.Review;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public ArrayList<Review> getReviews() {
		String query = "SELECT name, review, reviewDate, reviewTime from reviews";
		ArrayList<Review> reviews = (ArrayList<Review>) jdbc.query(query,
				new BeanPropertyRowMapper<Review>(Review.class));
		return reviews;
	}

	public void addReview(Review review) {
		String query = "INSERT into Reviews(name, review, reviewDate, reviewTime) values(:name, :review, :reviewDate, :reviewTime)";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", review.getName());
		map.put("review", review.getReview());
		map.put("reviewDate", review.getReviewDate());
		map.put("reviewTime", review.getReviewTime());
		jdbc.update(query, map);
	}

	public void deleteReview(String name) {
		String query = "Delete from Reviews where name = :name";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		jdbc.update(query, map);
	}

	public void updateReview(Review review) {
		String query = "Update Reviews Set review = :review, reviewDate = :reviewDate, reviewTime = :reviewTime where name = :name";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("review", review.getReview());
		map.put("name", review.getName());
		map.put("reviewDate", review.getReviewDate());
		map.put("reviewTime", review.getReviewTime());
		jdbc.update(query, map);
	}

	public Review getReview(String name) {
		String query = "Select name, review, reviewDate, reviewTime from Reviews where name=:name";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		ArrayList<Review> reviews = new ArrayList<Review>();
		List<Map<String, Object>> list = jdbc.queryForList(query, map);
		for (Map<String, Object> a : list) {
			Date date = (Date) a.get("reviewDate");
			Time time = (Time) a.get("reviewTime");
			reviews.add(new Review((String) a.get("name"), (String) a.get("review"), date.toLocalDate(),
					time.toLocalTime()));
		}
		return reviews.get(0);
	}
}
