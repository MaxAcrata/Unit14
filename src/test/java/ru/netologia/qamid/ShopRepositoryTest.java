package ru.netologia.qamid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    private ShopRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ShopRepository();
        repository.add(new Product(1, "Product 1", 100));
        repository.add(new Product(2, "Product 2", 200));
        repository.add(new Product(3, "Product 3", 300));
    }

    @Test
    public void testRemoveExistingProduct() {
        // Удаляем существующий продукт
        repository.removeById(2);

        // Проверяем, что продукт удален
        Product[] products = repository.findAll();
        Assertions.assertEquals(2, products.length);
        Assertions.assertNull(repository.findById(2));
    }

    @Test
    public void testRemoveNonExistingProduct() {
        // Пытаемся удалить несуществующий продукт
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(999);
        });

        // Проверяем, что массив продуктов не изменился
        Product[] products = repository.findAll();
        Assertions.assertEquals(3, products.length);
    }
}