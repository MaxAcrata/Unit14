package ru.netologia.qamid;
public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    /**
     * Метод для удаления товара по ID
     * @param id — ID товара
     * @throws NotFoundException — если товар с указанным ID не найден
     */
    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product p : products) {
            if (p.getId() != id) {
                tmp[copyToIndex] = p;
                copyToIndex++;
            }
        }
        products = tmp;
    }
    /**
     * Метод для поиска товара по ID
     * @param id — ID товара
     * @return — найденный товар или null, если товар не найден
     */
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}