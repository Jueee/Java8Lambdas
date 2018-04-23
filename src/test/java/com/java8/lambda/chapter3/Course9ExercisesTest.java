package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)		//按方法名字母顺序执行
class Course9ExercisesTest {

	Course9Exercises test = new Course9Exercises();
	
	@Test
	void exercises1a() {
		List<Stream<Integer>> numbersList = new ArrayList<>();
		numbersList.add(Arrays.asList(1,2,3).stream());
		numbersList.add(Arrays.asList(12,24,32).stream());
		numbersList.add(Arrays.asList(1,2,3,4).stream());
		numbersList.forEach( numbers-> System.out.println(test.exercises1a(numbers)));
	}
	
	@Test
	void exercises1b() {
		List<Artist> artistList = SampleData.getThreeArtists();
		System.out.println(test.exercises1b(artistList));
	}
	
	@Test
	void exercises1c() {
		List<Album> albums = Arrays.asList(SampleData.manyTrackAlbum, SampleData.sampleShortAlbum, SampleData.aLoveSupreme);
		System.out.println(test.exercises1c(albums));
	}
	
	@Test
	void exercises2a() {
		List<Artist> artists = SampleData.getThreeArtists();
		System.out.println("[totalMembers]" + test.exercises2example(artists));
		System.out.println("[totalMembers]" + test.exercises2(artists));
	}
	
	@Test
	void exercises7a() {
		System.out.println(test.exercises7("asd77asdSDSD"));
		System.out.println(test.exercises7(""));
	}
	
	@Test
	void exercises8a() {
		System.out.println(test.exercises8(Arrays.asList("a", "abc", "ABCde")));
		System.out.println(test.exercises8(Arrays.asList()));
	}
	
	private <I,O> void assertMapped(Function<I, O> mapper, List<I> input, List<O> expectedOutput) {
		List<O> output = test.mapUsingReduce(input.stream(), mapper);
		assertEquals(expectedOutput, output);
		System.out.println("[expectedOutput]"+expectedOutput+"[output]"+output);
		 
		List<O> paralleOutPut = test.mapUsingReduce(input.parallelStream(), mapper);
		assertEquals(expectedOutput, paralleOutPut);
		System.out.println("[expectedOutput]"+expectedOutput+"[paralleOutPut]"+paralleOutPut);
	}

	@Test
	public void mapUsingReduce() {
		System.out.println("---test mapUsingReduce---");
		// emptyList
		assertMapped(Function.<Object>identity(), Collections.emptyList(), Collections.<Object>emptyList());
		
		// identityMapsToItself
		assertMapped((Integer x) -> x, Arrays.asList(1,2,3), Arrays.asList(1,2,3));
		
		// incrementingNumbers
		assertMapped((Integer x) -> x + 2, Arrays.asList(1,2,3), Arrays.asList(3,4,5));
		
	}

	
	private <I> void assertFiltered(Predicate<I> predicate, List<I> input, List<I> expectedOutput) {
		List<I> output = test.filterUsingReduce(input.stream(), predicate);
		assertEquals(expectedOutput, output);
		System.out.println("[expectedOutput]"+expectedOutput+"[output]"+output);
		 
		List<I> paralleOutPut = test.filterUsingReduce(input.parallelStream(), predicate);
		assertEquals(expectedOutput, paralleOutPut);
		System.out.println("[expectedOutput]"+expectedOutput+"[paralleOutPut]"+paralleOutPut);
	}
	
	@Test
	public void filterUsingReduce() {
		System.out.println("---test filterUsingReduce---");
		// emptyList
		assertFiltered(x -> false, Collections.<Object>emptyList(), Collections.<Object>emptyList());
		
		// trueReturnsEverything
		assertFiltered((Integer x) -> true, Arrays.asList(1,2,3), Arrays.asList(1,2,3));
		
		// falseRemovesEverything
		assertFiltered((Integer x) -> false, Arrays.asList(1,2,3), Arrays.asList());
		
		// filterPartOfList
		assertFiltered((Integer x) -> x > 2, Arrays.asList(1,2,3), Arrays.asList(3));
		
	}
}
