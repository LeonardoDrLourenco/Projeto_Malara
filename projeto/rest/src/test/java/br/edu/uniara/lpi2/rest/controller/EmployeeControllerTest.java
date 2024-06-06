package br.edu.uniara.lpi2.rest.controller;

import br.edu.uniara.lpi2.rest.model.Employee;
import br.edu.uniara.lpi2.rest.model.EmployeePaginRepository;
import br.edu.uniara.lpi2.rest.model.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {
    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeePaginRepository paginRepository;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAll() {
        // Este teste está projetado para falhar
        when(repository.findAll()).thenReturn(List.of(new Employee("Leo teste", "dev")));

        List<Employee> employees = controller.all();

        assertEquals(2, employees.size(), "O tamanho da lista de funcionários deve ser 2");
    }

    @Test
    public void testEmployeeNotFound() {
        // Mock da interface EmployeeRepository
        EmployeeRepository repository = mock(EmployeeRepository.class);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Criando o controlador com o mock do EmployeeRepository
        EmployeeController controller = new EmployeeController(repository);

        // Verificando se o funcionário retornado é nulo
        assertNull(controller.one(1L));
    }
}
