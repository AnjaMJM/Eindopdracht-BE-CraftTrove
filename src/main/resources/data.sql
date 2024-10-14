INSERT INTO categories (id, name, description)
VALUES (1, 'Sewing', 'The craft of fastening or attaching objects using stitches made with a needle and thread. Sewing is used in the making of garments, accessories, and home decor items.'),
       (2, 'Knitting', 'A method of creating fabric by interlocking loops of yarn with needles. Knitting can be used to make garments, blankets, and a wide variety of other items.'),
       (3, 'Crochet', 'A handicraft in which yarn is made up into a textured fabric by means of a hooked needle. Crochet can be used to create blankets, garments, and decorative items.'),
       (4, 'Embroidery', 'The art of decorating fabric with needle and thread or yarn to create intricate designs, often including embellishments like beads, sequins, and pearls.'),
       (5, 'Lace', 'A delicate fabric made of yarn or thread in an open weblike pattern, often used as decorative trims for clothing, home textiles, or crafts.'),
       (6, 'Quilt', 'A sewing method used to join two or more layers of fabric, typically filled with padding, to create a thicker material, commonly used for making blankets and home decor items.'),
       (7, 'Macrame', 'The craft of knotting cord or string in patterns to create decorative pieces such as wall hangings, plant holders, and jewelry.'),
       (8, 'Mending', 'The process of repairing holes or worn areas in fabric, often involving patching or stitching to extend the lifespan of a garment or textile.'),
       (9, 'Felt', 'A technique for creating fabric from wool or other fibers using heat, moisture, and pressure. Felt is used for making accessories, home decor, and craft items.'),
       (10, 'Weaving', 'The process of interlacing threads or yarns on a loom to create fabric. Weaving is used for making garments, rugs, and textiles.');

INSERT INTO designers (id, designer_name, brand_name, brand_description)
VALUES
    (1001, 'Alice Walker', 'Walkers Knits', 'A creative knitwear designer specializing in modern and classic patterns.'),
    (1002, 'Bob Johnson', 'Bobs Stitchworks', 'Specializes in functional, everyday clothing designs with a touch of creativity.'),
    (1003, 'Carol Smith', 'Smith Creations', 'Creating bold, unique crochet patterns and designs for enthusiasts.'),
    (1004, 'David Brown', 'Browns Weaving', 'Expert in woven designs and intricate patterns for traditional and modern styles.');

INSERT INTO products (id, title, description, price, thumbnail, pattern, designer_id, is_available)
VALUES
    (1001, 'Classic Cable Knit Sweater', 'A cozy, classic cable knit sweater pattern perfect for winter wear.', 49.99, 'thumb1.jpg', 'Cable Knit', 1001, false),
    (1002, 'Everyday Crochet Tote', 'A durable and functional crochet tote bag with modern aesthetics.', 29.99, 'thumb2.jpg', 'Crochet', 1003, true),
    (1003, 'Modern Weave Wall Hanging', 'A decorative woven wall hanging with a mix of textures and colors.', 79.99, 'thumb3.jpg', 'Weaving', 1004, false),
    (1004, 'Stitchwork Scarf', 'A warm, stitchwork scarf designed for comfort and style.', 19.99, 'thumb4.jpg', 'Knitting', 1002, true),
    (1005, 'Summer Lace Shawl', 'A light lace shawl pattern perfect for warm evenings and special occasions.', 34.99, 'thumb5.jpg', 'Lace', 1001, true);

INSERT INTO keywords (id, name)
VALUES
(1001, 'sweater'),
(1002, 'winter'),
(1003, 'bag');

INSERT INTO products_categories (product_id, category_id)
VALUES
-- Product 1: Classic Cable Knit Sweater (Knitting, Sewing)
(1001, 2),
(1001, 1),

-- Product 2: Everyday Crochet Tote (Crochet, Sewing)
(1002, 3),
(1002, 1),

-- Product 3: Modern Weave Wall Hanging (Weaving)
(1003, 10),

-- Product 4: Stitchwork Scarf (Knitting, Sewing)
(1004, 2),
(1004, 1),

-- Product 5: Summer Lace Shawl (Lace, Knitting)
(1005, 5),
(1005, 2);

INSERT INTO products_keywords (product_id, keyword_id)
VALUES
(1001, 1001),
(1001, 1002),
(1002, 1003),
(1004, 1002);