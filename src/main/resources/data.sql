-- 브랜드 데이터 삽입
INSERT INTO brand (name, code, created_at, updated_at)
VALUES ('A', 'A001', NOW(), NOW()),
       ('B', 'B001', NOW(), NOW()),
       ('C', 'C001', NOW(), NOW()),
       ('D', 'D001', NOW(), NOW()),
       ('E', 'E001', NOW(), NOW()),
       ('F', 'F001', NOW(), NOW()),
       ('G', 'G001', NOW(), NOW()),
       ('H', 'H001', NOW(), NOW()),
       ('I', 'I001', NOW(), NOW());

-- 제품 데이터 삽입
-- 상의
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('상의', 11200, 'A001', 1, NOW(), NOW()),
       ('상의', 10500, 'B001', 2, NOW(), NOW()),
       ('상의', 10000, 'C001', 3, NOW(), NOW()),
       ('상의', 10100, 'D001', 4, NOW(), NOW()),
       ('상의', 10700, 'E001', 5, NOW(), NOW()),
       ('상의', 11200, 'F001', 6, NOW(), NOW()),
       ('상의', 10500, 'G001', 7, NOW(), NOW()),
       ('상의', 10800, 'H001', 8, NOW(), NOW()),
       ('상의', 11400, 'I001', 9, NOW(), NOW());

-- 아우터
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('아우터', 5500, 'A001', 1, NOW(), NOW()),
       ('아우터', 5900, 'B001', 2, NOW(), NOW()),
       ('아우터', 6200, 'C001', 3, NOW(), NOW()),
       ('아우터', 5100, 'D001', 4, NOW(), NOW()),
       ('아우터', 5000, 'E001', 5, NOW(), NOW()),
       ('아우터', 7200, 'F001', 6, NOW(), NOW()),
       ('아우터', 5800, 'G001', 7, NOW(), NOW()),
       ('아우터', 6300, 'H001', 8, NOW(), NOW()),
       ('아우터', 6700, 'I001', 9, NOW(), NOW());

-- 바지
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('바지', 4200, 'A001', 1, NOW(), NOW()),
       ('바지', 3800, 'B001', 2, NOW(), NOW()),
       ('바지', 3300, 'C001', 3, NOW(), NOW()),
       ('바지', 3000, 'D001', 4, NOW(), NOW()),
       ('바지', 3800, 'E001', 5, NOW(), NOW()),
       ('바지', 4000, 'F001', 6, NOW(), NOW()),
       ('바지', 3900, 'G001', 7, NOW(), NOW()),
       ('바지', 3100, 'H001', 8, NOW(), NOW()),
       ('바지', 3200, 'I001', 9, NOW(), NOW());

-- 스니커즈
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('스니커즈', 9000, 'A001', 1, NOW(), NOW()),
       ('스니커즈', 9100, 'B001', 2, NOW(), NOW()),
       ('스니커즈', 9200, 'C001', 3, NOW(), NOW()),
       ('스니커즈', 9500, 'D001', 4, NOW(), NOW()),
       ('스니커즈', 9900, 'E001', 5, NOW(), NOW()),
       ('스니커즈', 9300, 'F001', 6, NOW(), NOW()),
       ('스니커즈', 9000, 'G001', 7, NOW(), NOW()),
       ('스니커즈', 9700, 'H001', 8, NOW(), NOW()),
       ('스니커즈', 9500, 'I001', 9, NOW(), NOW());

-- 가방
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('가방', 2000, 'A001', 1, NOW(), NOW()),
       ('가방', 2100, 'B001', 2, NOW(), NOW()),
       ('가방', 2200, 'C001', 3, NOW(), NOW()),
       ('가방', 2500, 'D001', 4, NOW(), NOW()),
       ('가방', 2300, 'E001', 5, NOW(), NOW()),
       ('가방', 2100, 'F001', 6, NOW(), NOW()),
       ('가방', 2200, 'G001', 7, NOW(), NOW()),
       ('가방', 2100, 'H001', 8, NOW(), NOW()),
       ('가방', 2400, 'I001', 9, NOW(), NOW());

