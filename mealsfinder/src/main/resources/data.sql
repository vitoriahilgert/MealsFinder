INSERT INTO environment_tags (name)
VALUES ('Aconchegante'),
       ('Moderno'),
       ('Romântico'),
       ('Familiar'),
       ('Ar livre'),
       ('Agitado');

INSERT INTO food_tags (name)
VALUES ('Pizza'),
       ('Sushi'),
       ('Hambúrguer'),
       ('Café'),
       ('Italiana'),
       ('Japonesa'),
       ('Sobremesa'),
       ('Vegano');

INSERT INTO service_tags (name)
VALUES ('Delivery Rápido'),
       ('Bom para Grupos'),
       ('Aceita Reservas'),
       ('Música ao Vivo');

INSERT INTO users (id, user_type, email, phone_number, username, password, is_account_confirmed, bio)
VALUES ('user-client-carlos', 'client', 'carlos.santos@email.com', '16991112222', 'carlos_santos', 'senha_hash_123', 1,
        'Amante de boa comida e fotografia.'),
       ('user-client-ana', 'client', 'ana.oliveira@email.com', '16992223333', 'ana_oliveira', 'senha_hash_456', 1,
        'Explorando os melhores cafés da cidade.'),
       ('user-client-bruno', 'client', 'bruno.costa@email.com', '16993334444', 'bruno_costa', 'senha_hash_789', 1,
        'Sempre em busca do sushi perfeito.'),
       ('user-est-pizzaria', 'establishment', 'contato@bellapizza.com', '1633445566', 'bellapizza', 'senha_hash_est1',
        1, 'A melhor pizza de fermentação natural da cidade desde 2010.'),
       ('user-est-sushi', 'establishment', 'atendimento@sushihouse.com', '1633556677', 'sushihouse', 'senha_hash_est2',
        1, 'Tradição e qualidade em cada peça. Venha nos conhecer!'),
       ('user-est-cafe', 'establishment', 'faleconosco@graocultura.com', '1633667788', 'graocultura', 'senha_hash_est3',
        1, 'Cafés especiais e um ambiente perfeito para trabalhar e relaxar.');

INSERT INTO clients (user_id)
VALUES ('user-client-carlos'),
       ('user-client-ana'),
       ('user-client-bruno');

INSERT INTO establishments (user_id, cnpj, name, establishment_type, status, is_delivery, is_presencial)
VALUES ('user-est-pizzaria', '11222333000144', 'Bella Pizza', 'Pizzaria', 'PENDENTE', 1, 1),
       ('user-est-sushi', '44555666000177', 'Sushi House', 'Restaurante Japonês', 'PENDENTE', 1, 1),
       ('user-est-cafe', '77888999000122', 'Grão & Cultura Café', 'Cafeteria', 'PENDENTE', 0, 1);

INSERT INTO posts (id, user_id, description)
VALUES ('post-01-pizzaria', 'user-est-pizzaria',
        'Fim de semana chegou! Que tal uma Bella Pizza para comemorar? Peça pelo nosso delivery!'),
       ('post-02-sushi', 'user-est-sushi',
        'Peixe fresco todos os dias. A qualidade que você merece está aqui no Sushi House.'),
       ('post-03-cafe', 'user-est-cafe',
        'Nosso cantinho especial esperando por você. Perfeito para uma pausa no seu dia.'),
       ('post-04-carlos', 'user-client-carlos', 'Acabei de sair da Bella Pizza. Que experiência!'),
       ('post-05-ana', 'user-client-ana', 'Trabalhando remotamente hoje do Grão & Cultura. Que lugar inspirador!'),
       ('post-06-bruno', 'user-client-bruno', 'Noite de sushi com os amigos!');

INSERT INTO reviews (post_id, reviewed_establishment_id, food_rate, service_rate, establishment_rate, price_rate)
VALUES ('post-04-carlos', 'user-est-pizzaria', 5.0, 4.0, 4.5, 4.0),
       ('post-05-ana', 'user-est-cafe', 5.0, 5.0, 5.0, 4.5),
       ('post-06-bruno', 'user-est-sushi', 4.5, 4.0, 4.0, 3.5);

