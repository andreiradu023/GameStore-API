Use `game_store`;

INSERT INTO `users` (`first_name`, `last_name`, `email`, `phone`, `address`, `password`)
VALUES ('Game', 'Master', 'gamemaster95@yahoo.com', '0723774423', 'Romania',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('George', 'Dumbrava', 'george_dumbrava@gmail.com', '0745123123',
        'Romania, Pitesti, Strada Egalitati, 110049', '$2a$12$6xMT5nqLblvXOJi50/mOW.4ukH/19.SLPIGfRsD5nWaFoxLjYw1ue'),
       ('Cristian', 'Ionescu', 'cristian.ionescu@yahoo.com', '0745124124',
        'Romania, Buzau, Bulevardul 1 Decembrie 1918, 120201',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Cristina', 'Popescu', 'cristina_popescu93@gmail.com', '0745125125',
        'Romania, Bucuresti, Strada Gabroveni, 030089', '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Dorel', 'Gheorghe', 'gheorghe_dorel@gmail.com', '0745126126',
        'Romania, Cluj-Napoca, Strada Deva, 400347', '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Ioana', 'Popa', 'ioana.popa44@yahoo.com', '0745127127',
        'Romania, Timisoara, Bd. Vasile Parvan, 300323',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Adrian', 'Ursu', 'ursu_adrian99@yahoo.com', '0745128128',
        'Romania, Oradea, Strada Motilor, 410089', '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Iulian', 'Dumitru', 'dumitru.iulian56@gmail.com', '0745129129',
        'Romania, Voluntari, Bulevardul Pipera, 077190',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Vasile', 'George', 'george_vasile78@gmail.com', '0745134134',
        'Romania, Timisoara, Strada Eugeniu de Savoya, 300085',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Teodora', 'Dobre', 'teodora-dobre@gmail.com', '0745135135',
        'Romania, Cluj-Napoca, Strada Emile Zola, 400112',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6'),
       ('Alina', 'Rosu', 'rosu_alina@yahoo.com', '0745136136',
        'Romania, Iasi, Strada George Emil Palade, 700259',
        '$2a$12$t7DgauxEDJoL8WfzSmI2L.yurz9ZvJa9QwC7irx6T36WRJagJSoO6');

INSERT INTO `roles` (`name`)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1);

INSERT INTO `games` (`title`, `platform`, `genre`, `description`, `price`,
                     `image`, `release_date`, `stock_quantity`)
VALUES ('Red Dead Redemption 2', 'PlayStation 4, Xbox One, Google Stadia, Microsoft Windows', 'Action-adventure',
        'Winner of over 175 Game of the Year Awards and recipient of over 250 perfect scores, RDR2 is the epic tale of outlaw Arthur Morgan and the infamous Van der Linde gang, on the run across America at the dawn of the modern age. Also includes access to the shared living world of Red Dead Online.',
        '59.99', 'https://image.api.playstation.com/cdn/UP1004/CUSA03041_00/Hpl5MtwQgOVF9vJqlfui6SDB5Jl4oBSq.png',
        '2018-10-01', '100'),
       ('Mass Effect Legendary Edition', 'PlayStation 4, Microsoft Windows, Xbox One',
        'Action role-playing, third-person shooter',
        'The Mass Effect™ Legendary Edition includes single-player base content and over 40 DLC from the highly acclaimed Mass Effect, Mass Effect 2, and Mass Effect 3 games, including promo weapons, armors, and packs — remastered and optimized for 4K Ultra HD.',
        '69.99', 'https://image.api.playstation.com/vulcan/ap/rnd/202101/2517/xK2b8gY5A5oyYlc1pnUUVEm5.png',
        '2021-04-30', '110'),
       ('The Witcher 3: Wild Hunt', 'PlayStation 5, PlayStation 4, Xbox One, Microsoft Windows, Nintendo Switch',
        'Open-world, Action role-playing',
        'You are Geralt of Rivia, mercenary monster slayer. Before you stands a war-torn, monster-infested continent you can explore at will. Your current contract? Tracking down Ciri — the Child of Prophecy, a living weapon that can alter the shape of the world.',
        '49.99', 'https://image.api.playstation.com/vulcan/ap/rnd/202211/0711/kh4MUIuMmHlktOHar3lVl6rY.png',
        '2015-05-18', '77'),
       ('Horizon Forbidden West', 'PlayStation 5, PlayStation 4', 'Action role-playing, Open-world',
        'Join Aloy as she braves the Forbidden West – a majestic but dangerous frontier that conceals mysterious new threats.Explore distant lands, fight bigger and more awe-inspiring machines, and encounter astonishing new tribes as you return to the far-future, post-apocalyptic world of Horizon.',
        '60.96', 'https://image.api.playstation.com/vulcan/ap/rnd/202107/3100/HO8vkO9pfXhwbHi5WHECQJdN.png',
        '2022-02-18', '50'),
       ('Hogwarts Legacy', 'PlayStation 5, Nintendo Switch, PlayStation 4, Microsoft Windows, Xbox One',
        'Action role-playing, Open-world',
        'Hogwarts Legacy is an immersive, open-world action RPG. Now you can take control of the action and be at the center of your own adventure in the wizarding world.',
        '54.99', 'https://image.api.playstation.com/vulcan/ap/rnd/202011/0919/cDHU28ds7cCvKAnVQo719gs0.png',
        '2023-02-10', '44'),
       ('God of War', 'PlayStation 5, PlayStation 4, Microsoft Windows', 'Action role-playing,Action, Adventure',
        'His vengeance against the Gods of Olympus years behind him, Kratos now lives as a man in the realm of Norse Gods and monsters. It is in this harsh, unforgiving world that he must fight to survive… and teach his son to do the same.',
        '69', 'https://static.posters.cz/image/750/postere/playstation-god-of-war-i116582.jpg', '2018-04-20', '23'),
       ('Star Wars Jedi: Survivor', 'PlayStation 5, PlayStation 4, Xbox One, Microsoft Windows', 'Action, Adventure',
        'The story of Cal Kestis continues in STAR WARS Jedi: Survivor™, a galaxy-spanning, third-person, action-adventure game.',
        '89', 'https://image.api.playstation.com/vulcan/ap/rnd/202211/2222/l8QTN7ThQK3lRBHhB3nX1s7h.png', '2023-04-28',
        '33'),
       ('Sekiro: Shadows Die Twice', 'PlayStation 4, Xbox One, Microsoft Windows', 'Action, Adventure',
        'Game of the Year - The Game Awards 2019 Best Action Game of 2019 - IGN Carve your own clever path to vengeance in the award winning adventure from developer FromSoftware, creators of Bloodborne and the Dark Souls series. Take Revenge. Restore Your Honor. Kill Ingeniously',
        '29.99',
        'https://image.api.playstation.com/cdn/HP0506/CUSA13910_00/QKJRzanGk86ezpx2pk5QqQaduoXGJV3u8vHIejSav4MYiHA3F7zNgxSOF9bJmt2T.png',
        '2019-03-22', '150'),
       ('The Last of Us', 'PlayStation 5, Microsoft Windows', 'Action, Adventure',
        'Experience the emotional storytelling and unforgettable characters in The Last of Us™, winner of over 200 Game of the Year awards.',
        '109', 'https://image.api.playstation.com/vulcan/ap/rnd/202206/0719/YScbS4L03wH1ABFZHgvL3NFW.jpg', '2022-09-02',
        '15'),
       ('Elden Ring', 'PlayStation 5, Microsoft Windows, Xbox One', 'Action, Action role-playing',
        'THE NEW FANTASY ACTION RPG. Rise, Tarnished, and be guided by grace to brandish the power of the Elden Ring and become an Elden Lord in the Lands Between.',
        '57', 'https://image.api.playstation.com/vulcan/ap/rnd/202108/0410/2odx6gpsgR6qQ16YZ7YkEt2B.png', '2022-02-25',
        '93');

INSERT INTO `orders` (`user_id`, `order_date`, `total_price`)
VALUES (1, '2022-10-29 11:28:03', 109.98),
       (5, '2023-04-23 15:28:50', 184.98),
       (2, '2022-12-21 17:28:15', 89),
       (6, '2023-08-23 21:10:57', 238.98),
       (9, '2023-05-05 14:28:25', 86.99);

INSERT INTO `order_items` (`order_id`, `game_id`, `quantity`, `unit_price`)
VALUES (1, 1, 1, 59.99),
       (1, 3, 1, 49.99),
       (2, 6, 2, 69),
       (2, 4, 1, 60.96),
       (2, 5, 1, 54.99),
       (3, 7, 1, 89),
       (4, 2, 1, 69.99),
       (4, 9, 1, 109),
       (4, 1, 1, 59.99),
       (5, 10, 1, 57),
       (5, 8, 1, 29.99);

INSERT INTO `cart_items` (`user_id`, `game_id`, `quantity`)
VALUES (8, 1, 1),
       (8, 6, 1),
       (3, 5, 1),
       (10, 2, 1);


