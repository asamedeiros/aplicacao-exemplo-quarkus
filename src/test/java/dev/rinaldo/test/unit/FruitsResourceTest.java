package dev.rinaldo.test.unit;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rinaldo.dao.FruitsDAO;
import dev.rinaldo.domain.Fruit;
import dev.rinaldo.rest.FruitsResource;

@ExtendWith(MockitoExtension.class)
public class FruitsResourceTest {

    @Mock
    private FruitsDAO fruitsDAO;

    @BeforeEach
    void setup() {
    }

    @Test
    public void given_SomeFruitsExists_when_listFruits_then_mustReturnThoseFruits() {
        // given
        List<String> fruitsNames = Arrays.asList("Orange", "Apple", "Pineapple", "Banana", "Watermelon");
        List<Fruit> expected = fruitsNames.stream()
                .map(name -> {
                    Fruit fruit = new Fruit();
                    fruit.setName(name);
                    return fruit;
                })
                .collect(toList());

        when(fruitsDAO.listAll()).thenReturn(expected);
        FruitsResource fruitsRest = new FruitsResource(fruitsDAO);

        // when
        List<Fruit> actual = fruitsRest.list();

        // then
        assertEquals(expected.size(), actual.size());
        assertEquals(Set.copyOf(expected), Set.copyOf(actual)); // compare a set so order is ignored
    }

}