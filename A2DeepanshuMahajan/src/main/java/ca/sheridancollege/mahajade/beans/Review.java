package ca.sheridancollege.mahajade.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String review;
	private @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate reviewDate = LocalDate.now();
	private @DateTimeFormat(pattern="HH:mm") LocalTime reviewTime = LocalTime.now();
	
}
