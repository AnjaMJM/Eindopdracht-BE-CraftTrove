INSERT INTO categories (id, name, description)
VALUES (1, 'Sewing',
        'The craft of fastening or attaching objects using stitches made with a needle and thread. Sewing is used in the making of garments, accessories, and home decor items.'),
       (2, 'Knitting',
        'A method of creating fabric by interlocking loops of yarn with needles. Knitting can be used to make garments, blankets, and a wide variety of other items.'),
       (3, 'Crochet',
        'A handicraft in which yarn is made up into a textured fabric by means of a hooked needle. Crochet can be used to create blankets, garments, and decorative items.'),
       (4, 'Embroidery',
        'The art of decorating fabric with needle and thread or yarn to create intricate designs, often including embellishments like beads, sequins, and pearls.'),
       (5, 'Lace',
        'A delicate fabric made of yarn or thread in an open weblike pattern, often used as decorative trims for clothing, home textiles, or crafts.'),
       (6, 'Quilt',
        'A sewing method used to join two or more layers of fabric, typically filled with padding, to create a thicker material, commonly used for making blankets and home decor items.'),
       (7, 'Macrame',
        'The craft of knotting cord or string in patterns to create decorative pieces such as wall hangings, plant holders, and jewelry.'),
       (8, 'Mending',
        'The process of repairing holes or worn areas in fabric, often involving patching or stitching to extend the lifespan of a garment or textile.'),
       (9, 'Felt',
        'A technique for creating fabric from wool or other fibers using heat, moisture, and pressure. Felt is used for making accessories, home decor, and craft items.'),
       (10, 'Weaving',
        'The process of interlacing threads or yarns on a loom to create fabric. Weaving is used for making garments, rugs, and textiles.');

-- Users Table
INSERT INTO users (id, username, password, email, is_designer)
VALUES (1001, 'alicewalker', '$2a$12$MU.EA3cAWvRVhX/ujco2OOtbp.4RwuhncKRBJqsJm1JbljnDc11dO', 'alice@walkersknits.com',
        true),
       (1002, 'bobjohnson', ' $2a$12$hwEYuVCgFJruonINEkKOj.F1Y8rXgIT5DJdSdcSfjp9NCEGJqd4sy ', 'bob@stitchworks.com',
        true),
       (1003, 'carolsmith', ' $2a$12$78KAIeHXf2MnbRN65Tk6MOHFPDnMnUQibvRsu9uxLWw20cY7UObPW ',
        'carol@smithcreations.com', true),
       (1004, 'davidbrown', ' $2a$12$ZzW29rNM86p6TnIrXAEoiefD/8YVauZCkK0H7kCbnSIPhwj/IEPpe ', 'david@brownsweaving.com',
        true),
       (1005, 'crafty user', '$2a$12$GL97deK465zp4.N2lDis7.XI86h/RLVqVHb9VpgXtCA2NBxhyTd7G', 'user@crafttrove.com',
        false), --ww: wachtwoord
       (1006, 'dreamy designer', '$2a$12$x/mwrBmtw1trzIInzsIZY.pKS0hKq/qgVhCBacrPHvA85cWpLGAMq',
        'designer@crafttrove.com', true); --ww: wachtwoord

-- Designers Table
INSERT INTO designers (id, brand_name, brand_logo, brand_description)
VALUES (1001, 'Walkers Knits', 'logo1.png',
        'A creative knitwear designer specializing in modern and classic patterns.'),
       (1002, 'Bobs Stitchworks', 'logo2.png',
        'Specializes in functional, everyday clothing designs with a touch of creativity.'),
       (1003, 'Smith Creations', 'logo3.png', 'Creating bold, unique crochet patterns and designs for enthusiasts.'),
       (1004, 'Browns Weaving', 'logo4.png',
        'Expert in woven designs and intricate patterns for traditional and modern styles.'),
       (1006, 'Dream Design', 'logo5.png',
        'Creating fun and easy designs, for everybody to follow along. From starting creatives, to pro-crafters looking for a quick project');

-- Products Table
INSERT INTO products (id, title, description, price, thumbnail, pattern, designer_id, is_available)
VALUES (1001, 'Classic Cable Knit Sweater', 'A cozy, classic cable knit sweater pattern perfect for winter wear.',
        49.99, 'thumb1.jpg', 'Cable_Knit.pdf', 1001, false),
       (1002, 'Everyday Crochet Tote', 'A durable and functional crochet tote bag with modern aesthetics.', 29.99,
        'thumb2.jpg', 'Crochet.pdf', 1003, true),
       (1003, 'Modern Weave Wall Hanging', 'A decorative woven wall hanging with a mix of textures and colors.', 79.99,
        'thumb3.jpg', 'Weaving.pdf', 1004, true),
       (1004, 'Stitchwork Scarf', 'A warm, stitchwork scarf designed for comfort and style.', 19.99, 'thumb4.jpg',
        'Knitting.pdf', 1002, false),
       (1005, 'Summer Lace Shawl', 'A light lace shawl pattern perfect for warm evenings and special occasions.', 34.99,
        'thumb5.jpg', 'Lace.pdf', 1001, true),
       (1006, 'Goofy bunny', 'Fun little needle felted bunny', 5.99, 'thumb6.jpg', 'bunny.pdf', 1006, true);

-- Keywords Table
INSERT INTO keywords (id, name)
VALUES (1001, 'sweater'),
       (1002, 'winter'),
       (1003, 'bag'),
       (1006, 'bunny'),
       (1007, 'little project');

-- Purchases Table (Placeholder data, adapt as needed)
INSERT INTO purchases (id, user_id, date, total_price, is_payed)
VALUES (1001, 1001, '2023-01-01', 79.98, true),
       (1002, 1002, '2023-02-15', 34.99, false),
       (1003, 1003, '2023-03-05', 19.99, true);

-- Reviews Table (Placeholder data, adapt as needed)
INSERT INTO reviews (id, user_id, product_id, text, rating, date)
VALUES (1001, 1001, 1001, 'Great pattern, very clear instructions!', 5, '2023-01-10'),
       (1002, 1002, 1002, 'Functional and stylish, love it!', 4, '2023-02-20'),
       (1003, 1003, 1003, 'Beautiful design but a bit pricey.', 3, '2023-03-15');

-- User Preferences (Categories)
INSERT INTO user_categories (user_id, category_id)
VALUES (1001, 1),
       (1001, 2),
       (1002, 3),
       (1003, 4);

-- User Wishlist Table (Placeholder data, adapt as needed)
INSERT INTO user_wishlist (user_id, product_id)
VALUES (1001, 1003),
       (1002, 1002),
       (1003, 1001),
       (1004, 1006),
       (1001, 1005),
       (1006, 1001),
       (1006, 1004);

-- Products Categories Table
INSERT INTO products_categories (product_id, category_id)
VALUES (1001, 2),
       (1001, 1),
       (1002, 3),
       (1002, 1),
       (1003, 10),
       (1004, 2),
       (1004, 1),
       (1005, 5),
       (1005, 2),
       (1006, 9);

-- Products Keywords Table
INSERT INTO products_keywords (product_id, keyword_id)
VALUES (1001, 1001),
       (1001, 1002),
       (1002, 1003),
       (1004, 1002),
       (1006, 1006),
       (1006, 1007);

-- Products Purchased Table
INSERT INTO products_purchased (product_id, purchase_id)
VALUES (1001, 1001),
       (1002, 1002),
       (1003, 1003);