-- 모자
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('모자', 1700, 'A001', 1, NOW(), NOW()),
       ('모자', 2000, 'B001', 2, NOW(), NOW()),
       ('모자', 1900, 'C001', 3, NOW(), NOW()),
       ('모자', 1500, 'D001', 4, NOW(), NOW()),
       ('모자', 1800, 'E001', 5, NOW(), NOW()),
       ('모자', 1600, 'F001', 6, NOW(), NOW()),
       ('모자', 1700, 'G001', 7, NOW(), NOW()),
       ('모자', 1600, 'H001', 8, NOW(), NOW()),
       ('모자', 1700, 'I001', 9, NOW(), NOW());

-- 양말
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('양말', 1800, 'A001', 1, NOW(), NOW()),
       ('양말', 2000, 'B001', 2, NOW(), NOW()),
       ('양말', 2200, 'C001', 3, NOW(), NOW()),
       ('양말', 2400, 'D001', 4, NOW(), NOW()),
       ('양말', 2100, 'E001', 5, NOW(), NOW()),
       ('양말', 2300, 'F001', 6, NOW(), NOW()),
       ('양말', 2100, 'G001', 7, NOW(), NOW()),
       ('양말', 2000, 'H001', 8, NOW(), NOW()),
       ('양말', 1700, 'I001', 9, NOW(), NOW());

-- 액세서리
INSERT INTO product (name, price, brand_code, brand_id, created_at, updated_at)
VALUES ('액세서리', 2300, 'A001', 1, NOW(), NOW()),
       ('액세서리', 2200, 'B001', 2, NOW(), NOW()),
       ('액세서리', 2100, 'C001', 3, NOW(), NOW()),
       ('액세서리', 2000, 'D001', 4, NOW(), NOW()),
       ('액세서리', 2100, 'E001', 5, NOW(), NOW()),
       ('액세서리', 1900, 'F001', 6, NOW(), NOW()),
       ('액세서리', 2000, 'G001', 7, NOW(), NOW()),
       ('액세서리', 2000, 'H001', 8, NOW(), NOW()),
       ('액세서리', 2400, 'I001', 9, NOW(), NOW());

-- 카테고리 데이터 삽입
INSERT INTO category (name, created_at, updated_at)
VALUES ('상의', NOW(), NOW()),
       ('아우터', NOW(), NOW()),
       ('바지', NOW(), NOW()),
       ('스니커즈', NOW(), NOW()),
       ('가방', NOW(), NOW()),
       ('모자', NOW(), NOW()),
       ('양말', NOW(), NOW()),
       ('액세서리', NOW(), NOW());

-- 카테고리 제품 매핑 데이터 삽입
-- 상의 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (1, 1, NOW(), NOW()), -- 상의 - A 브랜드
       (1, 2, NOW(), NOW()), -- 상의 - B 브랜드
       (1, 3, NOW(), NOW()), -- 상의 - C 브랜드
       (1, 4, NOW(), NOW()), -- 상의 - D 브랜드
       (1, 5, NOW(), NOW()), -- 상의 - E 브랜드
       (1, 6, NOW(), NOW()), -- 상의 - F 브랜드
       (1, 7, NOW(), NOW()), -- 상의 - G 브랜드
       (1, 8, NOW(), NOW()), -- 상의 - H 브랜드
       (1, 9, NOW(), NOW()); -- 상의 - I 브랜드

-- 아우터 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (2, 10, NOW(), NOW()), -- 아우터 - A 브랜드
       (2, 11, NOW(), NOW()), -- 아우터 - B 브랜드
       (2, 12, NOW(), NOW()), -- 아우터 - C 브랜드
       (2, 13, NOW(), NOW()), -- 아우터 - D 브랜드
       (2, 14, NOW(), NOW()), -- 아우터 - E 브랜드
       (2, 15, NOW(), NOW()), -- 아우터 - F 브랜드
       (2, 16, NOW(), NOW()), -- 아우터 - G 브랜드
       (2, 17, NOW(), NOW()), -- 아우터 - H 브랜드
       (2, 18, NOW(), NOW()); -- 아우터 - I 브랜드