INSERT INTO comments (id, post_id, user_id, description)
VALUES ('comment-01', 'post-01-pizzaria', 'user-client-carlos', 'A melhor da cidade, sem dúvidas!'),
       ('comment-02', 'post-01-pizzaria', 'user-client-ana', 'A de margherita é perfeita!'),
       ('comment-03', 'post-04-carlos', 'user-est-pizzaria', 'Que bom que gostou, Carlos! Volte sempre!'),
       ('comment-04', 'comment-01', 'user-client-bruno', 'Concordo plenamente!');

INSERT INTO images (id, post_id, url, image_type)
VALUES ('img-pizza-01', 'post-01-pizzaria', 'https://example.com/images/pizza_promo.jpg', 'POST');
INSERT INTO images (id, establishment_id, url, image_type)
VALUES ('img-cafe-ambiente', 'user-est-cafe', 'https://example.com/images/cafe_ambiente.jpg', 'ESTABLISHMENT_PICTURE'),
       ('img-sushi-fachada', 'user-est-sushi', 'https://example.com/images/sushi_fachada.jpg', 'ESTABLISHMENT_PICTURE'),
       ('img-menu-pizzaria-01', 'user-est-pizzaria', 'https://example.com/menus/pizzaria_pagina_1.jpg', 'MENU'),
       ('img-menu-pizzaria-02', 'user-est-pizzaria', 'https://example.com/menus/pizzaria_pagina_2.jpg', 'MENU'),
       ('img-menu-sushi-01', 'user-est-sushi', 'https://example.com/menus/sushi_completo.jpg', 'MENU'),
       ('img-menu-cafe-01', 'user-est-cafe', 'https://example.com/menus/cafe_bebidas.jpg', 'MENU'),
       ('img-menu-cafe-02', 'user-est-cafe', 'https://example.com/menus/cafe_comidas.jpg', 'MENU');

INSERT INTO post_likes (user_id, post_id)
VALUES ('user-client-ana', 'post-01-pizzaria'),
       ('user-client-bruno', 'post-01-pizzaria'),
       ('user-client-carlos', 'post-02-sushi'),
       ('user-client-ana', 'post-04-carlos'),
       ('user-est-sushi', 'post-04-carlos');

INSERT INTO comment_likes(user_id, comment_id)
VALUES ('user-client-ana', 'comment-01'),
       ('user-est-pizzaria', 'comment-01');

INSERT INTO follows (follower_id, following_id)
VALUES ('user-client-carlos', 'user-est-pizzaria'),
       ('user-client-carlos', 'user-est-sushi'),
       ('user-client-ana', 'user-est-cafe'),
       ('user-client-ana', 'user-client-carlos'),
       ('user-client-bruno', 'user-est-sushi');

INSERT INTO saved_posts (user_id, post_id)
VALUES ('user-client-ana', 'post-02-sushi');

INSERT INTO client_liked_food_tags (client_id, food_tag_id)
VALUES ('user-client-carlos', 1),
       ('user-client-carlos', 5),
       ('user-client-ana', 4),
       ('user-client-ana', 7),
       ('user-client-bruno', 2),
       ('user-client-bruno', 6);

INSERT INTO establishment_food_tags (establishment_id, food_tag_id)
VALUES ('user-est-pizzaria', 1),
       ('user-est-pizzaria', 5),
       ('user-est-sushi', 2),
       ('user-est-sushi', 6),
       ('user-est-cafe', 4);

INSERT INTO establishment_service_tags (establishment_id, service_tag_id)
VALUES ('user-est-pizzaria', 1),
       ('user-est-sushi', 3),
       ('user-est-cafe', 2);

INSERT INTO establishment_environment_tags (establishment_id, environment_tag_id)
VALUES ('user-est-pizzaria', 4),
       ('user-est-sushi', 2),
       ('user-est-sushi', 3),
       ('user-est-cafe', 1),
       ('user-est-cafe', 5);

INSERT INTO post_food_tags (post_id, food_tag_id)
VALUES ('post-04-carlos', 1),
       ('post-06-bruno', 2);

INSERT INTO user_activities (id, user_id, action_type, entity_type, entity_id)
VALUES ('activity-01', 'user-client-ana', 'LIKE', 'POST', 'post-01-pizzaria'),
       ('activity-02', 'user-client-bruno', 'LIKE', 'POST', 'post-01-pizzaria'),
       ('activity-03', 'user-client-carlos', 'COMMENT', 'POST', 'post-01-pizzaria'),
       ('activity-04', 'user-client-ana', 'FOLLOW', 'USER', 'user-est-cafe');