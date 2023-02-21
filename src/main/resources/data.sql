insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (10, '1000', '42-45', 'https://media.shoesonline.co.il/2021/06/8717562435790-1.jpg',
        'MEN''S BULLBOXER ALESSANDRO', 249, CURRENT_TIMESTAMP());
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (11, '1001', '42-45', 'https://media.shoesonline.co.il/2022/03/adidas-buty-lite-racer-3-0-gw1087-ba57f847.jpg',
        'MEN''S ADIDAS LITE RACER 3', 259, CURRENT_TIMESTAMP() + 1);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (12, '1002', '42-45', 'https://media.shoesonline.co.il/2021/08/adidas-buty-runfalcon-2-0-g58096-14d5b582.jpg',
        'MEN''S ADIDAS RUNFALCON 2.0', 229, CURRENT_TIMESTAMP() + 2);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (13, '1003', '42-45', 'https://media.shoesonline.co.il/2021/06/8717562440015-1.jpg', 'MEN''S BULLBOXER LEONARDO',
        199, CURRENT_TIMESTAMP() + 3);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (14, '1004', '42-45', 'https://media.shoesonline.co.il/2021/06/8717562435127-1.jpg', 'MEN''S BULLBOXER MATTEO',
        249, CURRENT_TIMESTAMP() + 4);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (15, '1005', '42-45',
        'https://media.shoesonline.co.il/2022/07/adidas-buty-lite-racer-cln-2-0-gy5975-5706269c.jpg',
        'MEN''S ADIDAS LITE RACER CLN 2.0', 345, CURRENT_TIMESTAMP() + 5);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (16, '1006', '42-45', 'https://media.shoesonline.co.il/2022/07/Duramo-10-Adidas-men__GW8336-555x555.jpg',
        'MEN''S ADIDAS DURAMO 10', 299, CURRENT_TIMESTAMP() + 6);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (17, '1007', '42-45', 'https://media.shoesonline.co.il/2021/02/adidas-buty-runfalcon-2-0-gz8077-a08c00b8.jpg',
        'MEN''S ADIDAS RUNFALCON 2.0', 335, CURRENT_TIMESTAMP() + 7);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (18, '1008', '42-45', 'https://media.shoesonline.co.il/2021/12/men-Nike-REVOLUTION-6__DC3728-401.jpg',
        'MEN''S NIKE REVOLUTION 6', 299, CURRENT_TIMESTAMP() + 8);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (19, '1009', '42-45', 'https://media.shoesonline.co.il/2022/01/men-Nike-COURT-LEGACY-NN__DH3162-001.jpg',
        'MEN''S NIKE COURT LEGACY', 479, CURRENT_TIMESTAMP() + 9);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (20, '1010', '42-45', 'https://media.shoesonline.co.il/2022/09/puma-buty-rickie-38760701-b44487b0.jpg',
        'MEN''S PUMA RICKIE', 269, CURRENT_TIMESTAMP() + 10);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (21, '1011', '42-45', 'https://media.shoesonline.co.il/2019/12/c4fe5f4e23003f48fe5fabe3cc3f12ad.jpg',
        'MEN''S ADIDAS VS PACE', 199, CURRENT_TIMESTAMP() + 11);
insert into shoes(id, barcode, sizes, image_url, name, cost, created_date)
values (22, '1012', '42-45',
        'https://media.shoesonline.co.il/2022/08/nike-buty-court-royale-2-nn-dh3160-101-b51f78e7.jpg',
        'MEN''S NIKE COURT ROYALE 2', 479, CURRENT_TIMESTAMP() + 12);

insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1000, '1000', '45', 2, 10);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1001, '1000', '43', 2, 10);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1002, '1000', '42', 2, 10);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1003, '1001', '45', 2, 11);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1004, '1001', '46', 2, 11);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1005, '1002', '43', 2, 12);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1006, '1003', '42', 2, 13);
insert into shoes_amount(id, barcode, size, amount, shoes_id)
values (1007, '1004', '45', 2, 14);

insert into worker(id, name)
values (1, 'Moshe');
insert into worker(id, name)
values (2, 'Shimon');
insert into worker(id, name)
values (3, 'Yael');
insert into worker(id, name)
values (4, 'Shmuel');
insert into worker(id, name)
values (5, 'Dor');
insert into worker(id, name)
values (6, 'Mor');

insert into month_sales(id, date, sum)
values (1, '2023-02-01', 58000.0);
insert into month_sales(id, date, sum)
values (2, '2023-01-01', 38000.0);
insert into month_sales(id, date, sum)
values (3, '2022-12-01', 50000.0);
insert into month_sales(id, date, sum)
values (4, '2022-11-01', 52000.0);
insert into month_sales(id, date, sum)
values (5, '2022-10-01', 36000.0);
insert into month_sales(id, date, sum)
values (6, '2022-09-01', 29000.0);

insert into day_sales(id, date, sum)
values (1, '2023-02-06', 5300.0);
insert into day_sales(id, date, sum)
values (2, '2023-02-05', 4000.0);
insert into day_sales(id, date, sum)
values (3, '2023-02-04', 4500.0);
insert into day_sales(id, date, sum)
values (4, '2023-02-03', 3800.0);
insert into day_sales(id, date, sum)
values (5, '2023-02-02', 2500.0);
insert into day_sales(id, date, sum)
values (6, '2023-02-01', 3500.0);
insert into day_sales(id, date, sum)
values (7, '2023-01-31', 2000.0);

insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (1, '2023-02-01', 2000, 10);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (2, '2023-02-01', 2100, 11);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (3, '2023-02-01', 2200, 12);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (4, '2023-02-01', 2300, 13);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (5, '2023-02-01', 2400, 14);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (6, '2023-02-01', 2500, 15);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (7, '2023-02-01', 2600, 16);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (8, '2023-02-01', 2700, 17);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (9, '2023-02-01', 2800, 18);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (10, '2023-02-01', 2900, 19);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (11, '2023-02-01', 3000, 20);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (12, '2023-02-01', 3100, 21);
insert into shoes_popularity(id, date, sold_pairs, shoes_id)
values (13, '2023-02-01', 3200, 22);

insert into worker_sales(id, date, worker_id, sum)
values (1, '2023-02-01', 1, 22000.0);
insert into worker_sales(id, date, worker_id, sum)
values (2, '2023-02-01', 2, 32000.0);
insert into worker_sales(id, date, worker_id, sum)
values (3, '2023-02-01', 3, 30000.0);
insert into worker_sales(id, date, worker_id, sum)
values (4, '2023-02-01', 4, 18000.0);
insert into worker_sales(id, date, worker_id, sum)
values (5, '2023-02-01', 5, 35000.0);
insert into worker_sales(id, date, worker_id, sum)
values (6, '2023-02-01', 6, 27000.0);
