package dev.rinaldo.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rinaldo.config.LogProducer;
import dev.rinaldo.dao.FrutasDAO;
import dev.rinaldo.domain.Fruta;
import dev.rinaldo.rest.FrutasResource;

@ExtendWith(MockitoExtension.class)
public class FrutasResourceTest {

    @Mock
    private FrutasDAO frutasDAO;

    @BeforeEach
    void setup() {
    }

    @Test
    public void listarTodasAsFrutas_PoucasFrutas() {
        // setup
        Fruta fruta1 = new Fruta();
        fruta1.setId(1L);
        Fruta fruta2 = new Fruta();
        fruta2.setId(2L);
        Fruta fruta3 = new Fruta();
        fruta3.setId(3L);

        List<Fruta> expected = Arrays.asList(fruta1, fruta2, fruta3);
        when(frutasDAO.listAll()).thenReturn(expected);
        
        FrutasResource frutasResource = new FrutasResource(frutasDAO, LogProducer.produceLog(getClass()));
        
        // funcionalidade
        List<Fruta> actual = frutasResource.get();

        // assert
        verify(frutasDAO, times(1)).listAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(Set.copyOf(expected), Set.copyOf(actual)); // compara um SET para que a ordem seja ignorada
    }

}