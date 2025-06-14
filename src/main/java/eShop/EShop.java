package eShop;

import eShop.model.Category;
import eShop.model.Product;
import eShop.service.CategoryService;
import eShop.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class EShop implements Commands {

    private static Scanner scanner = new Scanner(System.in);
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategoryById();
                    break;
                case "3":
                    deleteCategoryById();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    editProductById();
                    break;
                case "6":
                    deleteProductById();
                    break;
                case "7":
                    System.out.println(productService.getTotalQuantity());
                    break;
                case "8":
                    System.out.println(productService.getMaxPrice());
                    break;
                case "9":
                    System.out.println(productService.getMinPrice());
                    break;
                case "10":
                    System.out.println(productService.getAvgPrice());
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }

    }

    private static void addCategory() {
        System.out.println("Please input category name");
        String categoryDataStr = scanner.nextLine();
        String[] categoryDataArr = categoryDataStr.split(",");
        Category category = Category.builder()
                .name(categoryDataArr[0])
                .build();
        categoryService.addCategory(category);
        System.out.println("Category added!");
    }

    private static boolean editCategoryById() {
        List<Category> allCategories = categoryService.getAllCategories();
        System.out.println("Here are all categories:");
        for (Category category : allCategories) {
            System.out.println(category.getId() + " -> " + category.getName());
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            System.out.println("Category with id " + id + " not found!");
        } else {
            System.out.println("Please input category new name");
            String newName = scanner.nextLine();
            category.setName(newName);
            boolean success = categoryService.editCategoryById(category);
            if (success) {
                System.out.println("Category updated successfully");
            } else {
                System.out.println("Failed to update category");
            }
            return success;
        }
        return false;
    }


    private static void deleteCategoryById() {
        List<Category> allCategories = categoryService.getAllCategories();
        System.out.println("Here are all categories:");
        for (Category category : allCategories) {
            System.out.println(category.getId() + " -> " + category.getName());
        }
        System.out.println("Please choose category id to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        Category categoryById = categoryService.getCategoryById(id);
        if (categoryById == null) {
            System.out.println("Category with id " + id + " not found!");
        } else {
            boolean success = categoryService.deleteCategoryById(id);
            if (success) {
                System.out.println("Category deleted successfully");
            } else {
                System.out.println("Failed to delete category");
            }
        }
    }


    private static void addProduct() {
        List<Category> allCategories = categoryService.getAllCategories();
        System.out.println("Here are all categories:");
        for (Category category : allCategories) {
            System.out.println(category.getId() + " -> " + category.getName());
        }
        System.out.println("Please input category id");
        int id = Integer.parseInt(scanner.nextLine());
        Category categoryById = categoryService.getCategoryById(id);
        if (categoryById != null) {
            System.out.println("Please input product name, description, price, quantity");
            String productDataStr = scanner.nextLine();
            String[] productDataArr = productDataStr.split(",");
            Product product = Product.builder()
                    .name(productDataArr[0])
                    .description(productDataArr[1])
                    .price(Double.parseDouble(productDataArr[2]))
                    .quantity(Integer.parseInt(productDataArr[3]))
                    .category(categoryById)
                    .build();
            productService.addProduct(product);
            System.out.println("Product added!");
        }
    }

    private static boolean editProductById() {
        List<Product> allProducts = productService.getAllProducts();
        System.out.println("Here are all products:");
        for (Product product : allProducts) {
            System.out.println(product.getId() + " -> " + product.getName() + " -> " + product.getDescription() + " -> " + product.getPrice() + " -> " + product.getQuantity() + " -> " + product.getCategory().getName());
        }
        System.out.println("Please choose product id");
        int id = Integer.parseInt(scanner.nextLine());
        Product productById = productService.getProductById(id);
        if (productById == null) {
            System.out.println("Product with id " + id + " not found!");
            return false;
        } else {
            System.out.println("Please input product new name, description,price, quantity");
            String productDataStr = scanner.nextLine();
            String[] productDataArr = productDataStr.split(",");
            Product product = Product.builder()
                    .id(productById.getId())
                    .name(productDataArr[0])
                    .description(productDataArr[1])
                    .price(Double.parseDouble(productDataArr[2]))
                    .quantity(Integer.parseInt(productDataArr[3]))
                    .category(productById.getCategory())
                    .build();
            boolean success = productService.editProductById(productById);
            if (success) {
                System.out.println("Product updated successfully");
            } else {
                System.out.println("Failed to update product");
            }
            return success;
        }
    }


    private static void deleteProductById() {
        List<Product> allProducts = productService.getAllProducts();
        System.out.println("Here are all products:");
        for (Product product : allProducts) {
            System.out.println(product.getId() + " -> " + product.getName() + " -> " + product.getDescription() + " -> " + product.getPrice() + " -> " + product.getQuantity() + " -> " + product.getCategory().getName());
        }
        System.out.println("Please choose product id to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        Product productById = productService.getProductById(id);
        if (productById == null) {
            System.out.println("Product with id " + id + " not found!");
        } else {
            boolean success = productService.deleteProductById(id);
            if (success) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Failed to delete product");
            }
        }
    }
}