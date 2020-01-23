package dev.rinaldo.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rinaldo.dao.FruitsDAO;
import dev.rinaldo.domain.Fruit;
import dev.rinaldo.rest.FruitsRest;

@ExtendWith(MockitoExtension.class)
public class FruitsRestTest {

	@Mock
	private FruitsDAO fruitsDAO;
	
	private FruitsRest fruitsRest;
	
	@BeforeEach
	void setup() {
		fruitsRest = new FruitsRest(fruitsDAO);
	}
	
	@Test
    public void whenGetFruitsMustReturnFruit() {
		Fruit fruit = new Fruit();
		fruit.name = "Orange";
		ArrayList<Fruit> fruits = new ArrayList<>();
		fruits.add(fruit);
		
		when(fruitsDAO.findByName(anyString())).thenReturn(fruits);
		
		assertEquals("Orange", fruitsRest.getByName("Orange").get(0).name);
    }
    
}