-- 바지 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (3, 19, NOW(), NOW()), -- 바지 - A 브랜드
       (3, 20, NOW(), NOW()), -- 바지 - B 브랜드
       (3, 21, NOW(), NOW()), -- 바지 - C 브랜드
       (3, 22, NOW(), NOW()), -- 바지 - D 브랜드
       (3, 23, NOW(), NOW()), -- 바지 - E 브랜드
       (3, 24, NOW(), NOW()), -- 바지 - F 브랜드
       (3, 25, NOW(), NOW()), -- 바지 - G 브랜드
       (3, 26, NOW(), NOW()), -- 바지 - H 브랜드
       (3, 27, NOW(), NOW()); -- 바지 - I 브랜드

-- 스니커즈 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (4, 28, NOW(), NOW()), -- 스니커즈 - A 브랜드
       (4, 29, NOW(), NOW()), -- 스니커즈 - B 브랜드
       (4, 30, NOW(), NOW()), -- 스니커즈 - C 브랜드
       (4, 31, NOW(), NOW()), -- 스니커즈 - D 브랜드
       (4, 32, NOW(), NOW()), -- 스니커즈 - E 브랜드
       (4, 33, NOW(), NOW()), -- 스니커즈 - F 브랜드
       (4, 34, NOW(), NOW()), -- 스니커즈 - G 브랜드
       (4, 35, NOW(), NOW()), -- 스니커즈 - H 브랜드
       (4, 36, NOW(), NOW());
-- 스니커즈 - I 브랜드

-- 가방 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (5, 37, NOW(), NOW()), -- 가방 - A 브랜드
       (5, 38, NOW(), NOW()), -- 가방 - B 브랜드
       (5, 39, NOW(), NOW()), -- 가방 - C 브랜드
       (5, 40, NOW(), NOW()), -- 가방 - D 브랜드
       (5, 41, NOW(), NOW()), -- 가방 - E 브랜드
       (5, 42, NOW(), NOW()), -- 가방 - F 브랜드
       (5, 43, NOW(), NOW()), -- 가방 - G 브랜드
       (5, 44, NOW(), NOW()), -- 가방 - H 브랜드
       (5, 45, NOW(), NOW()); -- 가방 - I 브랜드

-- 모자 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (6, 46, NOW(), NOW()), -- 모자 - A 브랜드
       (6, 47, NOW(), NOW()), -- 모자 - B 브랜드
       (6, 48, NOW(), NOW()), -- 모자 - C 브랜드
       (6, 49, NOW(), NOW()), -- 모자 - D 브랜드
       (6, 50, NOW(), NOW()), -- 모자 - E 브랜드
       (6, 51, NOW(), NOW()), -- 모자 - F 브랜드
       (6, 52, NOW(), NOW()), -- 모자 - G 브랜드
       (6, 53, NOW(), NOW()), -- 모자 - H 브랜드
       (6, 54, NOW(), NOW()); -- 모자 - I 브랜드

-- 양말 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (7, 55, NOW(), NOW()), -- 양말 - A 브랜드
       (7, 56, NOW(), NOW()), -- 양말 - B 브랜드
       (7, 57, NOW(), NOW()), -- 양말 - C 브랜드
       (7, 58, NOW(), NOW()), -- 양말 - D 브랜드
       (7, 59, NOW(), NOW()), -- 양말 - E 브랜드
       (7, 60, NOW(), NOW()), -- 양말 - F 브랜드
       (7, 61, NOW(), NOW()), -- 양말 - G 브랜드
       (7, 62, NOW(), NOW()), -- 양말 - H 브랜드
       (7, 63, NOW(), NOW()); -- 양말 - I 브랜드

