package com.example.datastructures.product;

import com.example.models.Product;

public class ProductBst {

    private ProductNode root;

    private static class ProductNode {
        Product product;
        ProductNode left;
        ProductNode right;

        ProductNode(Product product) {
            this.product = product;
            this.left = null;
            this.right = null;
        }
    }

    public ProductBst() {
        this.root = null;
    }

    public void insert(Product product) {
        root = insert_record(root, product);
    }

    private ProductNode insert_record(ProductNode node, Product product) {
        if (node == null) return new ProductNode(product);

        if (product.getProductId() < node.product.getProductId()) {
            node.left = insert_record(node.left, product);
        } else if (product.getProductId() > node.product.getProductId()) {
            node.right = insert_record(node.right, product);
        }

        return node;
    }

    public Product searchById(int productId) {
        ProductNode result = search_id_record(root, productId);
        return result != null ? result.product : null;
    }

    private ProductNode search_id_record(ProductNode node, int productId) {
        if (node == null) return null;

        if (productId == node.product.getProductId()) return node;

        if (productId < node.product.getProductId()) {
            return search_id_record(node.left, productId);
        }

        return search_id_record(node.right, productId);
    }

    public ProductList searchByName(String name) {
        ProductList results = new ProductList();
        searchByNameRec(root, name.toLowerCase().trim(), results);
        return results;
    }

    private void searchByNameRec(ProductNode node, String name, ProductList results) {
        if (node == null) return;

        if (node.product.getProductName().toLowerCase().contains(name)) {
            results.add(node.product);
        }

        searchByNameRec(node.left, name, results);
        searchByNameRec(node.right, name, results);
    }

    public void delete(int productId) {
        root = deleteRec(root, productId);
    }

    private ProductNode deleteRec(ProductNode node, int productId) {
        if (node == null) return null;

        if (productId < node.product.getProductId()) {
            node.left = deleteRec(node.left, productId);
        } else if (productId > node.product.getProductId()) {
            node.right = deleteRec(node.right, productId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            ProductNode successor = findMin(node.right);
            node.product = successor.product;
            node.right = deleteRec(node.right, successor.product.getProductId());
        }

        return node;
    }

    private ProductNode findMin(ProductNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public ProductList inOrder() {
        ProductList result = new ProductList();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(ProductNode node, ProductList result) {
        if (node == null) return;

        inOrderRec(node.left, result);
        result.add(node.product);
        inOrderRec(node.right, result);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}