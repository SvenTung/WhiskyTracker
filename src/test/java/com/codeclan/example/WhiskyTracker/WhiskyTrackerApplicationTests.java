package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskyByYear(){
		List<Whisky> whiskies = whiskyRepository.findByYear(2018);
		assertEquals(2, whiskies.size());
	}

	@Test
	public void canGetAllDistilleriesByRegion(){
		List<Distillery> distilleries = distilleryRepository.findByRegionIgnoreCase("speyside");
		assertEquals(2, distilleries.size());
	}

	@Test
	public void canFindWhiskiesByAgeAndDistillery(){
		List<Whisky> whiskies = whiskyRepository.findByAgeAndDistilleryName(15, "Glendronach");
		assertEquals(1, whiskies.size());
	}

	@Test
	public void canFindWhiskyByDistilleryRegion(){
		List<Whisky> whiskies = whiskyRepository.findByDistilleryRegionIgnoreCase("highland");
		assertEquals(2, whiskies.size());
	}

	@Test
	public void canGetAllDistilleriesByWhiskyAge(){
		List<Distillery> distilleries = distilleryRepository.findByWhiskiesAge(12);
		assertEquals(2, distilleries.size());
	}



}
