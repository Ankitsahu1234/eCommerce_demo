INSERT INTO orders (total_price, order_status) VALUES
                                                   (100.50, 'PENDING'),
                                                   (250.75, 'SHIPPED'),
                                                   (89.99, 'DELIVERED'),
                                                   (450.00, 'PENDING'),
                                                   (129.49, 'CANCELLED'),
                                                   (75.25, 'DELIVERED'),
                                                   (999.99, 'SHIPPED'),
                                                   (349.99, 'PENDING'),
                                                   (59.99, 'DELIVERED'),
                                                   (799.00, 'SHIPPED');

INSERT INTO order_item (order_id, product_id, quantity) VALUES
                                                            (1, 1, 2),
                                                            (2, 2, 1),
                                                            (3, 3, 3),
                                                            (4, 4, 1),
                                                            (5, 5, 2),
                                                            (6, 6, 4),
                                                            (7, 7, 1),
                                                            (8, 8, 2),
                                                            (9, 9, 1),
                                                            (10, 10, 5);