-- 액세서리 카테고리 제품 매핑
INSERT INTO category_product_mapping (category_id, product_id, created_at, updated_at)
VALUES (8, 64, NOW(), NOW()), -- 액세서리 - A 브랜드
       (8, 65, NOW(), NOW()), -- 액세서리 - B 브랜드
       (8, 66, NOW(), NOW()), -- 액세서리 - C 브랜드
       (8, 67, NOW(), NOW()), -- 액세서리 - D 브랜드
       (8, 68, NOW(), NOW()), -- 액세서리 - E 브랜드
       (8, 69, NOW(), NOW()), -- 액세서리 - F 브랜드
       (8, 70, NOW(), NOW()), -- 액세서리 - G 브랜드
       (8, 71, NOW(), NOW()), -- 액세서리 - H 브랜드
       (8, 72, NOW(), NOW()); -- 액세서리 - I 브랜드

-- 카테고리 경로 삽입
INSERT INTO category_path (parent_id, child_id, depth, created_at, updated_at)
VALUES (1, 1, 0, NOW(), NOW()), -- 상의 -> 상의
       (2, 2, 0, NOW(), NOW()), -- 아우터 -> 아우터
       (3, 3, 0, NOW(), NOW()), -- 바지 -> 바지
       (4, 4, 0, NOW(), NOW()), -- 스니커즈 -> 스니커즈
       (5, 5, 0, NOW(), NOW()), -- 가방 -> 가방
       (6, 6, 0, NOW(), NOW()), -- 모자 -> 모자
       (7, 7, 0, NOW(), NOW()), -- 양말 -> 양말
       (8, 8, 0, NOW(), NOW()); -- 액세서리 -> 액세서리

-- 카테고리-브랜드 매핑 데이터 삽입
INSERT INTO category_brand_mapping (brand_id, category_id, created_at, updated_at)
VALUES (1, 1, NOW(), NOW()), -- A 브랜드 -> 상의
       (2, 2, NOW(), NOW()), -- B 브랜드 -> 아우터
       (3, 3, NOW(), NOW()), -- C 브랜드 -> 바지
       (4, 4, NOW(), NOW()), -- D 브랜드 -> 스니커즈
       (5, 5, NOW(), NOW()), -- E 브랜드 -> 가방
       (6, 6, NOW(), NOW()), -- F 브랜드 -> 모자
       (7, 7, NOW(), NOW()), -- G 브랜드 -> 양말
       (8, 8, NOW(), NOW()), -- H 브랜드 -> 액세서리
       (9, 1, NOW(), NOW()), -- I 브랜드 -> 상의
       (1, 2, NOW(), NOW()), -- A 브랜드 -> 아우터
       (2, 3, NOW(), NOW()), -- B 브랜드 -> 바지
       (3, 4, NOW(), NOW()), -- C 브랜드 -> 스니커즈
       (4, 5, NOW(), NOW()), -- D 브랜드 -> 가방
       (5, 6, NOW(), NOW()), -- E 브랜드 -> 모자
       (6, 7, NOW(), NOW()), -- F 브랜드 -> 양말
       (7, 8, NOW(), NOW()), -- G 브랜드 -> 액세서리
       (8, 1, NOW(), NOW()), -- H 브랜드 -> 상의
       (9, 2, NOW(), NOW()), -- I 브랜드 -> 아우터
       (1, 3, NOW(), NOW()), -- A 브랜드 -> 바지
       (2, 4, NOW(), NOW()), -- B 브랜드 -> 스니커즈
       (3, 5, NOW(), NOW()), -- C 브랜드 -> 가방
       (4, 6, NOW(), NOW()), -- D 브랜드 -> 모자
       (5, 7, NOW(), NOW()), -- E 브랜드 -> 양말
       (6, 8, NOW(), NOW()), -- F 브랜드 -> 액세서리
       (7, 1, NOW(), NOW()), -- G 브랜드 -> 상의
       (8, 2, NOW(), NOW()), -- H 브랜드 -> 아우터
       (9, 3, NOW(), NOW()); -- I 브랜드 -> 바지
